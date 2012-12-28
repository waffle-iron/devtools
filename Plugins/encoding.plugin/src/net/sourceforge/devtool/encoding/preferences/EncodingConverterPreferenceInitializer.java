/*
 * Created on 30.08.2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package net.sourceforge.devtool.encoding.preferences;

import net.sourceforge.devtool.encoding.EncodingPlugin;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;

/**
 * @author tmichel
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class EncodingConverterPreferenceInitializer extends
		AbstractPreferenceInitializer implements IEncodingConverterPreferenceConstants {

	/* (non-Javadoc)
	 * @see org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer#initializeDefaultPreferences()
	 */
	public void initializeDefaultPreferences() {
		EncodingPlugin.getDefault().getPluginPreferences().setDefault(P_JSP_FILES, true);
		EncodingPlugin.getDefault().getPluginPreferences().setDefault(P_XML_FILES, true);
		EncodingPlugin.getDefault().getPluginPreferences().setDefault(P_CSS_FILES, true);
		EncodingPlugin.getDefault().getPluginPreferences().setDefault(P_DTD_FILES, true);
		EncodingPlugin.getDefault().getPluginPreferences().setDefault(P_JAVA_FILES, false);
		EncodingPlugin.getDefault().getPluginPreferences().setDefault(P_ALL_FILES, false);
	}

}
