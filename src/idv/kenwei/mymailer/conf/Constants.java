package idv.kenwei.mymailer.conf;

import java.io.Serializable;

/**
 * Constants...
 * 
 * @author Ken Chen
 * @since 2004/2/25
 */
public class Constants implements Serializable {

    public static final String MAIL_SMTP_SERVER = "MAIL_SMTP_SERVER";
    public static final String MAIL_USER_NAME = "MAIL_USER_NAME";
    public static final String MAIL_USER_PASSWORD = "MAIL_USER_PASSWORD";
    public static final String MAIL_SERDER = "MAIL_SENDER";
    public static final String MAIL_RECIVER = "MAIL_RECIVER";

    public static final String CD_TITLE = "CD_TITLE";
    public static final String CD_SINGER = "CD_SINGER";
    public static final String CD_KEYWORD = "CD_KEYWORD";

    public static final String PARSER_CD_SPLIT_NUMBER = "PARSER_CD_SPLIT_NUMBER";
    public static final String PARSER_CD_INDEX_DEVITION = "PARSER_CD_INDEX_DEVITION";
    public static final String PARSER_MELODY_COMPOSER_KEYWORD = "PARSER_MELODY_COMPOSER_KEYWORD";
    public static final String PARSER_LYRICS_COMPOSER_KEYWORD = "PARSER_LYRICS_COMPOSER_KEYWORD";
    public static final String PARSER_OTHER_COMPOSER_KEYWORD = "PARSER_OTHER_COMPOSER_KEYWORD";

    public static final String LYRICS_INPUT_PATH = "LYRICS_INPUT_PATH";
    public static final String HTML_OUTPUT_PATH = "HTML_OUTPUT_PATH";
    public static final String ATTACH_DATA_PATH = "ATTACH_DATA_PATH";
    public static final String TEMPLATE_INPUT_PATH = "TEMPLATE_INPUT_PATH";

    public static final String VELOCITY_PROPERTIES = "VELOCITY_PROPERTIES";
    public static final String VELOCITY_KEY_TITLE = "title";
    public static final String VELOCITY_KEY_SINGER = "singer";
    public static final String VELOCITY_KEY_MUSICINDEX = "musicIndex";
    public static final String VELOCITY_KEY_SUBJECT = "subject";
    public static final String VELOCITY_KEY_COMPOSER = "composer";
    public static final String VELOCITY_KEY_LYRICSLIST = "lyricsList";

    private Constants() {
    }

}
