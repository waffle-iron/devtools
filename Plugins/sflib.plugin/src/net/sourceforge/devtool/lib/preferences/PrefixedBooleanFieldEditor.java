package net.sourceforge.devtool.lib.preferences;

import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

/*
 * Created on 07.04.2004
 * 
 * To change the template for this generated file go to Window - Preferences -
 * Java - Code Generation - Code and Comments
 */
public class PrefixedBooleanFieldEditor extends BooleanFieldEditor {

    protected String prefPrefix = null;

    protected String prefName = null;

    protected Composite parent = null;

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
    public PrefixedBooleanFieldEditor(final String keyPrefix,
            final String keyName, final String labelText, Composite parent) {
        super(keyPrefix + keyName, labelText, parent);
        this.prefPrefix = keyPrefix;
        this.prefName = keyName;
        this.parent = parent;
    }

    public void changePrefix(final String keyPrefix) {
        this.prefPrefix = keyPrefix;
        setPreferenceName(this.prefPrefix + this.prefName);
    }

    public void setToolTipText(final String toolTip) {
        final Button button = getChangeControl(parent);
        if (button != null) {
            button.setToolTipText(toolTip);
        }
        final Label label = getLabelControl();
        if (label != null) {
            label.setToolTipText(toolTip);
        }
    }

}