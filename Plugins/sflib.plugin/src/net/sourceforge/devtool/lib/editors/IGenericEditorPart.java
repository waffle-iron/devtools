/*
 * Created on 04.05.2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package net.sourceforge.devtool.lib.editors;

import net.sourceforge.devtool.lib.pattern.observer.ISubject;

import org.eclipse.ui.IEditorPart;


/**
 * @author tmichel
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public interface IGenericEditorPart extends IEditorPart{
    public ISubject getModel();
    public void setModel(final ISubject model);
}
