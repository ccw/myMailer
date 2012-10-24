package idv.kenwei.mymailer.genter;

import idv.kenwei.mymailer.bean.Lyrics;

import java.io.Serializable;

/**
 * LyricsGenter...
 * 
 * @author Ken Chen
 * @since 2004/2/24
 */
public interface LyricsGenter extends Serializable {

    public void generate(Lyrics[] aLyrics) throws Exception;

}
