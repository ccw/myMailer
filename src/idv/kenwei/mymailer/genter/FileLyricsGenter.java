package idv.kenwei.mymailer.genter;

import idv.kenwei.mymailer.bean.Lyrics;
import idv.kenwei.mymailer.conf.ConfigurationLoader;
import idv.kenwei.mymailer.conf.Constants;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.text.DecimalFormat;

/**
 * FileLyricsGenter...
 * 
 * @author Ken Chen
 * @since 2004/2/24
 */
public class FileLyricsGenter implements LyricsGenter {

    private static Log logger = LogFactory.getLog(FileLyricsGenter.class);

    private final String VELOCITY_PROPERTIES;
    private final String CD_KEYWORD;

    private final String TEMPLATE_FILE_NAME;
    private final String FILE_OUTPUT_FOLDER;

    private DecimalFormat format;

    public FileLyricsGenter(){
        ConfigurationLoader loader = ConfigurationLoader.getInstance();
        VELOCITY_PROPERTIES = loader.getProperty(Constants.VELOCITY_PROPERTIES);
        CD_KEYWORD = loader.getProperty(Constants.CD_KEYWORD);

        FILE_OUTPUT_FOLDER = loader.getProperty(Constants.HTML_OUTPUT_PATH);
        TEMPLATE_FILE_NAME = CD_KEYWORD + ".vm";

        format = new DecimalFormat("00");
    }

    public void generate(Lyrics[] aLyrics) throws Exception {
        logger.debug("~~ generate ~~");
        logger.debug("Template File [" + TEMPLATE_FILE_NAME + "]");
        logger.debug("Output Folder [" + FILE_OUTPUT_FOLDER + "]");
        //
        try {
            Velocity.init(FileLyricsGenter.class.getResource("/" + VELOCITY_PROPERTIES).getFile());
        } catch (Exception e) {
            logger.error("Failure!!", e);
            throw e;
        }
        Template template = Velocity.getTemplate(TEMPLATE_FILE_NAME, "Big5");
        VelocityContext context = null;
        for (int i = 0; i < aLyrics.length; i++) {
            context = new VelocityContext();
            context.put(Constants.VELOCITY_KEY_TITLE, aLyrics[i].getTitle());
            context.put(Constants.VELOCITY_KEY_SINGER, aLyrics[i].getSinger());
            context.put(Constants.VELOCITY_KEY_MUSICINDEX, new Integer(aLyrics[i].getMusicIndex()));
            context.put(Constants.VELOCITY_KEY_SUBJECT, aLyrics[i].getSubject());
            context.put(Constants.VELOCITY_KEY_COMPOSER, aLyrics[i].getComposer());
            context.put(Constants.VELOCITY_KEY_LYRICSLIST, aLyrics[i].getLyrics());
            //
            File oFile = new File(
                    FILE_OUTPUT_FOLDER + File.separator + "CD" +
                    aLyrics[i].getCDIndex() + "_" + format.format(aLyrics[i].getMusicIndex()) + ".html");
            logger.debug("Generate File [" + oFile + "]");
            BufferedWriter writer = writer = new BufferedWriter(new FileWriter(oFile, false));
            if (template != null) template.merge(context, writer);
            //
            writer.flush();
            writer.close();
        }

    }

}
