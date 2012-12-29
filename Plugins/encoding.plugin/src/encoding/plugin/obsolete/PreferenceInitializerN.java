package encoding.plugin.obsolete;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;

import encoding.plugin.Activator;

/**
 * Class used to initialize default preference values.
 */
public class PreferenceInitializerN extends AbstractPreferenceInitializer {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer#
	 * initializeDefaultPreferences()
	 */
	@Override
	public void initializeDefaultPreferences() {
		IPreferenceStore store = Activator.getDefault().getPreferenceStore();
		store.setDefault(PreferenceConstantsN.P_BOOLEAN, true);
		store.setDefault(PreferenceConstantsN.P_CHOICE, "choice2");
		store.setDefault(PreferenceConstantsN.P_STRING, "Default value");
	}

}
