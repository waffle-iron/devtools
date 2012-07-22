package com.github.funthomas424242.eclipseconfigurator;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 */
public class Activator extends AbstractUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "eclipseconfigurator"; //$NON-NLS-1$

	public static String getPluginId() {
		return PLUGIN_ID;
	}

	// The shared instance
	private static Activator plugin;

	/**
	 * The constructor
	 */
	public Activator() {
	}

	/**
	 * 
	 * @param message
	 *            error text
	 * @param status
	 *            IStatus.ERROR or IStatus.WARNING or IStatus.INFO
	 */
	public void log(final Throwable ex, final String message, final int status) {
		getDefault().getLog().log(
				new Status(status, getPluginId(), message, ex));
	}

	public static void logInfo(final String message) {
		getDefault().log(null, message, IStatus.INFO);
	}

	public static void logWarning(final Throwable ex, final String message) {
		getDefault().log(ex, message, IStatus.WARNING);
	}

	public static void logError(final Throwable ex, final String message) {
		getDefault().log(ex, message, IStatus.ERROR);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext
	 * )
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext
	 * )
	 */
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 * 
	 * @return the shared instance
	 */
	public static Activator getDefault() {
		return plugin;
	}

	/**
	 * Returns an image descriptor for the image file at the given plug-in
	 * relative path
	 * 
	 * @param path
	 *            the path
	 * @return the image descriptor
	 */
	public static ImageDescriptor getImageDescriptor(String path) {
		return imageDescriptorFromPlugin(PLUGIN_ID, path);
	}

}
