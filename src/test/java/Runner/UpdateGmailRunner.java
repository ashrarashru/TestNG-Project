package Runner;

import Classes.LoginUser;
import Classes.UpdateGmail;
import Setup.Setup;
import Utils.Utils;
import com.github.javafaker.Faker;
import org.apache.commons.configuration.ConfigurationException;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.Duration;

public class UpdateGmailRunner extends Setup {

    @Test(description = "Update gmail from user profile with unregistered gmail", priority = 1)
    public void updateGmail1() throws IOException, ConfigurationException, InterruptedException {
        LoginUser loginUser = new LoginUser(driver);

        Utils utils = new Utils();
        utils.getEnvVar();

        String email = utils.prop.getProperty("email");
        String password = utils.prop.getProperty("newPassword");

        loginUser.doLogin(email,password);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h2")));

        Faker faker = new Faker();
        String newGmail = "ashrarbd09+ok" + faker.number().digits(4)+ "@gmail.com";
        UpdateGmail updateGmail = new UpdateGmail(driver);
        updateGmail.doUpdateGmail(newGmail);
        utils.alertMessage(driver);

        utils.setEnvVar("update_gmail", newGmail);

        loginUser.doLogout();
    }

    @Test(description = "Update gmail from user profile with unregistered gmail", priority = 2)
    public void updateGmail2() throws IOException, ConfigurationException, InterruptedException {
        LoginUser loginUser = new LoginUser(driver);

        String email = "ashrarbd09+71714@gmail.com";
        String password = "1234";

        loginUser.doLogin(email,password);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h2")));

        String registeredGmail = "ashrarbd09+26945@gmail.com";
        UpdateGmail updateGmail = new UpdateGmail(driver);
        updateGmail.doUpdateGmail(registeredGmail);

        Thread.sleep(1500);
        String actText = driver.findElement(By.cssSelector("p")).getText();
        String expText = "Failed to update user.";
        Assert.assertTrue(actText.contains(expText));
        System.out.println(actText);
    }


}
