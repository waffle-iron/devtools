/*
 * Created on 19.06.2003
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package net.sourceforge.devtool.lib.preferences;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.List;

/**
 * @author michel
 *
 * To change the template for this generated type comment go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class FileListControlPanel extends Composite {

	 public class WrapSelectionAdapter extends SelectionAdapter{
				protected SelectionListener parentListener = null;
				
				public   WrapSelectionAdapter(final SelectionListener listener){
					this.parentListener = listener;
				}
				public void widgetSelected(SelectionEvent event) {
					parentListener.widgetSelected(event);
					selectionChanged();
				}

			};


	static public final int INVALID_CONTROL = 0;
	static public final int ADD_BUTTON = 1;
	
	protected Button addButton;
	protected Button removeButton;
	protected Button removeAllButton;
	protected Button upButton;
	protected Button downButton;
	protected List list;

	/**
	 * @param parent
	 * @param style
	 */
	public FileListControlPanel(Composite parent, int style, List list,final SelectionListener selectionListener) {
		super(parent, style);
		this.list = list;
		init(selectionListener); 
	}

	protected void init( final SelectionListener addButtonListener) {
		GridLayout layout = new GridLayout();
		layout.marginWidth = 0;
		setLayout(layout);
		createButtons();
		addDisposeListener(new DisposeListener() {
			public void widgetDisposed(DisposeEvent event) {
				addButton = null;
				removeButton = null;
				removeAllButton = null;
				upButton = null;
				downButton = null;
			}
		});
		addButton.addSelectionListener(
			addButtonListener);

		SelectionListener selectionListener = null;
		/**/
		{
			selectionListener = new SelectionAdapter(){
				public void widgetSelected(SelectionEvent event) {
					moveItem(true);
				}
			};
			upButton.addSelectionListener(
				selectionListener);
		} /**/

		/**/
		{
			selectionListener = new SelectionAdapter() {
				public void widgetSelected(SelectionEvent event) {
					moveItem(false);
				}
			};
			downButton.addSelectionListener(
				selectionListener);
		} /**/
		
	
		/**/{
				selectionListener = new SelectionAdapter(){
				public void widgetSelected(SelectionEvent event){
					removePressed();
				}
			};
			removeButton.addSelectionListener( selectionListener);
			}/**/
		/**/{
				selectionListener = new SelectionAdapter(){
				public void widgetSelected(SelectionEvent event){
					removeAllPressed();
				}
			};
			removeAllButton.addSelectionListener(  selectionListener);
			}/**/
	
		/**/{
				selectionListener = new SelectionAdapter(){
				public void widgetSelected(SelectionEvent event){
					selectionChanged();
				}
			};
			list.addSelectionListener(  selectionListener);
			}/**/ 
		//selectionChanged();
	}

	protected void createButtons() {
		addButton = createPushButton(this, "Add");
		removeButton = createPushButton(this, "Rremove");
		removeAllButton = createPushButton(this, "Remove All");
		upButton = createPushButton(this, "Up");
		downButton = createPushButton(this, "Down");
	}

	protected Button createPushButton(Composite parent, String label) {
		Button button = new Button(parent, SWT.PUSH);
		button.setText(label);
		return button;
	}


	protected void moveItem(boolean up) {
		int index = list.getSelectionIndex();
		int target = up ? index - 1 : index + 1;

		if (index >= 0) {
			String[] selection = list.getSelection();
			//assert.isTrue(selection.length == 1);
			list.remove(index);
			list.add(selection[0], target);
			list.setSelection(target);
		}
	}
	

	protected void removePressed() {
		int index = list.getSelectionIndex();
		if (index >= 0) {
			list.remove(index);
			selectionChanged();
		}
	}


	protected void removeAllPressed() {
			list.removeAll();
			selectionChanged();
	}


	public void selectionChanged() {
		int index = list.getSelectionIndex();
		int size = list.getItemCount();
		removeAllButton.setEnabled(size > 0);
		removeButton.setEnabled(index >= 0);
		upButton.setEnabled(size > 1 && index > 0);
		downButton.setEnabled(size > 1 && index >= 0 && index < size - 1);
	}


}
