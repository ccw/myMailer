package idv.kenwei.mymailer.mail;

import idv.kenwei.mymailer.bean.Lyrics;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;
import java.io.Serializable;

/**
 * MessageGenter...
 * 
 * @author Ken Chen
 * @since 2004/2/25
 */
public interface MessageGenter extends Serializable {

    public MimeMessage[] generate(Lyrics[] aLyrics, Session aSession) throws Exception;

}
