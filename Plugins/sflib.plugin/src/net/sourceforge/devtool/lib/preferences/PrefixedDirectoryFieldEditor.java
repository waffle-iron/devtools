/*
 * Created on 07.04.2004
 * 
 * To change the template for this generated file go to Window - Preferences -
 * Java - Code Generation - Code and Comments
 */
package net.sourceforge.devtool.lib.preferences;

import org.eclipse.jface.preference.DirectoryFieldEditor;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

/**
 * @author tmichel
 * 
 * To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Generation - Code and Comments
 */
public class PrefixedDirectoryFieldEditor extends DirectoryFieldEditor {

    protected String prefPrefix = null;

    protected String prefName = null;

    /**
     * @param keyPrefix
     *            a variable prefix for preference key name
     * @param keyName
     *            a fix name for preference propertie
     * @param labelText
     *            a label for text label of input field
     * @param parent
     *            the field editor parent composite
     */
    public PrefixedDirectoryFieldEditor(final String keyPrefix,
            final String keyName, final String labelText, Composite parent) {
        super(keyPrefix + keyName, labelText, parent);
        this.prefPrefix = keyPrefix;
        this.prefName = keyName;
    }

    public void changePrefix(final String keyPrefix) {
        this.prefPrefix = keyPrefix;
        setPreferenceName(this.prefPrefix + this.prefName);
    }

    public void setToolTipText(final String toolTip) {
        final Text text = getTextControl();
        if (text != null) {
            text.setToolTipText(toolTip);
        }
        final Label label = getLabelControl();
        if (label != null) {
            label.setToolTipText(toolTip);
        }
    }

}