package net.sourceforge.devtool.lib.xml;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.w3c.dom.Document;
import org.w3c.dom.DocumentType;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.ProcessingInstruction;

/**
 * @author michel
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class DOMWriter {

	protected final Logger logger = Logger.getLogger(getClass().getName());

	protected Document doc = null;

	public DOMWriter(final Document doc) {
		this.doc = doc;
		this.doc.normalize();
	} //DOMWriter(Document)

	public boolean writeToFile(final File file) throws IOException {
		if (!file.exists()) {
			file.createNewFile();
		}
		final FileWriter writer = new FileWriter(file);
		//final Node root = doc.getDocumentElement();
		boolean ok = writeNode(doc, writer);
		writer.close();
		return ok;
	} // writeToFile(File):boolean

	protected boolean writeNode(final Node root, final Writer writer) {
		if (root == null)
			return false;

		boolean ok = true;
		final short type = root.getNodeType();
		if( type == Node.DOCUMENT_NODE){
			final Document doc = (Document) root;
			final DocumentType docType = doc.getDoctype();
			writeNode( docType, writer);
			final Node child = doc.getDocumentElement();
			writeNode(child, writer);
		}else if( type  == Node.PROCESSING_INSTRUCTION_NODE ){
			try{
			final ProcessingInstruction procInst = (ProcessingInstruction) root;
			writer.write("<?");
			writer.write(procInst.getTarget());
			writer.write(" ");
			writer.write(procInst.getData());
			writer.write("?>");
			}catch( Exception ex){
				logger.log(Level.SEVERE, ex.getMessage());
			}
		}else if (type == Node.DOCUMENT_TYPE_NODE) {
			try {
				final DocumentType docType = (DocumentType) root;
				writer.write("<!DOCTYPE " + docType.getName());
				final String sysId = docType.getSystemId();
				final String pubId = docType.getPublicId();
				if (pubId != null) {
					writer.write(" PUBLIC \"" + pubId + "\"");
				}
				if (sysId != null) {
					writer.write(" SYSTEM \"" + sysId + "\"");
				}
				writer.write(">");
			} catch (Exception ex) {
				logger.log(Level.SEVERE, ex.getMessage());
			}
		} else if (type == Node.TEXT_NODE) {
			try {
				final String text = root.getNodeValue();
				writer.write(text);
			} catch (IOException ex) {
				ok = false;
			}
		} else if (type == Node.ELEMENT_NODE) {
			try {
				final StringBuffer text = new StringBuffer();
				final String nodeName = root.getNodeName();
				text.append("<" + nodeName + " ");
				// add fileList of attributes
				NamedNodeMap map = root.getAttributes();
				Node node = null;
				for (int i = 0; i < map.getLength(); i++) {
					node = map.item(i);
					text.append(
						node.getNodeName()
							+ "=\""
							+ node.getNodeValue()
							+ "\" ");
				} //next attribute
				text.append(">");
				writer.write(text.toString());
				final NodeList list = root.getChildNodes();
				for (int i = 0; i < list.getLength(); i++) {
					ok = ok && writeNode(list.item(i), writer);
				} // next child node

				writer.write("</" + nodeName + ">");
			} catch (IOException ex) {
				ok = false;
			}
		}
		try {
			writer.flush();
		} catch (IOException ex) {
			ok = false;
		}
		return ok;
	} // writeNodeNode,Writer):boolean	

} // DOMWriter
