package net.sourceforge.devtool.lib.gui.dialogs;

import org.eclipse.core.runtime.IPluginDescriptor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.Plugin;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.swt.widgets.Shell;

/**
 * @author michel
 *
 *History of Changes:
 *
 *  $Log: ErrorMessage.java,v $
 *  Revision 1.1  2005/04/03 10:38:50  tmichel
 *  new structure as a mix of j2ee best praxis and maven
 *
 *  Revision 1.1  2004/04/18 14:41:30  tmichel
 *  solving of dependency confilicts
 *
 *  Revision 1.2  2004/04/06 10:30:35  tmichel
 *  detail section at error dialog  added
 *
 *  Revision 1.1  2003/07/13 22:38:32  tmichel
 *  structure changed:
 *  - lib for core functions
 *  - support plugin
 *  - wizard plugin
 *
 *  Revision 1.1  2003/04/06 18:54:11  tmichel
 *  new version without lib plugin
 *
 *  Revision 1.1  2003/02/09 19:34:31  tmichel
 *  new features for images and xml processing added
 *
 * 
 */
public class ErrorMessage {

	public ErrorMessage(
	    final Plugin plugin,
		String errorTitle,
		final Class callerClass,
		final Exception exception){ 
	    // read informations
	    final IPluginDescriptor descriptor = plugin.getDescriptor();
	    final String id  = descriptor.getUniqueIdentifier();
	    // create Error status 
	    final MultiStatus errorStatus = new MultiStatus(id,IStatus.ERROR, exception.getLocalizedMessage(),null); 
	    // add details
	    final String pluginName = descriptor.getLabel();
	    final String version = descriptor.getVersionIdentifier().toString();
	    final String providerName = descriptor.getProviderName();
	    final String className = callerClass.getName();
	    String message = "Plugin-Id: "+id;
	    appendMessage(id, errorStatus, message);
	    message = "Plugin-Version: "+version;
	    appendMessage(id,errorStatus,message);
	    message = "Plugin-Name: "+pluginName;
	    appendMessage(id,errorStatus,message);
	    message = "Plugin-Provider: "+providerName;
	    appendMessage(id,errorStatus,message);
	    message = "Class: "+className;
	    appendMessage(id,errorStatus,message);
	    message = "Exception: "+exception.toString();
	    appendMessage(id,errorStatus,message);
	    plugin.getLog().log(errorStatus);
	    // FIXME must be localized
		if( errorTitle == null){
		    errorTitle = "Error";
		}
	    ErrorDialog.openError(
			(Shell) null,
			errorTitle,
			"There was a error occur."
			+"\n Select Details >> for more information."
			+"\n See the Error Log for more information.",
			errorStatus);
	} //ErrorMessage

    /**
     * @param id
     * @param errorStatus
     * @param message
     */
    protected void appendMessage(final String id, final MultiStatus errorStatus, String message) {
        errorStatus.add(new Status(IStatus.ERROR, id,IStatus.ERROR, message, null ));
    }

} // ErrorMessage
