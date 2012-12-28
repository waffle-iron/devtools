package com.gh.funthomas424242.swtextensions.preferences;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Shell;

public class PopUpDialogHelper {

	final Shell swtShell;

	public PopUpDialogHelper(final Shell shell) {
		this.swtShell = shell;
	}

	public Boolean openDangerQuestionDialog(final String title,
			final String message) {
		final String[] buttonLabels = { "YES", "NO", "CANCEL" };
		final MessageDialog dialog = new MessageDialog(this.swtShell, title,
				null, message, MessageDialog.WARNING, buttonLabels, 1);
		final int buttonIndex = dialog.open();
		return (0 == buttonIndex);
	}
}
