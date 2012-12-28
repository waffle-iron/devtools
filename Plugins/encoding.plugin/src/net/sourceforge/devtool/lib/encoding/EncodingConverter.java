package net.sourceforge.devtool.lib.encoding;

/**
 * 
 */
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.glaforge.i18n.io.CharsetToolkit;

/**
 * Diese Klasse wandelt beliebig kodierte Dateien in das UTF8 Format. Die
 * Erkennung der Kodierung der Quelldatei basiert auf den Verfahren und Klassen
 * von Guillaume LAFORGE
 * (http://glaforge.free.fr/wiki/index.php?wiki=GuessEncoding)
 * 
 * @author Thomas Michel <development@thomas-michel.info>
 */
public class EncodingConverter {

	/**
	 * Comment for <code>logger</code> Objekt zur Ausgabe von Logmeldungen.
	 */
	final protected Logger logger = Logger.getLogger(getClass().getName());

	/**
	 * Konvertiert die angegebene Datei bzw. alle Dateien in einem Verzeichnis
	 * und dessen Unterverzeichnissen in Dateien mit dem angegebenen Format.
	 * Wird kein Format angegeben wird in UTF-8 gespeichert.
	 * 
	 * @param args[0]
	 *            Quelldatei oder Verzeichnis
	 * @param args[1]
	 *            Zielkodiertung (Default UTF-8)
	 */
	public static void main(String[] args) {

		String filesystemEntryName = ".";
		String targetCharset = "UTF-8";
		switch (args.length) {
		case 2: {
			targetCharset = args[1];
		}
		case 1: {
			filesystemEntryName = args[0];
			break;
		}
		default: {
			System.out
					.println("Usage: EncodingConverter <dir|file>  [zielkodierung]");
			System.exit(1);
		}
		}
		final Stack fileStack = new Stack();
		fileStack.push(filesystemEntryName);
		final EncodingConverter util = new EncodingConverter();
		util.convertFilesystemEntries2Charset(fileStack, targetCharset);
	}

	/**
	 * Ermittelt zu allen Pfadangaben im übergebenen Stack rekursiv
	 * untergeordnete Dateien. Diese werden jeweils eingelesen und dann eine
	 * Kopie im übergebenen Format (z.B. UTF-8) erzeugt. Die Dateinamen der
	 * Kopien werden gebildet aus Originalnamen und dem Speicherformat als
	 * Postfix.
	 * 
	 * Durch Verwendung des Stacks wird ein OutOfMemory bei zu tiefer Rekursion
	 * verhindert.
	 * 
	 * @param entryStack
	 * @param charSet
	 */
	public void convertFilesystemEntries2Charset(final Stack entryStack,
			final String charSet) {

		// compute all path entries from stack
		while (!entryStack.isEmpty()) {

			// get next path entry from stack
			final String curEntry = (String) entryStack.pop();
			// make path to file handle
			final File file = new File(curEntry);
			if (file.isDirectory()) {
				// path entry is a directory
				// list of file and subdirectory names
				final String[] dirEntries = file.list();
				// Alle Verzeichniseinträge auf den Stack legen
				for (int i = 0; i < dirEntries.length; i++) {
					// skip the current and the parent directory
					if ("..".equals(dirEntries[i]) || ".".equals(dirEntries[i])) {
						continue;
					}
					// put new path entry onto stack
					entryStack.push(curEntry + File.separator + dirEntries[i]);
				}// next entry
			} else {
				// convert the file
				if (shouldConvert(file, charSet)) {
					final File fileCopy = createCopyOfFile(file, charSet);
					reorganizeSourceFile(file);
					reorganizeFileCopy(file, fileCopy);
				}

			}
		}//do until stack is empty
	}

