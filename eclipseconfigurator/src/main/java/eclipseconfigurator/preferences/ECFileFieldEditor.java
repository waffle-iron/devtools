package eclipseconfigurator.preferences;

import org.eclipse.jface.preference.FileFieldEditor;
import org.eclipse.swt.widgets.Composite;

public class ECFileFieldEditor extends FileFieldEditor {

	/**
	 * 
	 */
	public ECFileFieldEditor() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param name
	 * @param labelText
	 * @param enforceAbsolute
	 * @param parent
	 */
	public ECFileFieldEditor(String name, String labelText,
			boolean enforceAbsolute, Composite parent) {
		super(name, labelText, enforceAbsolute, parent);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param name
	 * @param labelText
	 * @param enforceAbsolute
	 * @param validationStrategy
	 * @param parent
	 */
	public ECFileFieldEditor(String name, String labelText,
			boolean enforceAbsolute, int validationStrategy, Composite parent) {
		super(name, labelText, enforceAbsolute, validationStrategy, parent);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param name
	 * @param labelText
	 * @param parent
	 */
	public ECFileFieldEditor(String name, String labelText, Composite parent) {
		super(name, labelText, parent);
		// TODO Auto-generated constructor stub
	}

	// @Override
	// protected boolean doCheckState() {
	// // TODO Auto-generated method stub
	// // return super.doCheckState();
	// return true;
	// }

	@Override
	protected boolean checkState() {
		// TODO Auto-generated method stub
		return true;
	}

}
