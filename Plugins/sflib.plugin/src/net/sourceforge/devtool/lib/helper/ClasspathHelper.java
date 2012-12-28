/*
 * Created on 23.04.2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package net.sourceforge.devtool.lib.helper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import net.sourceforge.devtool.lib.LibPlugin;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;


/**
 * @author tmichel
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class ClasspathHelper {
    public static boolean addClasspathEntries(final IJavaProject project,
            final IClasspathEntry[] entries, final IProgressMonitor monitor) {

        boolean ok = true;
        try {
            final ArrayList newEntries = new ArrayList();
            // add old classpath entries
            final IClasspathEntry[] oldEntries = project.getRawClasspath();
            newEntries.addAll((Collection) Arrays.asList(oldEntries));
            // add new classpath entries
            newEntries.addAll((Collection) Arrays.asList(entries));
            // put all entries into a array
            IClasspathEntry[] newEntryList = new IClasspathEntry[newEntries
                    .size()];
            newEntries.toArray(newEntryList);
            project.setRawClasspath(newEntryList, monitor);
            project.makeConsistent(new NullProgressMonitor());
        } catch (Exception ex) {
            ok = false;
            LibPlugin.getDefault()
                    .logError(ClasspathHelper.class, ex);
        }
        return ok;
    } // addClasspathEntriesToProject

    // FIXME move to LibPlugin
    public static boolean removeClasspathEntry(final IJavaProject project,
            final IPath entryPath, final IProgressMonitor monitor) {

        boolean ok = true;
        try {

            final IClasspathEntry[] oldEntries = project.getRawClasspath();
            final ArrayList newEntries = new ArrayList();
            IPath path = null;
            for (int index = 0; index < oldEntries.length; index++) {
                // copy non matching old entries to new list
                path = oldEntries[index].getPath();
                if (path != null && !path.equals(entryPath)) {
                    newEntries.add(oldEntries[index]);
                } //enif no jdo variable
            } //next old entry

            final IClasspathEntry[] newEntryList = new IClasspathEntry[newEntries
                    .size()];
            newEntries.toArray(newEntryList);
            project.setRawClasspath(newEntryList, monitor);
        } catch (Exception ex) {
            ok = false;
            LibPlugin.getDefault()
                    .logError(ClasspathHelper.class, ex);
        }

        return ok;
    } //removeClasspathEntry

    
    
    
}//ClasspathHelper
