package net.sourceforge.devtool.lib.xml;

import java.util.ArrayList;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * @author michel
 *
 *History of Changes:
 *
 *  $Log: XMLUtillity.java,v $
 *  Revision 1.1  2005/04/03 10:38:50  tmichel
 *  new structure as a mix of j2ee best praxis and maven
 *
 *  Revision 1.6  2004/05/06 20:20:37  tmichel
 *  new tjdo extension editor page added
 *
 *  Revision 1.5  2004/03/23 19:16:34  tmichel
 *  getChildNodes added
 *
 *  Revision 1.4  2004/01/01 13:51:53  tmichel
 *  changes for bugfix
 *
 *  Revision 1.3  2003/07/13 22:38:31  tmichel
 *  structure changed:
 *  - lib for core functions
 *  - support plugin
 *  - wizard plugin
 *
 *  Revision 1.2  2003/04/25 20:35:07  tmichel
 *  bugfix NullPointer Exception
 *
 *  Revision 1.1  2003/04/06 18:54:11  tmichel
 *  new version without lib plugin
 *
 *  Revision 1.1  2003/02/09 19:34:29  tmichel
 *  new features for images and xml processing added
 *
 * 
 */
public class XMLUtillity {

	/**
	 * Constructor for XMLUtillity.
	 */
	protected XMLUtillity() {
		super();
	} //XMLUtillity()

	public static String getAttribute(
		final Node node,
		final String attributeName) {
		final NamedNodeMap tmpMap = node.getAttributes();
		if( tmpMap == null ){
			return null;
		}
		final Node tmpNode = tmpMap.getNamedItem(attributeName);
		if( tmpNode ==null ){
			return null;
		}
		return tmpNode.getNodeValue();
	} //getAttribute(Node,String)

	public static String[] getAttributes(
		final Node node,
		final String[] attributeNames) {
		final String[] valueList = new String[attributeNames.length];
		final NamedNodeMap attMap = node.getAttributes();
		Node tmpNode = null;
		for (int i = 0; i < attributeNames.length; i++) {
			try {
				tmpNode = attMap.getNamedItem(attributeNames[i]);
				valueList[i] = tmpNode.getNodeValue();
			} catch (Exception e) {
				valueList[i] = "";
			}
		} // next attribute
		return valueList;
	} //getAttributes[]

	public static Node[] getChildNodes(
		final Node node,
		final String attributeName,
		final String value) {
		final ArrayList nodeList = new ArrayList();
		final NodeList childs = node.getChildNodes();
		String readValue = null;
		for (int i = 0; i < childs.getLength(); i++) {
			readValue = getAttribute(childs.item(i), attributeName);
			if (value.equals(readValue)) {
				nodeList.add(childs.item(i));
			}
		} //next child node
		final Node[] result = new Node[nodeList.size()];
		nodeList.toArray(result);
		return result;
	} //getChildNodes(String,String)

	public static Node[] getChildNodes(
			final Node node,
			final String elementName){
		final ArrayList nodeList = new ArrayList();
		final NodeList childs = node.getChildNodes();
		String nodeName = null;
		for (int i = 0; i < childs.getLength(); i++) {
			nodeName = childs.item(i).getNodeName();
			if (nodeName != null && nodeName.equals(elementName)) {
				nodeList.add(childs.item(i));
			}
		} //next child node
		final Node[] result = new Node[nodeList.size()];
		nodeList.toArray(result);
		return result;
	} //getChildNodes(String,String)
	
	
} //XMLUtillity
