/**
 * 
 */
package com.gh.funthomas424242.swtextensions.preferences;

import java.io.File;

import org.eclipse.jface.preference.StringButtonFieldEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.FileDialog;

/**
 * @author SchubertT006
 * 
 */
public class NewFileFieldEditor extends StringButtonFieldEditor {

	private File filterPath;

	private String[] fileExtensions;

	/**
	 * 
	 */
	public NewFileFieldEditor() {
		super();
	}

	/**
	 * @param name
	 * @param labelText
	 * @param parent
	 */
	public NewFileFieldEditor(String name, String labelText, Composite parent) {
		super(name, labelText, parent);
	}

	/**
	 * @param filterPath
	 *            the filterPath to set
	 */
	public void setFilterPath(File filterPath) {
		this.filterPath = filterPath;
	}

	/**
	 * @param extensions
	 *            the extensions to set
	 */
	public void setFileExtensions(String[] extensions) {
		this.fileExtensions = extensions;
	}

	@Override
	protected String changePressed() {
		File startDirectory = new File(getTextControl().getText());
		File newFile = browseFile(startDirectory);
		if (newFile == null) {
			return null;
		}

		return newFile.getAbsolutePath();
	}

	@Override
	protected boolean checkState() {
		boolean isValid = super.checkState();
		if (isValid) {
			String path = getTextControl().getText();
			final File f = new File(path);
			// HINT Test auf isFile geht nicht, weil es dann existieren muss.
			isValid = !f.isDirectory();
		}
		return isValid;
	}

	/**
	 * Helper to open the file chooser dialog.
	 * 
	 * @param startDirectory
	 *            the directory to open the dialog on.
	 * @return File The File the user selected or <code>null</code> if they do
	 *         not.
	 */
	protected File browseFile(File startDirectory) {

		FileDialog dialog = new FileDialog(getShell(), SWT.OPEN | SWT.SHEET);
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
