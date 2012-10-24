package idv.kenwei.mymailer.parser;

import junit.framework.TestCase;
import idv.kenwei.mymailer.genter.LyricsGenterFactory;
import idv.kenwei.mymailer.bean.Lyrics;

/**
 * TestTextLyricsParser...
 * 
 * @author Ken Chen
 * @since 2004/2/24
 */
public class TestTextLyricsParser extends TestCase {

    public TestTextLyricsParser(String s) {
        super(s);
    }

    public void setUp() throws Exception {
        super.setUp();
    }

    public void tearDown() throws Exception {
        super.tearDown();
    }

    public void testParse(){
        try{
            //System.out.println(" [" + TestTextLyricsParser.class.getResource("/velocity.properties").getFile() + "]");
            Lyrics[] data = LyricsParserFactory.getParserInstance(LyricsParserFactory.TEXT_PARSER).parse();
            LyricsGenterFactory.getGenterInstance(LyricsGenterFactory.FILE_GENERATER).generate(data);
        } catch (Exception e){
            e.printStackTrace();
            fail();
        }
    }

}
