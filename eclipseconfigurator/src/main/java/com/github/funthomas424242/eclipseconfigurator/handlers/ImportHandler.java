package com.github.funthomas424242.eclipseconfigurator.handlers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.core.runtime.preferences.IPreferencesService;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.handlers.HandlerUtil;

import com.github.funthomas424242.eclipseconfigurator.Activator;

/**
 * Our sample handler extends AbstractHandler, an IHandler base class.
 * 
 * @see org.eclipse.core.commands.IHandler
 * @see org.eclipse.core.commands.AbstractHandler
 */
public class ImportHandler extends AbstractHandler {
	/**
	 * The constructor.
	 */
	public ImportHandler() {
	}

	/**
	 * the command has been executed, so extract extract the needed information
	 * from the application context.
	 */
	public Object execute(ExecutionEvent event) throws ExecutionException {
		IWorkbenchWindow window = HandlerUtil
				.getActiveWorkbenchWindowChecked(event);
		String[] fileExtensions = { "*.*" };
		final File exchangeFile = browseFile(window.getShell(), null, null,
				fileExtensions);

		final boolean doIt = MessageDialog
				.openConfirm(
						window.getShell(),
						"Eclipseconfigurator",
						"Danger!\n Your current settings (all settings and in a global scope) will be overidden\n"
								+ " with the setting from the imported settings file: "
								+ exchangeFile.getAbsolutePath());

		if (doIt) {

			IPreferencesService service = Platform.getPreferencesService();

			try {

				final FileInputStream fileInputStream = new FileInputStream(
						exchangeFile);
				final IStatus status = service
						.importPreferences(fileInputStream);
				if (status.isOK()) {
					MessageDialog.openInformation(window.getShell(),
							"Eclipseconfigurator",
							"Settings successful imported");
				}

			} catch (Exception ex) {
				Activator.logError(ex, ex.getLocalizedMessage());
			}

		}
		return null;
	}

	/**
	 * Helper to open the file chooser dialog.
	 * 
	 * @param startDirectory
	 *            the directory to open the dialog on.
	 * @return File The File the user selected or <code>null</code> if they do
	 *         not.
	 */
	protected File browseFile(final Shell shell, File startDirectory,
			File filterPath, String[] fileExtensions) {

		FileDialog dialog = new FileDialog(shell, SWT.OPEN | SWT.SHEET);
		if (startDirectory != null) {
			dialog.setFileName(startDirectory.getPath());
		} else if (filterPath != null) {
			dialog.setFilterPath(filterPath.getPath());
		}
		if (fileExtensions != null) {
			dialog.setFilterExtensions(fileExtensions);
		}
		String filePath = dialog.open();
		if (filePath != null) {
			filePath = filePath.trim();
			if (filePath.length() > 0) {
				return new File(filePath);
			}
		}

		return null;
	}
}
