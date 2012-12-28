/*
 * Created on Jul 14, 2004
 */

package net.sourceforge.devtool.lib.xml;
import java.util.Hashtable;

/**
 * Diese Klasse dient zum kodieren/dekodieren von XHTML Entities.
 *
 *
 * @version: $Revision: 1.1 $
 * @author:  $Author: tmichel $
 * @date:    $Date: 2005/04/03 10:38:50 $
 */

public class EntityEncoder
{

    static final Hashtable decoder = new Hashtable (300 );

    static final String[] encoder = new String[0x100];

    
    static
    {
        add (" ", 160 );
        add ("¡", 161 );
        add ("¢", 162 );
        add ("£", 163 );
        add ("¤", 164 );
        add ("¥", 165 );
        add ("¦", 166 );
        add ("§", 167 );
        add ("¨", 168 );
        add ("©", 169 );
        add ("ª", 170 );
        add ("«", 171 );
        add ("¬", 172 );
        add ("­", 173 );
        add ("®", 174 );
        add ("¯", 175 );
        add ("°", 176 );
        add ("±", 177 );
        add ("²", 178 );
        add ("³", 179 );
        add ("´", 180 );
        add ("µ", 181 );
        add ("¶", 182 );
        add ("·", 183 );
        add ("¸", 184 );
        add ("¹", 185 );
        add ("º", 186 );
        add ("»", 187 );
        add ("¼", 188 );
        add ("½", 189 );
        add ("¾", 190 );
        add ("¿", 191 );
        add ("À", 192 );
        add ("�?", 193 );
        add ("Â", 194 );
        add ("Ã", 195 );
        add ("&Auml", 196 );//Ä
        add ("Å", 197 );
        add ("Æ", 198 );
        add ("Ç", 199 );
        add ("È", 200 );
        add ("É", 201 );
        add ("Ê", 202 );
        add ("Ë", 203 );
        add ("Ì", 204 );
        add ("�?", 205 );
        add ("Î", 206 );
        add ("�?", 207 );
        add ("�?", 208 );
        add ("Ñ", 209 );
        add ("Ò", 210 );
        add ("Ó", 211 );
        add ("Ô", 212 );
        add ("Õ", 213 );
        add ("&Ouml", 214 );//Ö
        add ("×", 215 );
        add ("Ø", 216 );
        add ("Ù", 217 );
        add ("Ú", 218 );
        add ("Û", 219 );
        add ("&Uuml", 220 );//Ü
        add ("�?", 221 );
        add ("Þ", 222 );
        add ("&szlig", 223 );//ß
        add ("à", 224 );
        add ("á", 225 );
        add ("â", 226 );
        add ("ã", 227 );
        add ("&auml", 228 );//ä
        add ("å", 229 );
        add ("æ", 230 );
        add ("ç", 231 );
        add ("è", 232 );
        add ("é", 233 );
        add ("ê", 234 );
        add ("ë", 235 );
        add ("ì", 236 );
        add ("í", 237 );
        add ("î", 238 );
        add ("ï", 239 );
        add ("ð", 240 );
        add ("ñ", 241 );
        add ("ò", 242 );
        add ("ó", 243 );
        add ("ô", 244 );
        add ("õ", 245 );
        add ("&ouml", 246 );//ö
        add ("÷", 247 );
        add ("ø", 248 );
        add ("ù", 249 );
        add ("ú", 250 );
        add ("û", 251 );
        add ("&uuml", 252 );//ü
        add ("ý", 253 );
        add ("þ", 254 );
        add ("ÿ", 255 );
        add ("&fnof", 402 );
        add ("&Alpha", 913 );
        add ("&Beta", 914 );
        add ("&Gamma", 915 );
        add ("&Delta", 916 );
        add ("&Epsilon", 917 );
        add ("&Zeta", 918 );
        add ("&Eta", 919 );
        add ("&Theta", 920 );
        add ("&Iota", 921 );
        add ("&Kappa", 922 );
        add ("&Lambda", 923 );
        add ("&Mu", 924 );
        add ("&Nu", 925 );
        add ("&Xi", 926 );
        add ("&Omicron", 927 );
        add ("&Pi", 928 );
        add ("&Rho", 929 );
        add ("&Sigma", 931 );
        add ("&Tau", 932 );
        add ("&Upsilon", 933 );
        add ("&Phi", 934 );
        add ("&Chi", 935 );
        add ("&Psi", 936 );
        add ("&Omega", 937 );
        add ("&alpha", 945 );
        add ("&beta", 946 );
        add ("&gamma", 947 );
        add ("&delta", 948 );
        add ("&epsilon", 949 );
        add ("&zeta", 950 );
        add ("&eta", 951 );
        add ("&theta", 952 );
        add ("&iota", 953 );
        add ("&kappa", 954 );
        add ("&lambda", 955 );
        add ("&mu", 956 );
        add ("&nu", 957 );
        add ("&xi", 958 );
        add ("&omicron", 959 );
        add ("&pi", 960 );
        add ("&rho", 961 );
        add ("&sigmaf", 962 );
        add ("&sigma", 963 );
        add ("&tau", 964 );
        add ("&upsilon", 965 );
        add ("&phi", 966 );
        add ("&chi", 967 );
        add ("&psi", 968 );
        add ("&omega", 969 );
        add ("&thetasym", 977 );
        add ("&upsih", 978 );
        add ("&piv", 982 );
        add ("&bull", 8226 );
        add ("&hellip", 8230 );
        add ("&prime", 8242 );
        add ("&Prime", 8243 );
        add ("&oline", 8254 );
        add ("&frasl", 8260 );
        add ("&weierp", 8472 );
        add ("&image", 8465 );
        add ("&real", 8476 );
        add ("&trade", 8482 );
        add ("&alefsym", 8501 );
        add ("&larr", 8592 );
        add ("&uarr", 8593 );
        add ("&rarr", 8594 );
        add ("&darr", 8595 );
        add ("&harr", 8596 );
        add ("&crarr", 8629 );
        add ("&lArr", 8656 );
        add ("&uArr", 8657 );
        add ("&rArr", 8658 );
        add ("&dArr", 8659 );
        add ("&hArr", 8660 );
        add ("&forall", 8704 );
        add ("&part", 8706 );
        add ("&exist", 8707 );
        add ("&empty", 8709 );
        add ("&nabla", 8711 );
        add ("&isin", 8712 );
        add ("¬in", 8713 );
        add ("&ni", 8715 );
        add ("&prod", 8719 );
        add ("&sum", 8721 );
        add ("&minus", 8722 );
        add ("&lowast", 8727 );
        add ("&radic", 8730 );
        add ("&prop", 8733 );
        add ("&infin", 8734 );
        add ("&ang", 8736 );
        add ("&and", 8743 );
        add ("&or", 8744 );
        add ("&cap", 8745 );
        add ("&cup", 8746 );
        add ("&int", 8747 );
        add ("&there4", 8756 );
        add ("&sim", 8764 );
        add ("&cong", 8773 );
        add ("&asymp", 8776 );
        add ("&ne", 8800 );
        add ("&equiv", 8801 );
        add ("&le", 8804 );
        add ("&ge", 8805 );
        add ("&sub", 8834 );
        add ("&sup", 8835 );
        add ("&nsub", 8836 );
        add ("&sube", 8838 );
        add ("&supe", 8839 );
        add ("&oplus", 8853 );
        add ("&otimes", 8855 );
        add ("&perp", 8869 );
        add ("&sdot", 8901 );
        add ("&lceil", 8968 );
        add ("&rceil", 8969 );
        add ("&lfloor", 8970 );
        add ("&rfloor", 8971 );
        add ("&lang", 9001 );
        add ("&rang", 9002 );
        add ("&loz", 9674 );
        add ("&spades", 9824 );
        add ("&clubs", 9827 );
        add ("&hearts", 9829 );
        add ("&diams", 9830 );
        add ("\"", 34 );
        add ("&amp", 38 );//&
        add ("&lt", 60 );//<
        add ("&gt", 62 );//>
        add ("&OElig", 338 );
        add ("&oelig", 339 );
        add ("&Scaron", 352 );
        add ("&scaron", 353 );
        add ("&Yuml", 376 );
        add ("&circ", 710 );
        add ("&tilde", 732 );
        add ("&ensp", 8194 );
        add ("&emsp", 8195 );
        add ("&thinsp", 8201 );
        add ("&zwnj", 8204 );
        add ("&zwj", 8205 );
        add ("&lrm", 8206 );
        add ("&rlm", 8207 );
        add ("&ndash", 8211 );
        add ("&mdash", 8212 );
        add ("&lsquo", 8216 );
        add ("&rsquo", 8217 );
        add ("&sbquo", 8218 );
        add ("&ldquo", 8220 );
        add ("&rdquo", 8221 );
        add ("&bdquo", 8222 );
        add ("&dagger", 8224 );
        add ("&Dagger", 8225 );
        add ("&permil", 8240 );
        add ("&lsaquo", 8249 );
        add ("&rsaquo", 8250 );
        add ("&euro", 8364 );

    }

    
    
    
    
