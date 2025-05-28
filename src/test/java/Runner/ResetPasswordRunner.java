package Runner;
import Classes.ResetPassword;
import Setup.Setup;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ResetPasswordRunner extends Setup {

    @Test(description = "Reset with blank email", priority = 1)
    public void blankMail(){
        ResetPassword resetPassword = new ResetPassword(driver);

        resetPassword.resetBtn.get(0).click();
        String email = "";
        resetPassword.doResetPassword(email);

        WebElement emailInput = driver.findElement(By.cssSelector("input[type='email']"));

        JavascriptExecutor js = (JavascriptExecutor) driver;
        String validationMsg = (String) js.executeScript("return arguments[0].validationMessage;", emailInput);
        System.out.println(validationMsg);
        String expText = "Please fill out this field";
        Assert.assertNotNull(validationMsg);
        Assert.assertTrue(validationMsg.contains(expText));
    }

    @Test(description = "Reset with unregistered email", priority = 2)
    public void wrongMail(){
        setup();
        ResetPassword resetPassword = new ResetPassword(driver);

        resetPassword.resetBtn.get(0).click();
        String email = "ashrarbd@gmail.com";
        resetPassword.doResetPassword(email);

        String resetMessage = driver.findElement(By.cssSelector("p")).getText();
        String expMessage = "Your email is not registered";
        Assert.assertTrue(resetMessage.contains(expMessage));
        System.out.println(resetMessage);
    }

}
