/*
 * Created on 30.08.2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package encoding.plugin.preferences;

import org.eclipse.jface.preference.IPreferenceStore;

import encoding.plugin.Activator;

/**
 * @author tmichel
 * 
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class EncodingPreferencePageHelper {

	public String getEncodingConverterFilePattern() {
		String pattern = ""; //$NON-NLS-1$
		IPreferenceStore store = Activator.getDefault().getPreferenceStore();
		if (store.getBoolean(EncodingConverterPreferenceConstants.P_ALL_FILES)) {
			pattern = ".*\\..*"; //$NON-NLS-1$
		} else {
			int i = 0;
			if (store
					.getBoolean(EncodingConverterPreferenceConstants.P_JSP_FILES)) {
				pattern += ".*\\.jsp"; //$NON-NLS-1$
				i++;
			}
			if (store
					.getBoolean(EncodingConverterPreferenceConstants.P_CSS_FILES)) {
				if (i > 0) {
					pattern += "|";} //$NON-NLS-1$
				pattern += ".*\\.css"; //$NON-NLS-1$
				i++;
			}
			if (store
					.getBoolean(EncodingConverterPreferenceConstants.P_DTD_FILES)) {
				if (i > 0) {
					pattern += "|";} //$NON-NLS-1$
				pattern += ".*\\.dtd"; //$NON-NLS-1$
				i++;
			}
			if (store
					.getBoolean(EncodingConverterPreferenceConstants.P_JAVA_FILES)) {
				if (i > 0) {
					pattern += "|";} //$NON-NLS-1$
				pattern += ".*\\.java"; //$NON-NLS-1$
				i++;
			}
			if (store
					.getBoolean(EncodingConverterPreferenceConstants.P_XML_FILES)) {
				if (i > 0) {
					pattern += "|";} //$NON-NLS-1$
				pattern += ".*\\.xml"; //$NON-NLS-1$
				i++;
			}
			if (store
					.getBoolean(EncodingConverterPreferenceConstants.P_TXT_FILES)) {
				if (i > 0) {
					pattern += "|";} //$NON-NLS-1$
				pattern += ".*\\.txt"; //$NON-NLS-1$
				i++;
			}

		}
		return pattern;
	}

}
