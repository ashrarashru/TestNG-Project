package Runner;

import Classes.ReadGmail;
import Classes.UserRegistration;
import Setup.Setup;
import com.github.javafaker.Faker;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class UserRegistrationRunner extends Setup {

    @Test(description = "Register a new user", priority = 6)
    public void registerAUser() throws InterruptedException, IOException, ConfigurationException {
        UserRegistration userRegistration = new UserRegistration(driver);
        userRegistration.registrationBtn.get(1).click();
        Faker faker = new Faker();

        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String email = "ashrarbd09+" + faker.number().digits(5) + "@gmail.com";
        String password = "1234";
        String phoneNumber = "016" + faker.number().digits(8);
        String address = faker.address().fullAddress();

        userRegistration.doRegistration(firstName,lastName,email,password,phoneNumber,address);
        Thread.sleep(2000);

        String getMessage = driver.findElement(By.className("Toastify__toast")).getText();
        String expMessage = "registered successfully!";
        Assert.assertTrue(getMessage.contains(expMessage));
        System.out.println(getMessage);

        setEnvVar("newUser",email);

        ReadGmail readGmail = new ReadGmail();
        readGmail.login();
        readGmail.readGmailMessage();
    }

    @Test(description = "Register a new user with invalid name", priority = 1)
    public void registerAUser1() throws InterruptedException, IOException, ConfigurationException {
        UserRegistration userRegistration = new UserRegistration(driver);
        userRegistration.registrationBtn.get(1).click();
        Faker faker = new Faker();

        String firstName = "342ad";
        String lastName = "asd33w";
        String email = "ashrarbd09+" + faker.number().digits(5) + "@gmail.com";
        String password = "1234";
        String phoneNumber = "016" + faker.number().digits(8);
        String address = faker.address().fullAddress();

        userRegistration.doRegistration(firstName,lastName,email,password,phoneNumber,address);
        Thread.sleep(2000);

        String getMessage = driver.findElement(By.className("Toastify__toast")).getText();
        String expMessage = "registered successfully!";
        Assert.assertTrue(getMessage.contains(expMessage));
        System.out.println(getMessage);
        Thread.sleep(4000);
        //driver.navigate().refresh();
    }

    @Test(description = "Register a new user with wrong ESP", priority = 2)
    public void registerAUser2() throws InterruptedException, IOException, ConfigurationException {
        UserRegistration userRegistration = new UserRegistration(driver);
        userRegistration.registrationBtn.get(1).click();
        Faker faker = new Faker();

        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String email = "ashrarbd09+" + faker.number().digits(5) + "@yahoo.com";
        String password = "1234";
        String phoneNumber = "016" + faker.number().digits(8);
        String address = faker.address().fullAddress();

        userRegistration.doRegistration(firstName,lastName,email,password,phoneNumber,address);
        Thread.sleep(2000);

        String getMessage = driver.findElement(By.className("Toastify__toast")).getText();
        String expMessage = "Only Gmail addresses are accepted";
        Assert.assertTrue(getMessage.contains(expMessage));
        System.out.println(getMessage);
        Thread.sleep(3000);
        driver.navigate().refresh();
    }

    @Test(description = "Register a new user with characterized phone number", priority = 3)
    public void registerAUser3() throws InterruptedException, IOException, ConfigurationException {
        UserRegistration userRegistration = new UserRegistration(driver);
        //userRegistration.registrationBtn.get(1).click();
        Faker faker = new Faker();

        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String email = "ashrarbd09+" + faker.number().digits(5) + "@gmail.com";
        String password = "1234";
        //String phoneNumber = "016" + faker.number().digits(8);
        String phoneNumber = "abcdef";
        String address = faker.address().fullAddress();

        userRegistration.doRegistration(firstName,lastName,email,password,phoneNumber,address);
        Thread.sleep(2000);

        String getMessage = driver.findElement(By.className("Toastify__toast")).getText();
        String expMessage = "registered successfully!";
        Assert.assertTrue(getMessage.contains(expMessage));
        System.out.println(getMessage);
        Thread.sleep(5000);
        //driver.navigate().refresh();
    }

    @Test(description = "Register a new user with less than 11 digits", priority = 4)
    public void registerAUser4() throws InterruptedException, IOException, ConfigurationException {
        UserRegistration userRegistration = new UserRegistration(driver);
        userRegistration.registrationBtn.get(1).click();
        Faker faker = new Faker();

        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String email = "ashrarbd09+" + faker.number().digits(5) + "@gmail.com";
        String password = "1234";
        String phoneNumber = "016" + faker.number().digits(6);
        String address = faker.address().fullAddress();

        userRegistration.doRegistration(firstName,lastName,email,password,phoneNumber,address);
        Thread.sleep(2000);

        String getMessage = driver.findElement(By.className("Toastify__toast")).getText();
        String expMessage = "registered successfully!";
        Assert.assertTrue(getMessage.contains(expMessage));
        System.out.println(getMessage);
        Thread.sleep(5000);
        //driver.navigate().refresh();
    }
    @Test(description = "Register a new user with invalid address(special character)", priority = 5)
    public void registerAUser5() throws InterruptedException, IOException, ConfigurationException {
        UserRegistration userRegistration = new UserRegistration(driver);
        userRegistration.registrationBtn.get(1).click();
        Faker faker = new Faker();

        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String email = "ashrarbd09+" + faker.number().digits(5) + "@gmail.com";
        String password = "1234";
        String phoneNumber = "016" + faker.number().digits(8);
        String address = "$^&@#!";

        userRegistration.doRegistration(firstName,lastName,email,password,phoneNumber,address);
        Thread.sleep(2000);

        String getMessage = driver.findElement(By.className("Toastify__toast")).getText();
        String expMessage = "registered successfully!";
        Assert.assertTrue(getMessage.contains(expMessage));
        System.out.println(getMessage);
        Thread.sleep(4000);
        //driver.navigate().refresh();
    }

    public void setEnvVar(String key, String value) throws ConfigurationException {
        String path = "./src/test/resources/config.properties";

        PropertiesConfiguration propertiesConfiguration = new PropertiesConfiguration(path);
        propertiesConfiguration.setProperty(key,value);
        propertiesConfiguration.save();
    }
}
