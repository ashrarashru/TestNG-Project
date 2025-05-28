package Runner;

import Classes.AddItem;
import Classes.LoginUser;
import Setup.Setup;
import Utils.Utils;
import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;


import java.time.Duration;



public class AddItemRunner extends Setup {

    @Test(description = "Add all items", priority = 1)
    public void addAllField() throws InterruptedException {
        LoginUser loginUser = new LoginUser(driver);
        String email = "ashrarbd09+6576@gmail.com";
        String password = "1234";
        loginUser.doLogin(email,password);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h2")));

        AddItem addItem = new AddItem(driver);
        addItem.addBtn.click();
        String itemName = "Chips";
        String amount = "30";
        String month = "May";
        String remarks = "Good";

        addItem.itemAdd(itemName,amount,month,remarks);

        Utils utils = new Utils();
        utils.alertMessage(driver);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h2")));

        String expText = driver.findElement(By.cssSelector("h2")).getText();
        String actText = "User Daily Costs";
        Assert.assertTrue(expText.contains(actText));
    }

    @Test(description = "Add mandatory items", priority = 2)
    public void addMandatoryField() throws InterruptedException {

        AddItem addItem = new AddItem(driver);
        addItem.addBtn.click();
        String itemName = "Juice";
        String amount = "45";

        addItem.itemAdd(itemName,amount);

        Utils utils = new Utils();
        utils.alertMessage(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h2")));

        String expText = driver.findElement(By.cssSelector("h2")).getText();
        String actText = "User Daily Costs";
        Assert.assertTrue(expText.contains(actText));

    }
}
