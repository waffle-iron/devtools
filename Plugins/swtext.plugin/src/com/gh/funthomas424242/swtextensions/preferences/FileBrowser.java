/**
 * 
 */
package com.gh.funthomas424242.swtextensions.preferences;

import java.io.File;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;

/**
 * @author SchubertT006
 * 
 */
public class FileBrowser {

	protected Shell swtShell;

	public FileBrowser(final Shell shell) {
		swtShell = shell;
	}

	/**
	 * Helper to open the file chooser dialog.
	 * 
	 * @param startDirectory
	 *            the directory to open the dialog on.
	 * @return File The File the user selected or <code>null</code> if they do
	 *         not.
	 */
	public File getFile(File startDirectory, File filterPath,
			String[] fileExtensions) {

		FileDialog dialog = new FileDialog(this.swtShell, SWT.OPEN | SWT.SHEET);
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
