/*
 * Created on 08.04.2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package net.sourceforge.devtool.lib.gui.listener;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.List;


/**
 * @author tmichel
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class SingleSelectionListDownButtonListener extends SelectionAdapter {

    protected List list = null;
    
    
    /**
     * 
     */
    public SingleSelectionListDownButtonListener(final List list) {
        super();
        this.list = list;
    }

    /* (non-Javadoc)
     * @see org.eclipse.swt.events.SelectionListener#widgetDefaultSelected(org.eclipse.swt.events.SelectionEvent)
     */
    public void widgetDefaultSelected(SelectionEvent e) {
        super.widgetDefaultSelected(e);
        moveDown(e);
    }
    /* (non-Javadoc)
     * @see org.eclipse.swt.events.SelectionListener#widgetSelected(org.eclipse.swt.events.SelectionEvent)
     */
    public void widgetSelected(SelectionEvent e) {
        super.widgetSelected(e);
        moveDown(e);
    }
    
    
    protected void moveDown(final SelectionEvent e){
        final int selectedIndex = list.getSelectionIndex();
        if(selectedIndex < 0 ){
            // no selection
            return;
        }
        final int lastIndex = list.getItemCount()-1;
        if( selectedIndex<lastIndex){
            final String item = list.getItem(selectedIndex);
            list.remove(selectedIndex);
            list.add(item,selectedIndex+1);
            list.select(selectedIndex+1);
        }
    }
}
