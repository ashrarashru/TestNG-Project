package Runner;

import Classes.LoginUser;
import Setup.Setup;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class LoginUserRunner extends Setup {

    @Test(description = "Login with retrieve password", priority = 1)
    public void login1() throws IOException {
        getEnvVar();
        LoginUser loginUser = new LoginUser(driver);
        String email = properties.getProperty("email");
        String password = properties.getProperty("newPassword");
        loginUser.doLogin(email,password);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h2")));

        String actMessage = driver.findElement(By.cssSelector("h2")).getText();
        String expMessage = "User Daily Costs";
        Assert.assertTrue(actMessage.contains(expMessage));

        loginUser.doLogout();
    }

    @Test(description = "Login with old password", priority = 2)
    public void login2() throws IOException {
        getEnvVar();
        LoginUser loginUser = new LoginUser(driver);
        String email = properties.getProperty("email");
        String password = "1234";
        loginUser.doLogin(email,password);
        String actMessage = driver.findElement(By.cssSelector("p")).getText();
        String expMessage = "Invalid email or password";
        Assert.assertTrue(actMessage.contains(expMessage));
    }

    public Properties properties;
    public void getEnvVar() throws IOException {
        String path = "./src/test/resources/config.properties";
        properties = new Properties();
        FileInputStream fileInputStream = new FileInputStream(path);
        properties.load(fileInputStream);
    }
}
