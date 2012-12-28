/*
 * Created on 07.04.2004
 * 
 * To change the template for this generated file go to Window - Preferences -
 * Java - Code Generation - Code and Comments
 */
package net.sourceforge.devtool.lib.preferences;

import java.util.StringTokenizer;

import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.preference.ListEditor;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Shell;

/**
 * @author tmichel
 * 
 * To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Generation - Code and Comments
 */
public class StringListFieldEditor extends ListEditor {

    protected Composite parent = null;
    protected IListInputValidator validator = null;
    protected String[] protectedEntries = new String[0];
    
   
    
    public StringListFieldEditor(final String prefName, final String labelText,
            final Composite parent, final IListInputValidator validator) {
        super(prefName, labelText, parent);
        this.parent = parent;
        this.validator = validator;
        final List listControl = getListControl(parent);
        listControl.addSelectionListener(new SelectionListener(){

            /* (non-Javadoc)
             * @see org.eclipse.swt.events.SelectionListener#widgetSelected(org.eclipse.swt.events.SelectionEvent)
             */
            public void widgetSelected(SelectionEvent e) {
                final int i= listControl.getSelectionIndex();
                final String value = listControl.getItem(i);
                boolean disableRemove = false;
                for( int item=0;item<protectedEntries.length;item++){
                    if(protectedEntries[item].equals(value) ){
                        disableRemove = true;
                        break;
                    }
                }
               // FIXME here we must enable/disable the remove Button
                // but we can get him
                // as workaround we remove the selection
                if( disableRemove ){
                    listControl.deselect(i);
                }
                
            }

            
            
            /* (non-Javadoc)
             * @see org.eclipse.swt.events.SelectionListener#widgetDefaultSelected(org.eclipse.swt.events.SelectionEvent)
             */
            public void widgetDefaultSelected(SelectionEvent e) {
                // for double click we have no interest
            }
        });
    }

    
    public void setProtectedEntries(final String[] protectedEntries){
        this.protectedEntries = protectedEntries;
    }
    
    
    
    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.preference.ListEditor#createList(java.lang.String[])
     */
    protected String createList(String[] items) {
        final StringBuffer buf = new StringBuffer();
        for (int i = 0; i < items.length; i++) {
            if (i > 0) {
                buf.append(',');
            }
            buf.append(items[i]);
        }
        return buf.toString();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.preference.ListEditor#getNewInputObject()
     */
    protected String getNewInputObject() {
        final List listControl = getListControl(parent);
        this.validator.updateListItems(listControl.getItems());
        final InputDialog dialog = new InputDialog(new Shell(),
                "Add JDO Vendor", "Enter the Name of JDO Vendor", "new Vendor",
                validator);
        dialog.setBlockOnOpen(true);
        int ret = dialog.open();
        String value = null;
        if( ret == InputDialog.OK ){
            value = dialog.getValue();
        }
        return value;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.preference.ListEditor#parseString(java.lang.String)
     */
    protected String[] parseString(String stringList) {
        final StringTokenizer tok = new StringTokenizer(stringList, ",", false);
        final String[] entries = new String[tok.countTokens()];
        int i = 0;
        while (tok.hasMoreTokens()) {
            entries[i] = tok.nextToken();
            i++;
        }
        return entries;
    }

    public void setToolTipText(final String toolTip) {
        final List list = getListControl(parent);
        if (list != null) {
            list.setToolTipText(toolTip);
        }
        final Label label = getLabelControl();
        if (label != null) {
            label.setToolTipText(toolTip);
        }
    }
    
    

}