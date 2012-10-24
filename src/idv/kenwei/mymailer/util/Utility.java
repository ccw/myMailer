package idv.kenwei.mymailer.util;

import java.io.Serializable;

/**
 * Utility...
 * 
 * @author Ken Chen
 * @since 2004/2/24
 */
public class Utility implements Serializable {

    public static final boolean isStringEmpty(String aString){
        return (aString == null) || (aString.trim().length() <= 0);
    }

}
