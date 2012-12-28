/*
 * Created on 08.04.2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package net.sourceforge.devtool.lib.helper;

import java.util.StringTokenizer;


/**
 * @author tmichel
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class StringListTokenizer extends StringTokenizer {

    /**
     * @param str
     */
    public StringListTokenizer(String str) {
        super(str);
    }

    /**
     * @param str
     * @param delim
     */
    public StringListTokenizer(String str, String delim) {
        super(str, delim);
    }

    /**
     * @param str
     * @param delim
     * @param returnDelims
     */
    public StringListTokenizer(String str, String delim, boolean returnDelims) {
        super(str, delim, returnDelims);
    }

    
    public String[] getTokens(){
        final String[] entries = new String[countTokens()];
        int i = 0;
        while (hasMoreTokens()) {
            entries[i] = nextToken();
            i++;
        }
        return entries;
    }
    public static String tokensToString(final String[] tokens){
        return tokensToString(tokens,',');
    }
    public static String tokensToString(final String[] tokens,final char delimiter){
        final StringBuffer buf = new StringBuffer();
        for(int i=0; i<tokens.length;i++){
            if(i>0){
                buf.append(delimiter);
            }
            buf.append(tokens[i]);
        }
        return buf.toString();
    }
    
    
    
}
