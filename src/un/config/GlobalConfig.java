/**
 *
 */
package un.config;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author gonglei
 */
public class GlobalConfig {

    private static GlobalConfig instance;

    private Properties properties;

    private GlobalConfig() {
        InputStream fis = null;
        properties = new Properties();
        try {
            fis = new FileInputStream("config.properties");
            properties.load(fis);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                fis.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }

    public static synchronized GlobalConfig getInstance() {
        if (instance == null) {
            instance = new GlobalConfig();
        }
        return instance;
    }

    public void storeToPropertis() {
        // 无true和有true是有区别的，我发现区别跟怎么加载propertise属性有关系，具体的还是没有弄清楚。
        FileOutputStream fos;
        try {
            fos = new FileOutputStream("config.properties", false);
            properties.store(fos, " ");
            fos.close();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public void setProperty(String name, String value) {
        properties.setProperty(name, value);
    }

    public String getProperty(String name) {
        return properties.getProperty(name);
    }

    public String getProperty(String name, String value) {
        return properties.getProperty(name, value);
    }
}