    /**
     * Dekodieren des übergebenen Entities und Rückgabe als
     * 
     * @param entity
     * @return
     */
    static final String decode (String entity )
    {
        // bei null oder length==0 -> nichts tun
        if( entity == null || entity.length () < 1 ) { return entity; }

        // anhängendes Semikolon entfernen
        if( entity.charAt (entity.length () - 1 ) == ';' )
        {
            entity = entity.substring (0, entity.length () - 1 );
        }

        if( entity.charAt (1 ) == '#' )
        {
            // auslesen Ziffernkode
            int start = 2;
            int radix = 10;
            if( entity.charAt (2 ) == 'X' || entity.charAt (2 ) == 'x' )
            {
                start++;
                radix = 16;
            }
            final Character c = new Character ((char) Integer.parseInt (entity.substring (start ),
                                                                        radix ) );
            return c.toString ();
        }
        else
        {
            // auslesen Ligaturen
            final String s = (String) decoder.get (entity );
            if( s != null )
            {
                return s;
            }
            else
            {
                return "";
            }
        }
    }

    static final public String encode (String s )
    {
        final int length = s.length ();
        final StringBuffer buffer = new StringBuffer (length * 2 );
        for( int i = 0; i < length; i++ )
        {
            char c = s.charAt (i );
            int j = (int) c;
            if( j < 0x100 && encoder[j] != null )
            {
                // have a named encoding
                buffer.append (encoder[j] );
                buffer.append (';' );
            }
            else if( j < 0x80 )
            {
                // use ASCII value
                buffer.append (c );
            }
            else
            {
                // use numeric encoding
                buffer.append ("&#" );
                buffer.append ((int) c );
                buffer.append (';' );
            }
        }
        return buffer.toString ();
    }

    static final void add (String entity, int value )
    {
        // Entity in Dekoder einstellen
        decoder.put (entity, (new Character ((char) value )).toString () );
        // 
        if( value < 0x100 )
        {
            encoder[value] = entity;
        }
    }

}

