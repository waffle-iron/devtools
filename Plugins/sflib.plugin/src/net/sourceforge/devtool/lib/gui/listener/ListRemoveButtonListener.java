/*
 * Created on 08.04.2004
 * 
 * To change the template for this generated file go to Window - Preferences -
 * Java - Code Generation - Code and Comments
 */
package net.sourceforge.devtool.lib.gui.listener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Shell;

/**
 * @author tmichel
 * 
 * To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Generation - Code and Comments
 */
public class ListRemoveButtonListener extends SelectionAdapter {

    protected List list = null;

    protected final ArrayList protectedEntries = new ArrayList();

    /**
     *  
     */
    public ListRemoveButtonListener(final List list) {
        super();
        this.list = list;
        this.protectedEntries.clear();
    }

    /**
     *  
     */
    public ListRemoveButtonListener(final List list,
            final String[] protectedEntries) {
        super();
        this.list = list;
        this.protectedEntries.clear();
        this.protectedEntries.addAll((Collection) Arrays.asList(protectedEntries));
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.swt.events.SelectionListener#widgetDefaultSelected(org.eclipse.swt.events.SelectionEvent)
     */
    public void widgetDefaultSelected(SelectionEvent e) {
        super.widgetDefaultSelected(e);
        doRemove(e);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.swt.events.SelectionListener#widgetSelected(org.eclipse.swt.events.SelectionEvent)
     */
    public void widgetSelected(SelectionEvent e) {
        super.widgetSelected(e);
        doRemove(e);
    }

    protected void doRemove(final SelectionEvent e) {
        while (list.getSelectionCount() > 0) {
            final int[] indices = list.getSelectionIndices();
            final String item = list.getItem(indices[0]);
            if (!protectedEntries.contains(item)) {
                list.remove(indices[0]);
                removeDone(item);
            } else {
                MessageDialog.openInformation(new Shell(), "Protected Item: "
                        + item, "This item could not be removed !");
                list.deselect(indices[0]);
            }
        }// next item
        list.deselectAll();
        list.select(0);
        removeFinished();
    }

    /**
     * Notify subclasses for successful remove a item done. Subclasses not need
     * to call super.
     * 
     * @param item
     *            wich was removed
     */
    protected void removeDone(final String item) {
    };

    /**
     * Notify subclasses: The remove of items is finished. Subclasses not need
     * to call super.
     */
    protected void removeFinished() {
    }

}