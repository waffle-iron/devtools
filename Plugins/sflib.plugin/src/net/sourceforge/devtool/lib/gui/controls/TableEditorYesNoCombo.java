/*
 * Created on 29.04.2004
 * 
 * To change the template for this generated file go to Window - Preferences -
 * Java - Code Generation - Code and Comments
 */
package net.sourceforge.devtool.lib.gui.controls;

import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.widgets.Table;

/**
 * @author tmichel
 * 
 * To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Generation - Code and Comments
 */
public class TableEditorYesNoCombo {

    protected CCombo combo = null;

    public TableEditorYesNoCombo(final Table parent, final int style) {
        this.combo = new CCombo(parent, style);
        this.combo.add("yes");
        this.combo.add("no");
    }

    public void setSelection(final boolean selected) {

        if(selected){
            combo.select(0);
        }else{
            combo.select(1);
        }
    }
    
    public void setValue(final String value){
        setSelection("yes".equals(value));
    }
    
    
    public CCombo getCombo(){
        return combo;
    }
    


    
    
}