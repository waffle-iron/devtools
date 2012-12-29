package encoding.plugin.encoding;

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
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;

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

	// TODO Write a JUnit Test for this code
	// /**
	// * Konvertiert die angegebene Datei bzw. alle Dateien in einem Verzeichnis
	// * und dessen Unterverzeichnissen in Dateien mit dem angegebenen Format.
	// * Wird kein Format angegeben wird in UTF-8 gespeichert.
	// *
	// * @param args
	// * [0] Quelldatei oder Verzeichnis
	// * @param args
	// * [1] Zielkodiertung (Default UTF-8)
	// */
	// public static void main(String[] args) {
	//
	// String filesystemEntryName = ".";
	// String targetCharset = "UTF-8";
	// switch (args.length) {
	// case 2 : {
	// targetCharset = args[1];
	// }
	// case 1 : {
	// filesystemEntryName = args[0];
	// break;
	// }
	// default : {
	// System.out
	// .println("Usage: EncodingConverter <dir|file>  [zielkodierung]");
	// System.exit(1);
	// }
	// }
	// final Stack<IAdaptable> fileStack = new Stack<IAdaptable>();
	// fileStack.push(filesystemEntryName);
	// final EncodingConverter util = new EncodingConverter();
	// util.convertFilesystemEntries2Charset(fileStack, targetCharset);
	// }

	/**
	 * Ermittelt zu allen Pfadangaben im �bergebenen Stack rekursiv
	 * untergeordnete Dateien. Diese werden jeweils eingelesen und dann eine
	 * Kopie im �bergebenen Format (z.B. UTF-8) erzeugt. Die Dateinamen der
	 * Kopien werden gebildet aus Originalnamen und dem Speicherformat als
	 * Postfix.
	 * 
	 * Durch Verwendung des Stacks wird ein OutOfMemory bei zu tiefer Rekursion
	 * verhindert.
	 * 
	 * @param entryStack
	 * @param charSet
	 */
	public void convertFilesystemEntries2Charset(
			final Stack<IAdaptable> entryStack, final String charSet) {

		// compute all path entries from stack
		while (!entryStack.isEmpty()) {

			final IAdaptable curEntry = entryStack.pop();

			// // get next path entry from stack
			// final IFile curFile = (IFile) entryStack.pop();
			// final String fullPath = curFile.getLocation().toOSString();
			// // make path to file handle
			// final File file = new File(fullPath);
			if (curEntry instanceof IFolder) {

				// push the members to the stack
				final IFolder curFolder = (IFolder) curEntry;
				try {
					final IResource[] dirEntries = curFolder.members();
					for (int i = 0; i < dirEntries.length; i++) {
						final String lastSegmentName = dirEntries[i].getName();

						// skip the current and the parent directory
						if ("..".equals(lastSegmentName)
								|| ".".equals(lastSegmentName)) {
							continue;
						}

						// push member to Stack (IFile or IFolder will be
						// expected)
						entryStack.push(dirEntries[i]);
					}
				} catch (CoreException e) {
					// TODO print out a message
					// "this resource could not be converted"
				}

			} else {

				// convert the file
				try {
					final IFile curFile = (IFile) curEntry;
					final String srcCharSet = curFile.getCharset();
					final String filePath = curFile.getLocation().toOSString();
					final File file = new File(filePath);
					if (shouldConvert(file, charSet)) {
						final File fileCopy = createCopyOfFile(file,
								srcCharSet, charSet);
						reorganizeSourceFile(file);
						reorganizeFileCopy(file, fileCopy);
					}
				} catch (CoreException ex) {
					// TODO print out a message
					// "this resource could not be read or so on"
				}

			}
		}// do until stack is empty
	}
	public File createCopyOfFile(final File src, final String srcCharSet,
			final String trgCharSet) {
		final String srcFileName = src.getAbsolutePath();
		InputStreamReader reader = null;
		BufferedWriter bufferedWriter = null;
		try {
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
	 * Subklassen k�nnen hier verschiedene Aktionen durchf�hren nachdem ein File
	 * konvertiert und gespeichert wurde. Vor Aufruf dieser Methode wird
	 * sichergestellt, dass alle Streams geschlossen sind.
	 * 
	 * So k�nnten die Datein umbenannt bzw. gel�scht werden.
	 * 
	 * @param srcFile
	 */
	protected void reorganizeSourceFile(final File srcFile) {
		moveFileToTmpDir(srcFile);
	}

	/**
	 * Subklassen k�nnen hier verschiedene Aktionen durchf�hren nachdem ein File
	 * konvertiert und gespeichert wurde. Vor Aufruf dieser Methode wird
	 * sichergestellt, dass alle Streams geschlossen sind.
	 * 
	 * So k�nnten die Datein umbenannt bzw. gel�scht werden.
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
