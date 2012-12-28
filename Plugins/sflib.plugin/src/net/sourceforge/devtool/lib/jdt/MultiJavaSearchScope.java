/*
 * Created on 25.04.2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package net.sourceforge.devtool.lib.jdt;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import org.eclipse.core.runtime.IPath;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.search.IJavaSearchScope;


/**
 * @author tmichel
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class MultiJavaSearchScope implements IJavaSearchScope {

    protected IJavaSearchScope[] scopes=null;
    
    public MultiJavaSearchScope(final IJavaSearchScope[] scopes){
        this.scopes = scopes;
    }
    
    
    /* (non-Javadoc)
     * @see org.eclipse.jdt.core.search.IJavaSearchScope#encloses(org.eclipse.jdt.core.IJavaElement)
     */
    public boolean encloses(IJavaElement element) {
        for(int i=0; i<scopes.length;i++){
            if(scopes[i].encloses(element)){
                return true;
            }
        }
        return false;
    }
    /* (non-Javadoc)
     * @see org.eclipse.jdt.core.search.IJavaSearchScope#encloses(java.lang.String)
     */
    public boolean encloses(String resourcePath) {
        for(int i=0; i<scopes.length;i++){
            if(scopes[i].encloses(resourcePath)){
                return true;
            }
        }
        return false;
    }
    /* (non-Javadoc)
     * @see org.eclipse.jdt.core.search.IJavaSearchScope#enclosingProjectsAndJars()
     */
    public IPath[] enclosingProjectsAndJars() {
        final ArrayList pathList = new ArrayList();
        for(int i=0; i<scopes.length;i++){
            final IPath[] sublist = scopes[i].enclosingProjectsAndJars();
            final Collection col = Arrays.asList(sublist);
            pathList.add(col);
        }
        final IPath[] path = new IPath[ pathList.size()];
        pathList.toArray(path);
        return path;
    }
    /* (non-Javadoc)
     * @see org.eclipse.jdt.core.search.IJavaSearchScope#includesBinaries()
     */
    public boolean includesBinaries() {
        for(int i=0; i<scopes.length;i++){
            if(scopes[i].includesBinaries()){
                return true;
            }
        }
        return false;
    }
    /* (non-Javadoc)
     * @see org.eclipse.jdt.core.search.IJavaSearchScope#includesClasspaths()
     */
    public boolean includesClasspaths() {
        for(int i=0; i<scopes.length;i++){
            if(scopes[i].includesClasspaths()){
                return true;
            }
        }
        return false;
    }
    /* (non-Javadoc)
     * @see org.eclipse.jdt.core.search.IJavaSearchScope#setIncludesBinaries(boolean)
     */
    public void setIncludesBinaries(boolean includesBinaries) {
        // TODO Auto-generated method stub

    }
    /* (non-Javadoc)
     * @see org.eclipse.jdt.core.search.IJavaSearchScope#setIncludesClasspaths(boolean)
     */
    public void setIncludesClasspaths(boolean includesClasspaths) {
        // TODO Auto-generated method stub

    }
}
