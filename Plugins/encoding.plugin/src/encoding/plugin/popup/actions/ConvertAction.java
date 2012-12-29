package encoding.plugin.popup.actions;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Stack;
import java.util.logging.Level;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IActionDelegate;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

import encoding.plugin.encoding.EncodingConverter;
import encoding.plugin.preferences.EncodingPreferencePageHelper;

public class ConvertAction implements IObjectActionDelegate {

	protected Shell shell;

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
	@Override
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		shell = targetPart.getSite().getShell();
	}

	/**
	 * @see IActionDelegate#selectionChanged(IAction, ISelection)
	 */
	@Override
	public void selectionChanged(IAction action, ISelection selection) {
		this.selection = selection;
	}

	/**
	 * @see IActionDelegate#run(IAction)
	 */
	@Override
	public void run(IAction action) {
		if (selection instanceof IStructuredSelection) {
			final IStructuredSelection structSelection = (IStructuredSelection) selection;

			final Stack<IAdaptable> resourceStack = new Stack<IAdaptable>();
			for (Iterator<?> elements = structSelection.iterator(); elements
					.hasNext();) {

				final Object obj = elements.next();

				if (obj instanceof IFile || obj instanceof IFolder) {
					resourceStack.push((IAdaptable) obj);
				}

			} // next contained element

			final EncodingConverter converter = new EncodingConverter() {

				/*
				 * (non-Javadoc)
				 * 
				 * @see net.sourceforge.devtool.lib.encoding.EncodingConverter#
				 * shouldConvert(java.io.File, java.lang.String)
				 */
				@Override
				protected boolean shouldConvert(File file, String srcCharset,
						String trgCharset) {
					boolean doConvert = super.shouldConvert(file, srcCharset,
							trgCharset);
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
		}

	}

}