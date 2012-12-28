/*
 * Created on 07.05.2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package net.sourceforge.devtool.lib.pattern.observer;


/**
 * @author tmichel
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class Observer implements IObserver {

    protected ISubject[] subjects=null;
    protected Object[] states =null;
    
    
    public Observer(final ISubject subject){
        subjects=new ISubject[]{ subject };
        states=new Object[1];
    }
    
    public Observer(final ISubject[] subjects){
        this.subjects=subjects;
        states= new Object[subjects.length];
    }
    
    /* (non-Javadoc)
     * @see net.sourceforge.devtool.lib.pattern.observer.IObserver#update()
     */
    public void update() {
        for(int i=0;i<subjects.length;i++){
            states[i]=subjects[i].getState();
        }// next subject
    }
    
    public Object getModel(){
        return getModel(0);
    }
    
    public Object getModel(final int i){
        return states[i];
    }
}
