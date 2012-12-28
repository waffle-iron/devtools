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
public interface ISubject {

    public void addObserver(final IObserver observer);
    public void removeObserver(final IObserver observer);
    public void notifyObservers();
    public void setState(final Object object);
    public void setState(final Object object, final IObserver observer);
    public Object getState();
    
}
