/*
 * Created on 04.05.2004
 * 
 * To change the template for this generated file go to Window - Preferences -
 * Java - Code Generation - Code and Comments
 */
package net.sourceforge.devtool.lib.editors;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;

import net.sourceforge.devtool.lib.LibPlugin;
import net.sourceforge.devtool.lib.pattern.observer.ISubject;
import net.sourceforge.devtool.lib.pattern.observer.Subject;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IPluginRegistry;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Platform;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.MultiPageEditorPart;

/**
 * @author tmichel
 * 
 *         To change the template for this generated type comment go to Window -
 *         Preferences - Java - Code Generation - Code and Comments
 */
public class GenericMultipageEditor extends MultiPageEditorPart {

	// protected Object editorModel = null;
	protected final Map modelMap = new Hashtable();

	@Override
	protected void createPages() {
		// TODO API has changed
		// createEditorPagesFromExtentionPoint(LibPlugin.PLUGIN_ID,
		// LibPlugin.EXTENSIONPOINT_EDITORPAGES);
		// final IEditorInput editorInput = getEditorInput();
		// try {
		//
		// final IPath resourcePath = editorInput.getPersistable()
		// getStorage().getFullPath();
		// if (resourcePath != null) {
		// final IWorkspace workSpace = ResourcesPlugin.getWorkspace();
		// workSpace
		// .addResourceChangeListener(new IResourceChangeListener() {
		//
		// /*
		// * (non-Javadoc)
		// *
		// * @see
		// * org.eclipse.core.resources.IResourceChangeListener
		// * #resourceChanged(org.eclipse.core.resources.
		// * IResourceChangeEvent)
		// */
		// @Override
		// public void resourceChanged(
		// IResourceChangeEvent event) {
		// IResourceDelta delta = event.getDelta();
		// delta = delta.findMember(resourcePath);
		// if (delta == null)
		// return;
		// if (delta.getKind() != IResourceDelta.REMOVED)
		// return;
		// closeEditor();
		// }
		// });
		// }
		// } catch (CoreException ex) {
		// LibPlugin.getDefault().logError(getClass(), ex);
		// }
	}

	protected void closeEditor() {
		getSite().getPage().closeEditor(this, false);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.ISaveablePart#doSave(org.eclipse.core.runtime.IProgressMonitor
	 * )
	 */
	@Override
	public void doSave(IProgressMonitor monitor) {
		for (int i = 0; i < getPageCount(); i++) {
			final IEditorPart editor = getEditor(i);
			if (editor.isDirty()) {
				editor.doSave(monitor);
			}
		}// next editor
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.ISaveablePart#doSaveAs()
	 */
	@Override
	public void doSaveAs() {
		for (int i = 0; i < getPageCount(); i++) {
			final IEditorPart editor = getEditor(i);
			if (editor.isDirty()) {
				editor.doSaveAs();
			}
		}// next editor
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.ISaveablePart#isSaveAsAllowed()
	 */
	@Override
	public boolean isSaveAsAllowed() {
		boolean isAllowed = true;
		for (int i = 0; i < getPageCount(); i++) {
			isAllowed = isAllowed && getEditor(i).isSaveAsAllowed();
		}
		return isAllowed;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.part.MultiPageEditorPart#pageChange(int)
	 */
	// protected void pageChange(int newPageIndex) {
	// super.pageChange(newPageIndex);
	// final IGenericEditorPart editor = (IGenericEditorPart)
	// getEditor(newPageIndex);
	// editor.updateModel(editorModel);
	// }
	protected void createEditorPagesFromExtentionPoint(final String pluginId,
			final String extensionPointId) {
		final String editorId = getConfigurationElement().getAttribute("id");
		if (pluginId == null || extensionPointId == null || editorId == null)
			return;
		final IPluginRegistry pluginRegistry = Platform.getPluginRegistry();
		final IExtensionPoint extensionPoint = pluginRegistry
				.getExtensionPoint(pluginId, extensionPointId);
		final IExtension[] extensions = extensionPoint.getExtensions();
		//
		final ArrayList configElementList = new ArrayList();
		for (int ex = 0; ex < extensions.length; ex++) {
			final IConfigurationElement[] configElements = extensions[ex]
					.getConfigurationElements();
			for (int i = 0; i < configElements.length; i++) {
				if ("editor".equals(configElements[i].getName())
						&& editorId
								.equals(configElements[i].getAttribute("id"))) {
					configElementList.add(extensions[ex]
							.getConfigurationElements());
				}
			}// next configElement
		}// next extension
		for (int i = 0; i < configElementList.size(); i++) {
			final IConfigurationElement[] configElements = (IConfigurationElement[]) configElementList
					.get(i);
			doAddPageFromConfigElement(configElements);
		}// next extentsion
	}

	/**
	 * @param in
	 * @param configElements
	 */
	private void doAddPageFromConfigElement(
			final IConfigurationElement[] configElements) {
		final IEditorInput in = getEditorInput();
		for (int el = 0; el < configElements.length; el++) {
			if ("editor".equals(configElements[el].getName()))
				continue;
			try {

				final IGenericEditorPart editorPart = (IGenericEditorPart) configElements[el]
						.createExecutableExtension("class");
				// add the model class
				final String modelClassName = configElements[el]
						.getAttribute("modelclass");
				if (modelClassName != null) {
					if (!modelMap.containsKey(modelClassName)) {
						// try {
						// final ISubject model = (ISubject) configElements[el]
						// .createExecutableExtension("modelclass");
						final ISubject model = new Subject();
						modelMap.put(modelClassName, model);
						// } catch (CoreException coreException) {
						// LibPlugin.getDefault().logError(getClass(),
						// coreException);
						// }
					}
					final ISubject model = (ISubject) modelMap
							.get(modelClassName);
					editorPart.setModel(model);
				}

				final int pageIndex = addPage(editorPart, in);
				final String pageName = configElements[el].getAttribute("name");
				setPageText(pageIndex, pageName);
				// final String pageIcon =
				// currentConfigElement.getAttribute("pageicon");
				// final Image image =
				// setPageImage(pageIndex,image);
				// if (editorModel == null && editorPart.supportSharedModel()) {
				// editorModel = editorPart.getModel();
				// }
			} catch (PartInitException e) {
				LibPlugin.getDefault().logError(getClass(), e);
			} catch (CoreException e) {
				LibPlugin.getDefault().logError(getClass(), e);
			}
		}// next configuration element
	}

}