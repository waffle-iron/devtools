/*
 * Created on 17.04.2004
 * 
 * To change the template for this generated file go to Window - Preferences -
 * Java - Code Generation - Code and Comments
 */
package net.sourceforge.devtool.lib.helper;

import net.sourceforge.devtool.lib.jdt.MultiJavaSearchScope;

import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.search.IJavaSearchScope;
import org.eclipse.jdt.core.search.SearchEngine;
import org.eclipse.jdt.ui.IJavaElementSearchConstants;
import org.eclipse.jdt.ui.JavaUI;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.dialogs.SelectionDialog;

/**
 * @author tmichel
 * 
 * To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Generation - Code and Comments
 */
public class DialogHelper {

    public static IType[] seachTypesInWorkspace() throws JavaModelException {
        IType[] types = new IType[0];
        SelectionDialog dialog = JavaUI.createTypeDialog(new Shell(),
                new ProgressMonitorDialog(new Shell()), SearchEngine
                        .createWorkspaceScope(),
                IJavaElementSearchConstants.CONSIDER_CLASSES, false);
        dialog.setTitle("Choose a Class");
        dialog.setMessage("We could only lookup in the workspace for classes");
        if (dialog.open() != IDialogConstants.CANCEL_ID) {
            Object[] objs = dialog.getResult();
            if (objs != null && objs.length > 0) {
                types = new IType[objs.length];
                //types = (IType[]) objs;
                for (int i = 0; i < objs.length; i++) {
                    types[i] = (IType) objs[i];
                }
            } //endif more as 0 types
        } //endif not canceled

        return types;
    } //selectType()

    public static IType[] seachTypesInWorkspaceAndElements(
            final IJavaElement[] elements) throws JavaModelException {
        final IJavaSearchScope[] scopes = new IJavaSearchScope[2];
        scopes[0] = SearchEngine.createWorkspaceScope();
        scopes[1] = SearchEngine.createJavaSearchScope(elements);
        final MultiJavaSearchScope scope = new MultiJavaSearchScope(scopes);
        // open dialog for searching types in multi scope
        SelectionDialog dialog = JavaUI.createTypeDialog(new Shell(),
                new ProgressMonitorDialog(new Shell()), scope,
                IJavaElementSearchConstants.CONSIDER_CLASSES, false);
        dialog.setTitle("Choose a Class");
//        dialog.setMessage("We could only lookup in the workspace for classes");
        IType[] types = new IType[0];
        if (dialog.open() != IDialogConstants.CANCEL_ID) {
            Object[] objs = dialog.getResult();
            if (objs != null && objs.length > 0) {
                types = new IType[objs.length];
                  for (int i = 0; i < objs.length; i++) {
                    types[i] = (IType) objs[i];
                }
            } //endif more as 0 types
        } //endif not canceled

        return types;
    } //selectType()

}