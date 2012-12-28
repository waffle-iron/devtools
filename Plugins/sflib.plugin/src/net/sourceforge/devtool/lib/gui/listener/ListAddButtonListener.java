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
public abstract class ListAddButtonListener extends SelectionAdapter {

    protected List list = null;
    
    
    /**
     * 
     */
    public ListAddButtonListener(final List list){
        super();
        this.list = list;
    }

    /* (non-Javadoc)
     * @see org.eclipse.swt.events.SelectionListener#widgetDefaultSelected(org.eclipse.swt.events.SelectionEvent)
     */
    public void widgetDefaultSelected(SelectionEvent e) {
        super.widgetDefaultSelected(e);
        doAdd(e);
    }
    /* (non-Javadoc)
     * @see org.eclipse.swt.events.SelectionListener#widgetSelected(org.eclipse.swt.events.SelectionEvent)
     */
    public void widgetSelected(SelectionEvent e) {
        super.widgetSelected(e);
        doAdd(e);
    }
    
    protected void doAdd(final SelectionEvent e){
        final String[] values = getNewInput();
        for(int i=0; i<values.length; i++ ){
            list.add(values[i]);
            addDone(values[i]);
        }
        list.deselectAll();
        if( values.length >0 ){
            list.select(list.indexOf(values[0]));
        }
        addFinished();
    }
    
    /**
     * Notify subclasses for successful add a item done.
     * Subclasses not need to call super.
     * @param item wich was added
     */
    protected void addDone(final String item){};
    /**
     * Notify subclasses: The add of new items is finished.
     * Subclasses not need to call super.
     */
    protected void addFinished(){}
    protected abstract String[] getNewInput();
        
   
    
}
