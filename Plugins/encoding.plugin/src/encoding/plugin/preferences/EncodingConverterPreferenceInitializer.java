/*
 * Created on 30.08.2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package encoding.plugin.preferences;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;

import encoding.plugin.Activator;

/**
 * @author tmichel
 * 
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class EncodingConverterPreferenceInitializer
		extends
			AbstractPreferenceInitializer

{

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer#
	 * initializeDefaultPreferences()
	 */
	@Override
	public void initializeDefaultPreferences() {

		IPreferenceStore store = Activator.getDefault().getPreferenceStore();
		store.setDefault(EncodingConverterPreferenceConstants.P_JSP_FILES, true);
		store.setDefault(EncodingConverterPreferenceConstants.P_XML_FILES, true);
		store.setDefault(EncodingConverterPreferenceConstants.P_CSS_FILES, true);
		store.setDefault(EncodingConverterPreferenceConstants.P_DTD_FILES, true);
		store.setDefault(EncodingConverterPreferenceConstants.P_TXT_FILES, true);
		store.setDefault(EncodingConverterPreferenceConstants.P_JAVA_FILES,
				false);
		store.setDefault(EncodingConverterPreferenceConstants.P_ALL_FILES,
				false);
	}

}