	public File createCopyOfFile(final File src, final String trgCharSet) {
		final String srcFileName = src.getAbsolutePath();
		InputStreamReader reader = null;
		BufferedWriter bufferedWriter = null;
		try {
			final Charset srcCharSet = CharsetToolkit.guessEncoding(src, 4096);
			final FileInputStream inStream = new FileInputStream(src);
			reader = new InputStreamReader(inStream, srcCharSet);
			final String outFileName = srcFileName + "." + trgCharSet;
			logger.info("Lese [" + srcCharSet + "]" + srcFileName);
			final String trgFileName = getFileNameOfCopy(srcFileName,
					trgCharSet);
			final File trgFile = new File(trgFileName);
			logger.info("Schreibe [" + trgCharSet + "]" + outFileName);
			final FileOutputStream fileOutputStream = new FileOutputStream(
					trgFile);
			final OutputStreamWriter writer = new OutputStreamWriter(
					fileOutputStream, trgCharSet);
			bufferedWriter = new BufferedWriter(writer);
			int c = reader.read();
			while (c != -1) {
				bufferedWriter.write(c);
				c = reader.read();
			}// next byte
			bufferedWriter.flush();
			return trgFile;
		} catch (Exception ex) {
			logger.log(Level.SEVERE, "could not found file: " + srcFileName);
		} finally {
			try {
				if (reader != null) {
					reader.close();
				}
				if (bufferedWriter != null) {
					bufferedWriter.close();
				}
			} catch (IOException ex) {
				logger.log(Level.SEVERE, ex.getLocalizedMessage());
			}
		}
		return null;
	}

	public void moveFileToTmpDir(final File srcFile) {
		String srcFileName = "";
		try {
			srcFileName = srcFile.getCanonicalPath();
		} catch (IOException e) {
			logger.log(Level.SEVERE, e.getLocalizedMessage());
		}
		if (srcFileName.charAt(1) == ':') {
			srcFileName = srcFileName.substring(2);
		}
		final String tmpDirName = System.getProperty("java.io.tmpdir");
		final File tmpDir = new File(tmpDirName);
		File renamedFile = null;
		renamedFile = new File(tmpDir, srcFileName);
		if (renamedFile.exists()) {
			renamedFile.delete();
		}
		renamedFile.getParentFile().mkdirs();
		final boolean renamed = srcFile.renameTo(renamedFile);
		final String actionMessage = srcFile.getAbsolutePath() + " --> "
				+ renamedFile.getAbsolutePath();
		if (renamed) {
			logger.info("Umbenannt: " + actionMessage);
		} else {
			logger.log(Level.SEVERE, "Umbenennung: " + actionMessage);
		}
	}

	/**
	 * Creating a new File depends on source file and target charset.
	 * 
	 * @param srcFileName
	 *            name of source file.
	 * @param trgCharSet
	 *            name of target encoding charset.
	 * @return File abstract description of new file.
	 */
	protected String getFileNameOfCopy(final String srcFileName,
			final String trgCharSet) {
		return srcFileName + "." + trgCharSet;
	}

	/**
	 * Subklassen können hier verschiedene Aktionen durchführen nachdem ein File
	 * konvertiert und gespeichert wurde. Vor Aufruf dieser Methode wird
	 * sichergestellt, dass alle Streams geschlossen sind.
	 * 
	 * So könnten die Datein umbenannt bzw. gelöscht werden.
	 * 
	 * @param srcFile
	 */
	protected void reorganizeSourceFile(final File srcFile) {
		moveFileToTmpDir(srcFile);
	}

	/**
	 * Subklassen können hier verschiedene Aktionen durchführen nachdem ein File
	 * konvertiert und gespeichert wurde. Vor Aufruf dieser Methode wird
	 * sichergestellt, dass alle Streams geschlossen sind.
	 * 
	 * So könnten die Datein umbenannt bzw. gelöscht werden.
	 * 
	 * @param srcFile
	 * @param trgFile
	 */
	protected void reorganizeFileCopy(final File srcFile, final File trgFile) {
		final boolean renamed = trgFile.renameTo(srcFile);
		final String actionMessage = trgFile.getAbsolutePath() + " --> "
				+ srcFile.getAbsolutePath();

		if (renamed) {
			logger.info("Umbenannt: " + actionMessage);
		} else {
			logger.log(Level.SEVERE, "Umbenennung: " + actionMessage);
		}
	}

	/**
	 * This method returns true if the encoding of file should be converted.
	 * Subclass should override this method to support pattern matching of file
	 * names.
	 * 
	 * @param file
	 *            the next file to convert
	 * @param trgCharset
	 *            the encoding to convert into
	 * @return true the given file encoding will be converted
	 */
	protected boolean shouldConvert(final File file, final String trgCharset) {
		return true;
	}

}

