package net.sourceforge.devtool.lib;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.osgi.framework.BundleContext;

/**
 * The main plugin class to be used in the desktop.
 */
public class LibPlugin extends GenericUIPlugin {

	public static final String IMGPATH_ERROR_IMAGE = "images/error_16x16.png";

	public static final String PLUGIN_ID = "net.sourceforge.devtool.lib";
	public static final String EXTENSIONPOINT_EDITORPAGES = "editorpages";

	// The shared instance.
	private static LibPlugin plugin;
	// Resource bundle.
	private ResourceBundle resourceBundle;

	/**
	 * The constructor.
	 */
	public LibPlugin() {
		super();
		init();
	}

	@Override
	public String getPluginID() {
		return PLUGIN_ID;
	}

	/**
     *  
     */
	protected void init() {
		plugin = this;
		try {
			resourceBundle = ResourceBundle.getBundle(PLUGIN_ID
					+ ".LibPluginResources");
		} catch (MissingResourceException x) {
			resourceBundle = null;
		}
	}

	/**
	 * This method is called upon plug-in activation
	 */
	@Override
	public void start(BundleContext context) throws Exception {
		super.start(context);
	}

	/**
	 * This method is called when the plug-in is stopped
	 */
	@Override
	public void stop(BundleContext context) throws Exception {
		super.stop(context);
	}

	/**
	 * Returns the shared instance.
	 */
	public static LibPlugin getDefault() {
		return plugin;
	}

	/**
	 * Returns the workspace instance.
	 */
	public static IWorkspace getWorkspace() {
		return ResourcesPlugin.getWorkspace();
	}

	/**
	 * Returns the string from the plugin's resource bundle, or 'key' if not
	 * found.
	 */
	public static String getResourceString(String key) {
		ResourceBundle bundle = LibPlugin.getDefault().getResourceBundle();
		try {
			return bundle.getString(key);
		} catch (MissingResourceException e) {
			return key;
		}
	}

	/**
	 * Returns the plugin's resource bundle,
	 */
	public ResourceBundle getResourceBundle() {
		return resourceBundle;
	}
}
