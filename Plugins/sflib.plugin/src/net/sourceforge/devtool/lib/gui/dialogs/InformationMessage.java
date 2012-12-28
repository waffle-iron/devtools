package net.sourceforge.devtool.lib.gui.dialogs;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Shell;

/**
 * @author michel
 *
 *History of Changes:
 *
 *  $Log: InformationMessage.java,v $
 *  Revision 1.1  2005/04/03 10:38:50  tmichel
 *  new structure as a mix of j2ee best praxis and maven
 *
 *  Revision 1.1  2004/04/18 14:41:30  tmichel
 *  solving of dependency confilicts
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
public class InformationMessage extends MessageDialog {

	/**
	 * Constructor for InformationMessage.
	 * @param parentShell
	 * @param dialogTitle
	 * @param dialogTitleImage
	 * @param dialogMessage
	 * @param dialogImageType
	 * @param dialogButtonLabels
	 * @param defaultIndex
	 */
	public InformationMessage(
		final Shell parentShell,
		final String dialogMessage
		) {
		super(
			parentShell,
			"Information",
			null,
			dialogMessage,
			INFORMATION,
			new String[]{ "OK"},
			1);
	}//InformationMessage(Shell,String)

}//InformationMessage
