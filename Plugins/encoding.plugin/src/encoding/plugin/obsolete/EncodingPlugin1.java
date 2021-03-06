/*
 * $Id: EncodingPlugin.java,v 1.7 2004/11/27 21:22:40 tmichel Exp $
 *
 */
package encoding.plugin.obsolete;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * This is a description of the class. The first line of the description should
 * show a summary of the class, and subsequent lines a detailed description
 * 
 * @see net.sourceforge.devtool.encoding
 * 
 * @version: $Revision: 1.7 $
 * @author: $Author: tmichel $
 * @date: $Date: 2004/11/27 21:22:40 $
 */
public class EncodingPlugin1 extends AbstractUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "net.sourceforge.devtool.encoding"; //$NON-NLS-1$

	public static String getPluginId() {
		return PLUGIN_ID;
	}

	// The shared instance.
	private static EncodingPlugin1 plugin;

	// Resource bundle.
	private ResourceBundle resourceBundle;

	/**
	 * The constructor.
	 */
	public EncodingPlugin1() {
		// super();
		// plugin = this;
		// try {
		// resourceBundle = ResourceBundle
		//					.getBundle("net.sourceforge.devtool.encoding.EncodingPluginResources"); //$NON-NLS-1$
		// } catch (MissingResourceException x) {
		// resourceBundle = null;
		// }
	}

	/**
	 * This method is called upon plug-in activation
	 */
	@Override
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
	}

	/**
	 * This method is called when the plug-in is stopped
	 */
	@Override
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}
	/**
	 * Returns the shared instance.
	 */
	public static EncodingPlugin1 getDefault() {
		return plugin;
	}

	/**
	 * Returns the string from the plugin's resource bundle, or 'key' if not
	 * found.
	 */
	public static String getResourceString(String key) {
		ResourceBundle bundle = EncodingPlugin1.getDefault()
				.getResourceBundle();
		try {
			return (bundle != null) ? bundle.getString(key) : key;
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