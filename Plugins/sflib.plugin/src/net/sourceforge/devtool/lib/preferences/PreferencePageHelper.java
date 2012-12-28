/*
 * Created on 06.04.2004
 * 
 * To change the template for this generated file go to Window - Preferences -
 * Java - Code Generation - Code and Comments
 */
package net.sourceforge.devtool.lib.preferences;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;


/**
 * @author tmichel
 * 
 * To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Generation - Code and Comments
 */
public class PreferencePageHelper {

    /**
     * FIXME BOGUS This method don't work at now for plugin net.sourceforge.devtool
     * @param composite
     * @param imageDescriptor
     */
    public static void addImageToComposite(final Composite composite, final ImageDescriptor imageDescriptor) {
        final Image image = imageDescriptor.createImage();

        final Button button = new Button(composite, SWT.FLAT);
        button.setImage(image);
        button.addDisposeListener(new DisposeListener() {

            /*
             * (non-Javadoc)
             * 
             * @see org.eclipse.swt.events.DisposeListener#widgetDisposed(org.eclipse.swt.events.DisposeEvent)
             */
            public void widgetDisposed(DisposeEvent e) {
                if (image != null) {

                    image.dispose();
                }
            }
        });
    }

}