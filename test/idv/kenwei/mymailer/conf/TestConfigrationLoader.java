package idv.kenwei.mymailer.conf;

import junit.framework.TestCase;

/**
 * TestConfigrationLoader...
 * 
 * @author Ken Chen
 * @since 2004/2/25
 */
public class TestConfigrationLoader extends TestCase {

    public TestConfigrationLoader() {
        super();
    }

    protected void setUp() throws Exception {
        super.setUp();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testAll(){
        try{
            ConfigurationLoader loader = ConfigurationLoader.getInstance();
            System.out.println("MAIL_SENDER [" + loader.getProperty(Constants.MAIL_SERDER) + "]");
            System.out.println("PARSER_LYRICS_COMPOSER_KEYWORD [" + loader.getProperty(Constants.PARSER_LYRICS_COMPOSER_KEYWORD) + "]");
        } catch (Exception e){
            e.printStackTrace();
            fail();
        }
    }

}
