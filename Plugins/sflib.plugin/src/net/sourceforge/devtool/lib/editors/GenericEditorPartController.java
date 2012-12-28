/*
 * Created on 01.05.2004
 * 
 * To change the template for this generated file go to Window - Preferences -
 * Java - Code Generation - Code and Comments
 */
package net.sourceforge.devtool.lib.editors;

import net.sourceforge.devtool.lib.pattern.observer.IObserver;
import net.sourceforge.devtool.lib.pattern.observer.ISubject;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.ui.IEditorInput;

/**
 * @author tmichel
 * 
 * To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Generation - Code and Comments
 */
public abstract class GenericEditorPartController implements IObserver {

    protected ISubject modelWrapper = null;
    protected boolean ignoreIsDirtyChanges = false;

    public GenericEditorPartController() {
    }
    
    public ISubject getModel() {
        return modelWrapper;
    }

    public void setModel(final ISubject model) {
        if (modelWrapper != null) {
            modelWrapper.removeObserver(this);
        }
        this.modelWrapper = model;
        this.modelWrapper.addObserver(this);
    }


    public void initControls(final IEditorInput editorInput) {
        initModel(editorInput);
        initListener();
        // init layout based on model
        update();
        //updateControlsFromModel();
    }

    public void setIgnoreDirtyChanges(final boolean ignoreDirtyChanges) {
        this.ignoreIsDirtyChanges = ignoreDirtyChanges;
    }

    /**
     * This method must be set a dirty flag in subclasses.
     * General this flag should be stored in the specific shared model, 
     * so only subclasses could be implements this method correct.
     * Attetion: subclasses should call super at the end of method to fire
     * the fireIsDirtyChangeEvent method.
     * 
     * @param isDirty
     * 
     * @author tmichel
     */
    protected void setDirty(boolean isDirty) {
        if (!ignoreIsDirtyChanges) {
            fireIsDirtyChangedEvent();
        }
    }

    abstract protected void initModel(final IEditorInput editorInput);

    /**
     * Subclasses should a all listener for gui controls in this method.
     * So the full behaivor is stored at this place.
     * In the most cases, listener should be set attributes of shared model 
     * then call the setDirty(true) method.
     */
    abstract protected void initListener();

    /**
     * This method should be implements in a anonymous subclass of
     * of contoller. A good place is the constructor of editor part, wich 
     * should use a expression to instanciation of controller. 
     * At this place we can call the firePropertyChange(PROP_DIRTY) method of
     * editor part to notify for dirty changes.
     */
    abstract protected void fireIsDirtyChangedEvent();

    /**
     * This method should be update all gui controls based on
     * the current values of the shared model. If needed this
     * method must be init basic values (such as selection(0) of lists)
     * to secure a user choice.
     */
    abstract protected void updateControlsFromModel();

    /**
     * This method should read the dirty flag from the shared model.
     * @return true the editor part is dirty, otherwise false.
     */
    abstract public boolean isDirty();

    /**
     * Subclasses must be implements the full save logic at this place.
     * 
     * @param input
     * @param monitor
     */
    abstract public void doSave(final IEditorInput input,
            final IProgressMonitor monitor);


}