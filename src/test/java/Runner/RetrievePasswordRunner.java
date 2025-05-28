package Runner;

import Classes.ReadGmail;
import Classes.ResetPassword;
import Setup.Setup;
import org.apache.commons.configuration.ConfigurationException;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


public class RetrievePasswordRunner extends Setup {

    @Test(description = "Retrieve password from reset link",priority = 1)
    public void retrievePassword() throws IOException, ConfigurationException, InterruptedException {
        getEnvVar();
        ResetPassword resetPassword = new ResetPassword(driver);

        resetPassword.resetBtn.get(0).click();
        String mail = properties.getProperty("newUser");
        resetPassword.doResetPassword(mail);

        String resetMessage = driver.findElement(By.cssSelector("p")).getText();
        System.out.println(resetMessage);
        Thread.sleep(6000);

        ReadGmail readGmail = new ReadGmail();
        readGmail.login();
        readGmail.getResetLink();
        readGmail.setEnvVar("email",mail);
    }

    Properties properties;
    public void getEnvVar() throws IOException {
        String path = "./src/test/resources/config.properties";
        properties = new Properties();
        FileInputStream fileInputStream = new FileInputStream(path);
        properties.load(fileInputStream);
    }

}
