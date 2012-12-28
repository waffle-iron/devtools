/*
 * Created on 07.04.2004
 * 
 * To change the template for this generated file go to Window - Preferences -
 * Java - Code Generation - Code and Comments
 */
package net.sourceforge.devtool.lib.helper;

import java.util.ArrayList;

import net.sourceforge.devtool.lib.preferences.IListInputValidator;

/**
 * @author tmichel
 * 
 * To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Generation - Code and Comments
 */
public class UniqueListInputValidator implements IListInputValidator {

    protected String[] listItems = new String[0];

    /*
     * (non-Javadoc)
     * 
     * @see net.sourceforge.devtool.jdosupport.helper.IListInputValidator#updateListItems(java.lang.String[])
     */
    public void updateListItems(String[] items) {
        if (items != null) {

            this.listItems = items;
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.dialogs.IInputValidator#isValid(java.lang.String)
     */
    public String isValid(String newText) {
        final String errorMessage = "The entry already exists in the list !";
        for (int i = 0; i < listItems.length; i++) {
            if (listItems[i].equals(newText)) { return errorMessage; }
        }
        return null;
    }

    
    public String[] getValidSubSet(final String[] valuesToAdd ){
        final ArrayList list = new ArrayList();
        for(int i=0;i<valuesToAdd.length;i++){
            if( isValid(valuesToAdd[i])==null){
                list.add(valuesToAdd[i]);
            }
        }
        final String[] subSet= new String[list.size()];
        list.toArray(subSet);
        return subSet;
    }
    
}