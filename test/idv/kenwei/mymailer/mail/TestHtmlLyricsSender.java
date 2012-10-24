package idv.kenwei.mymailer.mail;

import junit.framework.TestCase;

/**
 * TestHtmlLyricsSender...
 * 
 * @author Ken Chen
 * @since 2004/2/26
 */
public class TestHtmlLyricsSender extends TestCase {

    public TestHtmlLyricsSender() {
        super();    //To change body of overridden methods use File | Settings | File Templates.
    }

    protected void setUp() throws Exception {
        super.setUp();    //To change body of overridden methods use File | Settings | File Templates.
    }

    protected void tearDown() throws Exception {
        super.tearDown();    //To change body of overridden methods use File | Settings | File Templates.
    }

    public void testAll(){
        try {
            MessageGenterFactory.getSenderInstance(MessageGenterFactory.HTML_MESSAGE_GENTER).generate(null, null);
        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            fail();
        }
    }

}
