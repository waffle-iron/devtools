package com.gh.funthomas424242.swtextensions.preferences;

import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.swt.widgets.Composite;

public class LabelFieldEditor extends StringFieldEditor {

	public LabelFieldEditor(final String labelText, final Composite parent) {
		super("", labelText, parent);
		getTextControl().setVisible(false);
	}
}
