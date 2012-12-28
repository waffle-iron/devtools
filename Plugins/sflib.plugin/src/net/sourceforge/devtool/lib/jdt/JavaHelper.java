/*
 * Created on 30.04.2004
 * 
 * To change the template for this generated file go to Window - Preferences -
 * Java - Code Generation - Code and Comments
 */
package net.sourceforge.devtool.lib.jdt;

/**
 * @author tmichel
 * 
 * To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Generation - Code and Comments
 */
public class JavaHelper {

    public static String getPackageName(final String fullClassName) {
        String packageName = null;
        if (fullClassName != null) {
            final int pos = fullClassName.lastIndexOf('.');
            if (pos > -1) {
                packageName = fullClassName.substring(0, pos);
            }
        }
        return packageName;
    }
    public static String getSimpleClassName(final String fullClassName) {
        String className = null;
        if (fullClassName != null) {
            final int pos = fullClassName.lastIndexOf('.');
            if (pos > -1) {
                className = fullClassName.substring(pos+1);
            }
        }
        return className;
    }

}