package com.github.funthomas424242.eclipseconfigurator.preferences;

import org.eclipse.core.internal.preferences.AbstractScope;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.core.runtime.preferences.IScopeContext;

public class WorkgroupPreferenceScope extends AbstractScope {

	public static final String SCOPE = "workgroup"; //$NON-NLS-1$

	public static final IScopeContext INSTANCE = new WorkgroupPreferenceScope();

	/**
	 * Create and return a new instance scope instance.
	 * 
	 * @deprecated call <code>InstanceScope.INSTANCE</code> instead.
	 */
	@Deprecated
	public WorkgroupPreferenceScope() {
		super();
	}

	/*
	 * @see org.eclipse.core.runtime.preferences.IScopeContext#getLocation()
	 */
	public IPath getLocation() {
		// Return null. The instance location usually corresponds to the state
		// location of the bundle and we don't know what bundle we are dealing
		// with.
		return null;
	}

	/*
	 * @see org.eclipse.core.runtime.preferences.IScopeContext#getName()
	 */
	public String getName() {
		return SCOPE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.core.runtime.preferences.IScopeContext#getNode(java.lang.
	 * String)
	 */
	public IEclipsePreferences getNode(String qualifier) {
		return super.getNode(qualifier);
	}
}
