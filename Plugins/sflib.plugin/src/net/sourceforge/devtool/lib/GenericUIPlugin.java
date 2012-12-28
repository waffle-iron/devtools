/*
 * Created on 23.04.2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package net.sourceforge.devtool.lib;

import org.eclipse.core.runtime.ILog;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.ui.plugin.AbstractUIPlugin;

/**
 * @author tmichel
 * 
 *         To change the template for this generated type comment go to Window -
 *         Preferences - Java - Code Generation - Code and Comments
 */
public abstract class GenericUIPlugin extends AbstractUIPlugin {

	/**
     * 
     */
	public GenericUIPlugin() {
		super();
	}

	protected void log(final Class<?> clazz, final int severity,
			final String message) {
		log(clazz, severity, IStatus.OK, message, null);
	}
	protected void log(final Class<?> clazz, final int severity,
			final Exception ex) {
		log(clazz, severity, -1, ex.getLocalizedMessage(), ex);

	}
	protected void log(final Class<?> clazz, final int severity,
			final int internalCode, final String message, final Exception ex) {
		System.out.print("log:");
		System.out.flush();
		System.out.println("[" + clazz.getName() + "]" + message);
		final IStatus status = new Status(severity, getPluginID(),
				internalCode, message, ex);
		final ILog log = this.getLog();
		if (log != null) {
			log.log(status);
		}
	}
	public void logError(final Class<?> clazz, final Exception ex) {
		log(clazz, IStatus.ERROR, ex);
	}
	public void logWarning(final Class<?> clazz, final Exception ex) {
		log(clazz, IStatus.WARNING, ex);
	}
	public void logInfo(final Class<?> clazz, final String message) {
		log(clazz, IStatus.INFO, message);
	}

	public abstract String getPluginID();

}
