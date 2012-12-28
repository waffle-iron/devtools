/*
 * Created on 07.04.2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package net.sourceforge.devtool.lib.preferences;

import org.eclipse.jface.dialogs.IInputValidator;


/**
 * @author tmichel
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public interface IListInputValidator extends IInputValidator {
    public void updateListItems(String[] items);
}
