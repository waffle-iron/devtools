/*
 * Created on 07.05.2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package net.sourceforge.devtool.lib.pattern.observer;

import java.util.Iterator;
import java.util.List;
import java.util.Vector;


/**
 * @author tmichel
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class Subject implements ISubject {
    
    protected final List observers=new Vector();
    protected Object state=null;
    
    public Subject(){}
    

    /* (non-Javadoc)
     * @see net.sourceforge.devtool.lib.pattern.observer.ISubject#addObserver(net.sourceforge.devtool.lib.pattern.observer.IObserver)
     */
    public void addObserver(IObserver observer) {
        if(!observers.contains(observer)){
            observers.add(observer);
        }
    }
    /* (non-Javadoc)
     * @see net.sourceforge.devtool.lib.pattern.observer.ISubject#getState()
     */
    public Object getState() {
        return state;
    }
    /* (non-Javadoc)
     * @see net.sourceforge.devtool.lib.pattern.observer.ISubject#notifyObservers()
     */
    public void notifyObservers() {
        notifyObservers(null);
//       for( Iterator it=observers.iterator(); it.hasNext();){
//           final IObserver observer=(IObserver)it.next();
//           observer.update();
//       }
    }

    public void notifyObservers(final IObserver trigger) {
        for( Iterator it=observers.iterator(); it.hasNext();){
            final IObserver observer=(IObserver)it.next();
            if( observer == trigger )continue;
            observer.update();
        }
     }

    
    /* (non-Javadoc)
     * @see net.sourceforge.devtool.lib.pattern.observer.ISubject#removeObserver(net.sourceforge.devtool.lib.pattern.observer.IObserver)
     */
    public void removeObserver(IObserver observer) {
        observers.remove(observer);

    }
   
    /* This method is triggered the notify method.
     * @see net.sourceforge.devtool.lib.pattern.observer.ISubject#setState(java.lang.Object)
     */
    public void setState(final Object object) {
        setState(object,null);
    }
    /* This method is triggered the notify method.
     * @see net.sourceforge.devtool.lib.pattern.observer.ISubject#setState(java.lang.Object)
     */
    public void setState(final Object object,final  IObserver observer) {
       state=object;
       notifyObservers(observer);
    }

}
