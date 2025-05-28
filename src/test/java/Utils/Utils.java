package Utils;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


public class Utils{

    public void alertMessage(WebDriver driver) throws InterruptedException {
        Thread.sleep(1500);
        driver.switchTo().alert().accept();
    }

    public Properties prop;
    public void getEnvVar() throws IOException {
        String path = "./src/test/resources/config.properties";
        prop = new Properties();
        FileInputStream fileInputStream = new FileInputStream(path);
        prop.load(fileInputStream);
    }

    public void setEnvVar(String key, String value) throws ConfigurationException {
        String path = "./src/test/resources/config.properties";

        PropertiesConfiguration propertiesConfiguration = new PropertiesConfiguration(path);
        propertiesConfiguration.setProperty(key,value);
        propertiesConfiguration.save();
    }
}
