package idv.kenwei.mymailer.mail;

import idv.kenwei.mymailer.conf.ConfigurationLoader;
import idv.kenwei.mymailer.conf.Constants;
import idv.kenwei.mymailer.bean.Lyrics;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileFilter;

/**
 * HtmlMessageGenter...
 * 
 * @author Ken Chen
 * @since 2004/2/25
 */
public class HtmlMessageGenter implements MessageGenter {

    private static Log logger = LogFactory.getLog(HtmlMessageGenter.class);

    private final String DEFAULT_SENDER;
    private final String DEFAULT_RECIVER;
    private final String HTML_FOLDER;
    private final String ATTACH_FOLDER;

    public HtmlMessageGenter() {
        logger.debug("~~ initial ~~");
        ConfigurationLoader loader = ConfigurationLoader.getInstance();
        DEFAULT_SENDER = loader.getProperty(Constants.MAIL_SERDER);
        DEFAULT_RECIVER = loader.getProperty(Constants.MAIL_RECIVER);
        HTML_FOLDER = loader.getProperty(Constants.HTML_OUTPUT_PATH);
        ATTACH_FOLDER = loader.getProperty(Constants.ATTACH_DATA_PATH);
    }

    public MimeMessage[] generate(Lyrics[] aLyrics, Session aSession) throws Exception {
        logger.debug("~~ generate ~~");
        File[] htmls = new File(HTML_FOLDER).listFiles(new HtmlFileFilter());
        MimeMessage[] messages = new MimeMessage[htmls.length];
        String mName = null, attachFile = null;
        for (int i = 0; i < htmls.length; i++) {
            mName = htmls[i].getName().substring(0, htmls[i].getName().indexOf("."));
            attachFile = mName + ".zip";
            logger.debug("FileName[" + mName + "]");
            logger.debug("Attached[" + attachFile + "]");
            logger.debug("Subject [" + aLyrics[i].getSubject() + "]");

            messages[i] = new MimeMessage(aSession);
            messages[i].setFrom(new InternetAddress(DEFAULT_SENDER));
            messages[i].setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(DEFAULT_RECIVER));
            messages[i].setSubject(mName + " " + aLyrics[i].getSubject());

            MimeBodyPart htmlMbp = new MimeBodyPart();
            htmlMbp.setDataHandler(new DataHandler(new ByteArrayDataSource(new FileInputStream(htmls[i]), "text/html")));

            MimeBodyPart fileMbp = new MimeBodyPart();
            fileMbp.setDataHandler(new DataHandler(new FileDataSource(ATTACH_FOLDER + File.separator + attachFile)));
            fileMbp.setFileName(attachFile);

            Multipart mp = new MimeMultipart();
            mp.addBodyPart(htmlMbp);
            mp.addBodyPart(fileMbp);
            messages[i].setContent(mp);

            htmls[i].deleteOnExit();
        }


        return messages;  //To change body of implemented methods use File | Settings | File Templates.
    }

    protected class HtmlFileFilter implements FileFilter{
        public boolean accept(File aFile) {
            return aFile.getName().endsWith("html");
        }
    }

}
