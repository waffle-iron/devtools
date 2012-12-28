package net.sourceforge.devtool.lib.helper;

import java.io.File;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * @author michel
 *
 *History of Changes:
 *
 *  $Log: PathTokenizer.java,v $
 *  Revision 1.1  2005/04/03 10:38:51  tmichel
 *  new structure as a mix of j2ee best praxis and maven
 *
 *  Revision 1.1  2004/04/18 14:41:29  tmichel
 *  solving of dependency confilicts
 *
 *  Revision 1.1  2004/04/09 15:44:01  tmichel
 *  start to realize new preferences structure
 *
 *  Revision 1.2  2004/01/29 20:55:24  tmichel
 *  merge with v0.0.9
 *
 *  Revision 1.1.2.2  2004/01/29 20:18:09  tmichel
 *  merge with v0.0.9
 *
 *  Revision 1.1  2003/07/13 22:30:41  tmichel
 *  structure changed:
 *  - lib for core functions
 *  - support plugin
 *  - wizard plugin
 *
 *  Revision 1.1  2003/01/17 21:04:39  tmichel
 *  popup for add and remove nature works correct
 *
 * 
 */
public class PathTokenizer extends StringTokenizer {

	/**
	 * Constructor for PathTokenizer.
	 * @param str
	 * @param delim
	 * @param returnDelims
	 */
	public PathTokenizer(String str) {
		super(str, File.pathSeparator, false);
	}

	public String[] getPathEntries(){
		final ArrayList list = new ArrayList();
		while(this.hasMoreTokens()){
			list.add( this.nextToken() );
		}// next token
		final String[] entries = new String[ list.size()];
		list.toArray( entries );
		return entries;
	}//getPathDelimiter()

}//PathTokenizer
