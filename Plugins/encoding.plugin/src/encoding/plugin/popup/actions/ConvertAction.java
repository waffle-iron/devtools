package encoding.plugin.popup.actions;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Stack;
import java.util.logging.Level;


import org.eclipse.core.resources.IResource;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IActionDelegate;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

import encoding.plugin.encoding.EncodingConverter;
import encoding.plugin.preferences.EncodingPreferencePageHelper;

public class ConvertAction implements IObjectActionDelegate {

	protected ISelection selection = null;

	/**
	 * Constructor for Action1.
	 */
	public ConvertAction() {
		super();
	}

	/**
	 * @see IObjectActionDelegate#setActivePart(IAction, IWorkbenchPart)
	 */
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
	}

	/**
	 * @see IActionDelegate#run(IAction)
	 */
	public void run(IAction action) {
		if (selection instanceof IStructuredSelection) {
			final IStructuredSelection sel = (IStructuredSelection) selection;

			final Stack resourceStack = new Stack();
			Object obj = null;
			for (Iterator elem = sel.iterator(); elem.hasNext();) {

				obj = elem.next();

				if (obj instanceof IResource) {
					final IResource resource = (IResource) obj;
					final String fullPath = resource.getLocation().toOSString();
					resourceStack.push(fullPath);
				} //endif( instanceof IProject)

			} //next selected element

			final EncodingConverter converter = new EncodingConverter() {

				/*
				 * (non-Javadoc)
				 * 
				 * @see net.sourceforge.devtool.lib.encoding.EncodingConverter#shouldConvert(java.io.File,
				 *      java.lang.String)
				 */
				protected boolean shouldConvert(File file, String trgCharset) {
					boolean doConvert = super.shouldConvert(file, trgCharset);
					try {
						final String filePath = file.getCanonicalPath();
						doConvert = filePath
								.matches((new EncodingPreferencePageHelper())
										.getEncodingConverterFilePattern());
					} catch (IOException e) {
						logger.log(Level.SEVERE, e.getLocalizedMessage());
					}
					return doConvert;
				}
			};
			converter.convertFilesystemEntries2Charset(resourceStack, "UTF-8"); //$NON-NLS-1$
		} // endif( instanceof IStructuredSelection )

	}

	/**
	 * @see IActionDelegate#selectionChanged(IAction, ISelection)
	 */
	public void selectionChanged(IAction action, ISelection selection) {
		this.selection = selection;
	}

}