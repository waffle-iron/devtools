package net.sourceforge.devtool.lib.xml;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/**
 * @author michel
 *
 *History of Changes:
 *
 *  $Log: LogErrorHandler.java,v $
 *  Revision 1.1  2005/04/03 10:38:50  tmichel
 *  new structure as a mix of j2ee best praxis and maven
 *
 *  Revision 1.4  2003/07/13 22:38:31  tmichel
 *  structure changed:
 *  - lib for core functions
 *  - support plugin
 *  - wizard plugin
 *
 *  Revision 1.1  2003/04/06 18:54:11  tmichel
 *  new version without lib plugin
 *
 *  Revision 1.2  2003/02/23 17:20:49  tmichel
 *  new images added and handling via url implements
 *
 *  Revision 1.1  2003/02/09 19:34:29  tmichel
 *  new features for images and xml processing added
 *
 * 
 */
public class LogErrorHandler implements ErrorHandler {
	
	protected final  Logger logger = Logger.getLogger(getClass().getName());
	

	/**
	 * Constructor for LogErrorHandler.
	 */
	public LogErrorHandler() {
		super();
	}//LogErrorHandler

	/**
	 * @see org.xml.sax.ErrorHandler#warning(org.xml.sax.SAXParseException)
	 */
	public void warning(SAXParseException exception) throws SAXException {
		log( Level.WARNING, exception);
	}//warning

	/**
	 * @see org.xml.sax.ErrorHandler#error(org.xml.sax.SAXParseException)
	 */
	public void error(SAXParseException exception) throws SAXException {
		log(Level.SEVERE, exception);
	}//error

	/**
	 * @see org.xml.sax.ErrorHandler#fatalError(org.xml.sax.SAXParseException)
	 */
	public void fatalError(SAXParseException exception) throws SAXException {
		log(Level.SEVERE, exception);
	}//fatalError


	protected void log(final Level level, final SAXParseException ex){
		int line = ex.getLineNumber();
		int col = ex.getColumnNumber();
		logger.log( level,"["+Integer.toString(line)+","+Integer.toString(col)+"]: "+ex.getLocalizedMessage());
	}//log

}
