package com.github.funthomas424242.eclipseconfigurator.handlers;

import java.io.File;
import java.io.FileInputStream;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.preferences.IPreferencesService;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.handlers.HandlerUtil;

import com.github.funthomas424242.eclipseconfigurator.Activator;
import com.github.funthomas424242.eclipseconfigurator.preferences.PreferenceConstants;
import com.github.funthomas424242.swtextensions.preferences.FileBrowser;
import com.github.funthomas424242.swtextensions.preferences.PopUpDialogHelper;

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

		final IWorkbenchWindow window = HandlerUtil
				.getActiveWorkbenchWindowChecked(event);
		final Shell shell = window.getShell();
		final File exchangeFile = getImportFile(shell);

		if (exchangeFile != null) {

			final PopUpDialogHelper dialogHelper = new PopUpDialogHelper(shell);
			final Boolean doIt = dialogHelper
					.openDangerQuestionDialog(
							"!!! Danger !!! - Do you will continue?",
							"Danger!\n\n Your current settings (all settings and in a global scope) will be overidden\n"
									+ " with the setting from the imported settings file: \n\n"
									+ exchangeFile.getAbsolutePath()
									+ "\n\n"
									+ "Do you will continue?");

			if (Boolean.TRUE.equals(doIt)) {

				IPreferencesService service = Platform.getPreferencesService();

				try {
					final FileInputStream fileInputStream = new FileInputStream(
							exchangeFile);
					final IStatus status = service
							.importPreferences(fileInputStream);

					if (status.isOK()) {
						MessageDialog.openInformation(window.getShell(),
								"Eclipse Configurator",
								"Settings successful imported");

					}

				} catch (Exception ex) {
					Activator.logError(ex, ex.getLocalizedMessage());
				}

			}
		}
		return null;
	}

	private File getImportFile(final Shell shell) throws ExecutionException {
		File exchangeFile = Activator.getDefault().getExchangeFilePreference();

		final FileBrowser fileBrowser = new FileBrowser(shell);
		exchangeFile = fileBrowser.getFile(exchangeFile, null,
				PreferenceConstants.FILE_EXTENSIONS);
		return exchangeFile;
	}

}
