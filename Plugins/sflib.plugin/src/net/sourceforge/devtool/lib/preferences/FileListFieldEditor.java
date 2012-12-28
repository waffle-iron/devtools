package net.sourceforge.devtool.lib.preferences;

import org.eclipse.jface.preference.FieldEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.List;

public abstract class FileListFieldEditor extends FieldEditor {

	protected FileListControlPanel controlPanel = null;
	protected List list = null;

	protected FileListFieldEditor(
		String name,
		String labelText,
		Composite parent) {
			init(name,labelText);
			init(parent);
			final SelectionListener selectionListener = getSelectionListener();
		controlPanel = new FileListControlPanel( parent,  SWT.NULL,list, selectionListener);
		createControl(parent);
		controlPanel.selectionChanged();
	}


	public void init(Composite parent) {
		list =new List(
					parent,
					SWT.BORDER | SWT.SINGLE | SWT.V_SCROLL | SWT.H_SCROLL);
			list.setFont(parent.getFont());
			list.addDisposeListener(new DisposeListener() {
				public void widgetDisposed(DisposeEvent event) {
					list = null;
				}
			});
	}


	protected void addPressed() {
		setPresentsDefaultValue(false);
		String[] input = getNewInputObjects();

		if (input != null) {
			int index = list.getSelectionIndex();
			for (int i = 0; i < input.length; i++) {
				if (index >= 0) {
					list.add(input[i], index + i + 1);
				} else {
					list.add(input[i],  i);
				}
			}
			controlPanel.selectionChanged();
		}
	}
	
	protected SelectionListener getSelectionListener() {
		SelectionListener selectionListener = null;
			selectionListener = new SelectionAdapter() {
				public void widgetSelected(SelectionEvent event) {
					addPressed();
				}
			};
	return selectionListener;

	}

	protected void doLoad() {
		if (list != null) {
			String s = getPreferenceStore().getString(getPreferenceName());
			String[] array = parseString(s);
			for (int i = 0; i < array.length; i++) {
				list.add(array[i]);
			}
			controlPanel.selectionChanged();
		}
	}

	protected void doLoadDefault() {
		if (list != null) {
			list.removeAll();
			String s =
				getPreferenceStore().getDefaultString(getPreferenceName());
			String[] array = parseString(s);
			for (int i = 0; i < array.length; i++) {
				list.add(array[i]);
			}
			controlPanel.selectionChanged();
		}
	} 

	protected void doStore() {
		String s = createList(list.getItems());
		if (s != null)
			getPreferenceStore().setValue(getPreferenceName(), s);
	}

	protected void doFillIntoGrid(Composite parent, int numColumns) {
		Control control = getLabelControl(parent);
		GridData gd = new GridData();
		gd.horizontalSpan = numColumns;
		control.setLayoutData(gd);

		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.verticalAlignment = GridData.FILL;
		gd.horizontalSpan = numColumns - 1;
		gd.grabExcessHorizontalSpace = true;
		list.setLayoutData(gd);

		gd = new GridData();
		gd.verticalAlignment = GridData.BEGINNING;
		this.controlPanel.setLayoutData(gd);
	}

	protected void adjustForNumColumns(int numColumns) {
		Control control = getLabelControl();
		((GridData)control.getLayoutData()).horizontalSpan = numColumns;
		((GridData)list.getLayoutData()).horizontalSpan = numColumns - 1;
	}


	public int getNumberOfControls() {
		return 2;
	}

	public void setFocus() {
		if (list != null) {
			list.setFocus();
		}
	}
 
	public void setEnabled(boolean enabled, Composite parent) {
		super.setEnabled(enabled, parent);
		list.setEnabled(enabled);
		controlPanel.setEnabled(enabled);
	}

	protected abstract String createList(String[] items);
	protected abstract String[] getNewInputObjects();
	protected abstract String[] parseString(String stringList);
}
