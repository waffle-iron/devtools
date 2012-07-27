package com.github.funthomas424242.eclipseconfigurator.handlers;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.core.runtime.preferences.IPreferencesService;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.handlers.HandlerUtil;
import org.osgi.service.prefs.BackingStoreException;
import org.osgi.service.prefs.Preferences;

import com.github.funthomas424242.eclipseconfigurator.Activator;
import com.github.funthomas424242.eclipseconfigurator.preferences.PreferenceConstants;
import com.github.funthomas424242.eclipseutils.StringEscaper;
import com.github.funthomas424242.swtextensions.preferences.FileBrowser;

/**
 * Our sample handler extends AbstractHandler, an IHandler base class.
 * 
 * @see org.eclipse.core.commands.IHandler
 * @see org.eclipse.core.commands.AbstractHandler
 */
public class ExportHandler extends AbstractHandler {

	/**
	 * The constructor.
	 */
	public ExportHandler() {
	}

	/**
	 * the command has been executed, so extract extract the needed information
	 * from the application context.
	 */
	public Object execute(ExecutionEvent event) throws ExecutionException {

		final IWorkbenchWindow window = HandlerUtil
				.getActiveWorkbenchWindowChecked(event);
		final Shell shell = window.getShell();
		final File file = getExportFile(shell);

		// final IPreferenceStore pStore = Activator.getDefault()
		// .getPreferenceStore();
		// final String exchangeFilePath = pStore
		// .getString(PreferenceConstants.P_FILE);

		//
		IPreferencesService service = Platform.getPreferencesService();
		IEclipsePreferences rootNode = service.getRootNode();
		IEclipsePreferences exportNode = (IEclipsePreferences) rootNode
				.node(InstanceScope.SCOPE);

		// final File file = new File(exchangeFilePath);
		if (file.exists()) {
			file.delete();
		}

		// Popup Export Message
		MessageDialog.openInformation(
				shell,
				"Eclipseconfigurator",
				"Try to export Eclipse configuration to "
						+ file.getAbsolutePath());

		try {
			file.createNewFile();
			final FileOutputStream fileOutputStream = new FileOutputStream(file);
			final PrintStream printOutputStream = new PrintStream(
					fileOutputStream);
			// service.exportPreferences(exportNode, printOutputStream, null);
			exportStartNode(exportNode, printOutputStream);

		} catch (BackingStoreException e) {
			Activator.logError(e, e.getLocalizedMessage());
		} catch (Exception ex) {
			Activator.logError(ex, ex.getLocalizedMessage());
		}
		return null;
	}

	private void exportStartNode(final IEclipsePreferences exportNode,
			final PrintStream exportStream) throws BackingStoreException {

		final SimpleDateFormat format = new SimpleDateFormat("dd.mm.yyyy hh:mm");
		final String dateLine = format.format(new Date());

		exportStream.println("# Export vom " + dateLine);
		exportChildNodesFrom(exportNode, exportStream);

	}

	private void exportChildNodesFrom(final IEclipsePreferences parentNode,
			final PrintStream exportStream) throws BackingStoreException {
		// start export with export Node
		final String[] childrenNames = parentNode.childrenNames();
		for (int curChildNameIndex = 0; curChildNameIndex < childrenNames.length; curChildNameIndex++) {

			// next Child Node
			final String childName = childrenNames[curChildNameIndex];
			final Preferences childNode = parentNode.node(childName);
			final String lineHeader = childName;

			exportNodeAttributes(childNode, exportStream, lineHeader);

		}
	}

	private void exportNodeAttributes(final Preferences childNode,
			final PrintStream exportStream, final String lineHeader)
			throws BackingStoreException {

		final String[] attributeNames = childNode.keys();
		for (int curAttributeIndex = 0; curAttributeIndex < attributeNames.length; curAttributeIndex++) {
			exportStream.print(lineHeader + "/"
					+ attributeNames[curAttributeIndex]);
			exportStream.print("=");
			final String attributeValue = childNode.get(
					attributeNames[curAttributeIndex], "");

			// TODO import org.apache.commons.lang Bundle to escape with apache
			// Utils
			// String results = StringEscapeUtils.escapeJava(str);
			final StringEscaper escaper = new StringEscaper(attributeValue);
			final String escapedAttributeValue = escaper.getEscapedContent();
			exportStream.println(escapedAttributeValue);
		}
	}

	private File getExportFile(final Shell shell) throws ExecutionException {
		File exchangeFile = Activator.getDefault().getExchangeFilePreference();

		final FileBrowser fileBrowser = new FileBrowser(shell);
		exchangeFile = fileBrowser.getFile(exchangeFile, null,
				PreferenceConstants.FILE_EXTENSIONS);
		return exchangeFile;
	}
}
