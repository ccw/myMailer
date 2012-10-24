package idv.kenwei.mymailer.mail;

import java.io.Serializable;

/**
 * MessageGenterFactory...
 * 
 * @author Ken Chen
 * @since 2004/2/25
 */
public class MessageGenterFactory implements Serializable {

    public static final int HTML_MESSAGE_GENTER = 1010;

    public static MessageGenter getSenderInstance(int aType){
        return new HtmlMessageGenter();
    }

}
