package com.github.funthomas424242.eclipseconfigurator.handlers;

import java.io.File;
import java.io.FileOutputStream;
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
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferenceDialog;
import org.eclipse.jface.preference.PreferenceManager;
import org.eclipse.jface.preference.PreferenceStore;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.handlers.HandlerUtil;
import org.osgi.service.prefs.BackingStoreException;
import org.osgi.service.prefs.Preferences;

import com.github.funthomas424242.eclipseconfigurator.Activator;
import com.github.funthomas424242.eclipseconfigurator.preferences.PreferenceConstants;

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

		final IPreferenceStore pStore = Activator.getDefault()
				.getPreferenceStore();
		final String exchangeFilePath = pStore
				.getString(PreferenceConstants.P_FILE);

		//
		IPreferencesService service = Platform.getPreferencesService();
		IEclipsePreferences rootNode = service.getRootNode();
		IEclipsePreferences exportNode = (IEclipsePreferences) rootNode
				.node(InstanceScope.SCOPE);

		try {
			exportStartNode(exportNode);
		} catch (BackingStoreException e) {
			Activator.logError(e, e.getLocalizedMessage());
		}

		final IWorkbenchWindow window = HandlerUtil
				.getActiveWorkbenchWindowChecked(event);

		final File file = new File(exchangeFilePath);
		if (file.exists()) {
			file.delete();
		}

		// Popup Export Message
		MessageDialog.openInformation(
				window.getShell(),
				"Eclipseconfigurator",
				"Try to export Eclipse configuration to "
						+ file.getAbsolutePath());

		try {
			file.createNewFile();
			final FileOutputStream fileOutputStream = new FileOutputStream(file);
			service.exportPreferences(exportNode, fileOutputStream, null);
		} catch (Exception ex) {
			Activator.logError(ex, ex.getLocalizedMessage());
		}
		return null;
	}

	private void exportStartNode(IEclipsePreferences exportNode)
			throws BackingStoreException {

		final SimpleDateFormat format = new SimpleDateFormat("dd.mm.yyyy hh:mm");
		final String dateLine = format.format(new Date());
		System.out.println("# Export vom " + dateLine);

		exportChildNodesFrom(exportNode);
	}

	private void exportChildNodesFrom(IEclipsePreferences parentNode)
			throws BackingStoreException {
		// start export with export Node
		final String[] childrenNames = parentNode.childrenNames();
		for (int curChildNameIndex = 0; curChildNameIndex < childrenNames.length; curChildNameIndex++) {

			// next Child Node
			final String childName = childrenNames[curChildNameIndex];
			final Preferences childNode = parentNode.node(childName);
			final String lineHeader = childName;

			exportNodeAttributes(lineHeader, childNode);

		}
	}

	private void exportNodeAttributes(final String lineHeader,
			final Preferences childNode) throws BackingStoreException {

		final String[] attributeNames = childNode.keys();
		for (int curAttributeIndex = 0; curAttributeIndex < attributeNames.length; curAttributeIndex++) {
			System.out.print(lineHeader + "/"
					+ attributeNames[curAttributeIndex]);
			System.out.print("=");
			final String attributeValue = childNode.get(
					attributeNames[curAttributeIndex], "");
			final String escapedAttributeValue = escapeString(attributeValue);

			System.out.println(escapedAttributeValue);
		}
	}

	private String escapeString(final String value) {
		// TODO import org.apache.commons.lang Bundle to escape with apache
		// Utils
		// String results = StringEscapeUtils.escapeJava(str);

		// HINT replace \ to \\
		final StringBuffer buf = new StringBuffer();
		for (int i = 0; i < value.length(); i++) {
			final char character = value.charAt(i);
			if (character == '\\') {
				buf.append('\\');
				buf.append('\\');
			} else {
				buf.append(character);
			}
		}
		String escapedString = buf.toString();
		escapedString = escapedString.replaceAll("\\:", "\\\\:");
		escapedString = escapedString.replaceAll("=", "\\\\=");
		escapedString = escapedString.replaceAll("\t", "\\\\t");
		escapedString = escapedString.replaceAll("\r", "\\\\r");
		escapedString = escapedString.replaceAll("\n", "\\\\n");
		return escapedString;
	}
}
