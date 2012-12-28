package net.sourceforge.devtool.lib.editors;

import net.sourceforge.devtool.lib.pattern.observer.ISubject;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.EditorPart;

/**
 * @todo Provide description for "GenericEditorPart".
 */
public abstract class GenericEditorPart extends EditorPart implements
        IGenericEditorPart {

    protected GenericEditorPartController controller = null;

    /**
     * @todo Implement the "GenericEditorPart" constructor.
     */
    public void setGenericEditorPartController(
            GenericEditorPartController controller) {
        this.controller = controller;

    }

    /*
     * (non-Javadoc)
     * 
     * @see net.sourceforge.devtool.lib.editors.IGenericEditorPart#getModel()
     */
    public ISubject getModel() {
        return (controller != null) ? controller.getModel() : null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see net.sourceforge.devtool.lib.editors.IGenericEditorPart#updateModel(java.lang.Object)
     */
    public void setModel(ISubject model) {
        if (controller != null) {
            controller.setModel(model);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.ui.ISaveablePart#doSave(org.eclipse.core.runtime.IProgressMonitor)
     */
    public void doSave(IProgressMonitor monitor) {
        if (controller != null) {
            controller.doSave(getEditorInput(), monitor);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.ui.ISaveablePart#isDirty()
     */
    public boolean isDirty() {
        return (controller != null) ? controller.isDirty() : false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.ui.IWorkbenchPart#createPartControl(org.eclipse.swt.widgets.Composite)
     */
    public void createPartControl(Composite parent) {
        createControls(parent);
        if (controller != null) {
            initController(controller);
            controller.initControls(getEditorInput());
        }
    }

    abstract protected void createControls(Composite parent);

    abstract protected void initController(
            GenericEditorPartController controller);

}