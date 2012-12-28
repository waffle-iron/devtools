/*
 * Created on 23.04.2004
 * 
 * To change the template for this generated file go to Window - Preferences -
 * Java - Code Generation - Code and Comments
 */
package net.sourceforge.devtool.lib.helper;

import org.eclipse.core.resources.ICommand;

/**
 * @author tmichel
 * 
 * To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Generation - Code and Comments
 */
public class BuilderHelper {

    public static boolean containsBuilder(final ICommand[] commands,
            final String builderID) {
        for (int i = 0; i < commands.length; i++) {
            if (commands[i].getBuilderName().equals(builderID)) { return true; }
        }// next command
        return false;
    }

}// BuilderHelper
