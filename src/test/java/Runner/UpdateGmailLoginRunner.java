package Runner;

import Classes.LoginUser;
import Setup.Setup;
import Utils.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class UpdateGmailLoginRunner extends Setup {

    private static final Logger log = LoggerFactory.getLogger(UpdateGmailLoginRunner.class);

    @Test(description = "Login with update gmail", priority = 1)
    public void UpdateGmailLogin() throws IOException {
        Utils utils = new Utils();
        utils.getEnvVar();
        LoginUser loginUser = new LoginUser(driver);

        String email = utils.prop.getProperty("update_gmail");
        String password = utils.prop.getProperty("newPassword");

        loginUser.doLogin(email,password);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h2")));

        String actText = driver.findElement(By.cssSelector("h2")).getText();
        String expText = "User Daily Costs";
        Assert.assertTrue(actText.contains(expText));

        loginUser.doLogout();
    }

    @Test(description = "Login with old gmail", priority = 2)
    public void oldGmailLogin() throws IOException {
        Utils utils = new Utils();
        utils.getEnvVar();
        LoginUser loginUser = new LoginUser(driver);

        String email = utils.prop.getProperty("email");
        String password = utils.prop.getProperty("newPassword");

        loginUser.doLogin(email,password);
        String actText = driver.findElement(By.cssSelector("p")).getText();
        String expText = "Invalid email or password";
        Assert.assertTrue(actText.contains(expText));
        driver.navigate().refresh();
    }

    @Test(description = "Login admin with CLI and search user", priority = 3)
    public void adminLoginWithCli() throws IOException {
        Utils utils = new Utils();
        utils.getEnvVar();

        LoginUser loginUser = new LoginUser(driver);
        loginUser.doLogin(System.getProperty("email"),System.getProperty("password"));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("total-count")));

        String email = utils.prop.getProperty("update_gmail");

        driver.findElement(By.className("search-box")).sendKeys(email);

        List<WebElement> search = driver.findElements(By.cssSelector("td"));
        String actResult = search.get(2).getText();
        String expResult = email;
        Assert.assertTrue(actResult.contains(expResult));
    }
}
