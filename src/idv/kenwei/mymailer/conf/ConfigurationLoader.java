package idv.kenwei.mymailer.conf;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.Serializable;
import java.util.*;

/**
 * ConfigurationLoader...
 * 
 * @author Ken Chen
 * @since 2004/2/25
 */
public class ConfigurationLoader implements Serializable {

    private static Log logger = LogFactory.getLog(ConfigurationLoader.class);

    private static ConfigurationLoader loader = null;
    private HashMap data = null;

    private ConfigurationLoader(){
        logger.debug("~~ Initial ~~");
        data = new HashMap();
        try {
            Properties prop = new Properties();
            prop.load(ConfigurationLoader.class.getResourceAsStream("/myMailer.properties"));
            Iterator keys = prop.keySet().iterator();
            while (keys.hasNext()) {
                String key = (String) keys.next();
                String value = new String(prop.getProperty(key).getBytes("ISO-8859-1"));
                data.put(key, value);
            }
//            ResourceBundle rb = ResourceBundle.getBundle("myMailer");
//            Enumeration keys = rb.getKeys();
//            while (keys.hasMoreElements()) {
//                String key = (String) keys.nextElement();
//                String value = new String(rb.getString(key).getBytes("Big5"));
//                data.put(key, value);
//            }
        } catch (Exception e) {
            logger.error(e);
        }

    }

    public static ConfigurationLoader getInstance(){
        if(loader == null){
            loader = new ConfigurationLoader();
        }
        return loader;
    }

    public String getProperty(String aKey){
        return (String)data.get(aKey);
    }

}
