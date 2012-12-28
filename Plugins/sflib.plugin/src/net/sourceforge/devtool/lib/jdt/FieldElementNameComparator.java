/*
 * Created on 04.01.2004
 * 
 * To change the template for this generated file go to Window - Preferences -
 * Java - Code Generation - Code and Comments
 */
package net.sourceforge.devtool.lib.jdt;

import java.text.Collator;
import java.util.Comparator;

import org.eclipse.jdt.core.IField;

/**
 * @author tmichel
 * 
 * To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Generation - Code and Comments
 */
public class FieldElementNameComparator implements Comparator {

	protected static final Collator collator = Collator.getInstance();
	
	
	/*
	 * Compare objects from type IField by usage the getElementName() method.
	 * 
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	public int compare(Object o1, Object o2) {
		if (!(o1 instanceof IField)) {
			throw new IllegalArgumentException("o1 is not applicable to compare method of FieldElementNameComparator ");
		}
		if (!(o2 instanceof IField)) {
			throw new IllegalArgumentException("o2 is not applicable to compare method of FieldElementNameComparator ");
		}
		final IField field1 = (IField) o1;
		final String name1 = field1.getElementName();
		final IField field2 = (IField) o2;
		final String name2 = field2.getElementName();
		return collator.compare(name1,name2);
	}

}
