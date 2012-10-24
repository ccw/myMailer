package idv.kenwei.mymailer.parser;

import idv.kenwei.mymailer.bean.Lyrics;
import idv.kenwei.mymailer.conf.ConfigurationLoader;
import idv.kenwei.mymailer.conf.Constants;
import idv.kenwei.mymailer.util.Utility;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.*;
import java.util.ArrayList;

/**
 * TextLyricsParser...
 * 
 * @author Ken Chen
 * @since 2004/2/23
 */
public class TextLyricsParser implements LyricsParser {

    private static Log logger = LogFactory.getLog(TextLyricsParser.class);

    private final String CD_SINGER;
    private final String CD_TITLE;
    private final int CD_SPLIT_NUMBER;
    private final String CD_INDEX_DEVITION;

    private final String MELODY_COMPOSER_KEYWORD;
    private final String LYRICS_COMPOSER_KEYWORD;
    private final String OTHER_COMPOSER_KEYWORD;

    private final String SOURCE_FILE_NAME;

    public TextLyricsParser() {
        ConfigurationLoader loader = ConfigurationLoader.getInstance();
        CD_SPLIT_NUMBER = Integer.parseInt(loader.getProperty(Constants.PARSER_CD_SPLIT_NUMBER));
        CD_SINGER = loader.getProperty(Constants.CD_SINGER);
        CD_TITLE = loader.getProperty(Constants.CD_TITLE);
        CD_INDEX_DEVITION = loader.getProperty(Constants.PARSER_CD_INDEX_DEVITION);
        MELODY_COMPOSER_KEYWORD = loader.getProperty(Constants.PARSER_MELODY_COMPOSER_KEYWORD);
        LYRICS_COMPOSER_KEYWORD = loader.getProperty(Constants.PARSER_LYRICS_COMPOSER_KEYWORD);
        OTHER_COMPOSER_KEYWORD = loader.getProperty(Constants.PARSER_OTHER_COMPOSER_KEYWORD);

        SOURCE_FILE_NAME = loader.getProperty(Constants.LYRICS_INPUT_PATH) + File.separator +
                loader.getProperty(Constants.CD_KEYWORD) + ".txt";
    }

    public Lyrics[] parse() throws Exception {
        logger.debug("~~ parse ~~");
        logger.debug("The File Name [" + SOURCE_FILE_NAME + "]");
        return loadData();
    }

    private Lyrics[] loadData() throws Exception {
        logger.debug("~~ loadData ~~");
        ArrayList data = new ArrayList();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(SOURCE_FILE_NAME)));
            //
            String line = br.readLine();
            Lyrics section = new Lyrics();
            int cFlag = -2;
            int index = -1;
            //
            while (line != null) { //!Utility.isStringEmpty(line)){
                //logger.debug(line);
                if ((cFlag >= 2)) {
                    if (Utility.isStringEmpty(section.getSinger())) {
                        section.setSinger(CD_SINGER);
                    }
                    if (Utility.isStringEmpty(section.getTitle())) {
                        section.setTitle(CD_TITLE);
                    }
                    data.add(section);
                    section = new Lyrics();
                    cFlag = 0;
                    logger.debug("Loaded Section Size [" + data.size() + "]");
                }
                //
                index = line.indexOf(CD_INDEX_DEVITION);
                if (index > 0) {
                    cFlag = 0;
                    logger.debug("~~ load lyrics header~~");
                    int mIndex = Integer.parseInt(line.substring(0, index));
                    section.setCDIndex((mIndex < CD_SPLIT_NUMBER)?1:2);
                    section.setMusicIndex((mIndex < CD_SPLIT_NUMBER)?mIndex:(mIndex - CD_SPLIT_NUMBER + 1));
                    logger.debug("MusicIndex [" + section.getMusicIndex() + "]");
                    section.setSubject(line.substring(index + 1));
                    logger.debug("Subject [" + section.getSubject() + "]");
                } else if ((line.indexOf(MELODY_COMPOSER_KEYWORD) >= 0) ||
                        (line.indexOf(LYRICS_COMPOSER_KEYWORD) >= 0) || (line.indexOf(OTHER_COMPOSER_KEYWORD) >= 0)) {
                    cFlag = 0;
                    logger.debug("~~ load lyrics composer ~~");
                    section.setComposer(line);
                    logger.debug("Composer [" + section.getComposer() + "]");
                } else if ((line.trim().length() == 0)) {
                    logger.debug("~~ load line breaker ~~");
                    cFlag++;
                } else if ((line.trim().length() > 0)) {
                    cFlag = 0;
                    logger.debug("~~ load lyrics ~~");
                    section.addLyrics(line);
                }

                //
                line = br.readLine();
            }
            if (section.getMusicIndex() > 0) {
                if (Utility.isStringEmpty(section.getSinger())) {
                    section.setSinger(CD_SINGER);
                }
                if (Utility.isStringEmpty(section.getTitle())) {
                    section.setTitle(CD_TITLE);
                }
                data.add(section);
                logger.debug("Loaded Section Size [" + data.size() + "]");
            }
            br.close();
            br = null;
        } catch (IOException e) {
            logger.error("loading data failure", e);
            throw e;
        }
        return (Lyrics[]) data.toArray(new Lyrics[data.size()]);
    }

}
