package idv.kenwei.mymailer.genter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.Serializable;

/**
 * LyricsGenterFactory...
 * 
 * @author Ken Chen
 * @since 2004/2/24
 */
public class LyricsGenterFactory implements Serializable {

    private static Log logger = LogFactory.getLog(LyricsGenterFactory.class);

    public static final int FILE_GENERATER = 1010;

    public static LyricsGenter getGenterInstance(int aType){
        logger.debug("~~ getGenterInstance ~~");
        return new FileLyricsGenter();
    }

}
