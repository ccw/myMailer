package idv.kenwei.mymailer;

import idv.kenwei.mymailer.bean.Lyrics;
import idv.kenwei.mymailer.conf.ConfigurationLoader;
import idv.kenwei.mymailer.conf.Constants;
import idv.kenwei.mymailer.genter.LyricsGenterFactory;
import idv.kenwei.mymailer.mail.MessageGenterFactory;
import idv.kenwei.mymailer.parser.LyricsParserFactory;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.mail.*;
import javax.mail.internet.MimeMessage;
import java.io.Serializable;
import java.util.Properties;

/**
 * MailSender...
 * 
 * @author Ken Chen
 * @since 2004/2/26
 */
public class MailSender implements Serializable {

    private static Log logger = LogFactory.getLog(MailSender.class);

    private final String SMTP_SERVER;
    private final String USER_NAME;
    private final String USER_PASSWORD;


    public MailSender() {
        logger.debug("~~ initial ~~");
        ConfigurationLoader loader = ConfigurationLoader.getInstance();
        SMTP_SERVER = loader.getProperty(Constants.MAIL_SMTP_SERVER);
        USER_NAME = loader.getProperty(Constants.MAIL_USER_NAME);
        USER_PASSWORD = loader.getProperty(Constants.MAIL_USER_PASSWORD);

    }

    public void send() throws Exception {
        logger.debug("~~ send ~~");
        Properties props = System.getProperties();
        props.put("mail.smtp.host", SMTP_SERVER);
        //props.put("mail.smtp.auth", "true");

        logger.debug("Initial MailSession");
        Session session = Session.getDefaultInstance(props); //, new DefaultAuthenticator());
        session.setDebug(true);

        Lyrics[] data = LyricsParserFactory.getParserInstance(LyricsParserFactory.TEXT_PARSER).parse();
        LyricsGenterFactory.getGenterInstance(LyricsGenterFactory.FILE_GENERATER).generate(data);
        MimeMessage[] msgs = MessageGenterFactory.getSenderInstance(MessageGenterFactory.HTML_MESSAGE_GENTER).generate(data, session);
        for (int i = 0; i < msgs.length; i++) {
            MimeMessage msg = msgs[i];
            try {
                Transport.send(msg);
                logger.info("[" + msg.getSubject() + "] Sending Successful!");
            } catch (MessagingException e) {
                logger.error("[" + msg.getSubject() + "] Sending Failure!", e);
            }
        }

    }

    protected class DefaultAuthenticator extends Authenticator {

        protected PasswordAuthentication getPasswordAuthentication() {
            logger.debug("~~ getPasswordAuthentication ~~");
            return new PasswordAuthentication(USER_NAME, USER_PASSWORD);
        }
    }

    public static void main(String[] args) {
        try {
            new MailSender().send();
        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

}
