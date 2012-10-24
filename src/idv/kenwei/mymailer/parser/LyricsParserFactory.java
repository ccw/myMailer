package idv.kenwei.mymailer.parser;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.Serializable;

/**
 * LyricsParserFactory...
 * 
 * @author Ken Chen
 * @since 2004/2/23
 */
public class LyricsParserFactory implements Serializable {

    public static final int TEXT_PARSER = 1010;
    public static final int XML_PARSER = 2020;
    public static final int EML_PARSER = 3030;
    public static final int OBJECT_PARSER = 4040;

    private static Log logger = LogFactory.getLog(LyricsParserFactory.class);

    public static LyricsParser getParserInstance(final int aParserType) {
        logger.debug("~~ getParserInstance ~~");
        LyricsParser parser = new TextLyricsParser();   // default parser
        switch (aParserType) {

        }
        return parser;
    }

}
