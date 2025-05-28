package Classes;

import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;

public class ReadGmail {

    WebDriver driver;

    public void login() throws IOException, ConfigurationException {

        getEnvVar();

        RestAssured.baseURI = "https://gmail.googleapis.com";
        String path = "/gmail/v1/users/me/messages";
        String content = "application/json";

        Response response = given().contentType(content)
                .header("Authorization", "Bearer "+properties.getProperty("gmail_token"))
                .when().get(path);

        //System.out.println(response.asString());
        JsonPath jsonPath = response.jsonPath();
        String id = jsonPath.get("messages[0].id");
        setEnvVar("id",id);
    }


    public void readGmailMessage() throws IOException {
        getEnvVar();

        RestAssured.baseURI = "https://gmail.googleapis.com";
        String path = "/gmail/v1/users/me/messages/";
        String content = "application/json";

        Response response = given().contentType(content)
                .header("Authorization", "Bearer "+properties.getProperty("gmail_token"))
                .when().get(path + properties.getProperty("id"));

        //System.out.println(response.asString());
        JsonPath jsonPath = response.jsonPath();
        String messages = jsonPath.get("snippet");
        System.out.println(messages);
    }

    public void getResetLink() throws IOException, InterruptedException, ConfigurationException {
        getEnvVar();

        RestAssured.baseURI = "https://gmail.googleapis.com";
        String path = "/gmail/v1/users/me/messages/";
        String content = "application/json";

        Response response = given().contentType(content)
                .header("Authorization", "Bearer "+properties.getProperty("gmail_token"))
                .when().get(path + properties.getProperty("id"));

        //System.out.println(response.asString());
        JsonPath jsonPath = response.jsonPath();
        String messages = jsonPath.get("snippet");
        //System.out.println(messages);

        String link = messages.substring(messages.indexOf("https:")).trim();
        //System.out.println(link);

        WebDriver driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.get(link);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h4")));

        String password = "aabbcc";

        ResetPassword resetPassword = new ResetPassword(driver);
        resetPassword.password(password,password);
        Thread.sleep(1000);
        resetPassword.clickReset.click();
        Thread.sleep(2000);

        String actText = driver.findElement(By.cssSelector("p")).getText();
        String expText = "Password reset successfully";
        Assert.assertTrue(actText.contains(expText));
        System.out.println(actText);
        setEnvVar("newPassword",password);
    }


    public Properties properties;
    public void getEnvVar() throws IOException {
        String path = "./src/test/resources/config.properties";
        properties = new Properties();
        FileInputStream fileInputStream = new FileInputStream(path);
        properties.load(fileInputStream);
    }

    public void setEnvVar(String key, String value) throws ConfigurationException {
        String path = "./src/test/resources/config.properties";

        PropertiesConfiguration propertiesConfiguration = new PropertiesConfiguration(path);
        propertiesConfiguration.setProperty(key,value);
        propertiesConfiguration.save();
    }
}
