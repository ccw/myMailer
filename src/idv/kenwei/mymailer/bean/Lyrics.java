package idv.kenwei.mymailer.bean;

import java.util.ArrayList;
import java.io.Serializable;

/**
 * Lyrics...
 * 
 * @author Ken Chen
 * @since 2004/2/23
 */
public class Lyrics implements Serializable {

    private int cdIndex;
    private int musicIndex;
    private String subject;
    private String composer;
    //private String melodyComposer;
    //private String lyricsComposer;
    private String singer;
    private String title;
    private ArrayList lyrics;

    public Lyrics() {
        lyrics = new ArrayList();
    }

    public String[] getLyrics() {
        return (String[]) lyrics.toArray(new String[lyrics.size()]);
    }

    public void addLyrics(String aLine){
        lyrics.add(aLine);
    }

    public void setLyrics(ArrayList aLyrics) {
        lyrics = aLyrics;
    }

    public void clearLyrics(){
        lyrics.clear();
    }

    public int getCDIndex() {
        return cdIndex;
    }

    public void setCDIndex(int aCdIndex) {
        cdIndex = aCdIndex;
    }

    public int getMusicIndex() {
        return musicIndex;
    }

    public void setMusicIndex(int aMusicIndex) {
        musicIndex = aMusicIndex;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String aSubject) {
        subject = aSubject;
    }

    public String getComposer() {
        return composer;
    }

    public void setComposer(String aComposer) {
        composer = aComposer;
    }

    /*
    public String getMelodyComposer() {
        return melodyComposer;
    }

    public void setMelodyComposer(String aMelodyComposer) {
        melodyComposer = aMelodyComposer;
    }

    public String getLyricsComposer() {
        return lyricsComposer;
    }

    public void setLyricsComposer(String aLyricsComposer) {
        lyricsComposer = aLyricsComposer;
    }
    */
    
    public String getSinger() {
        return singer;
    }

    public void setSinger(String aSinger) {
        singer = aSinger;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String aTitle) {
        title = aTitle;
    }


}
