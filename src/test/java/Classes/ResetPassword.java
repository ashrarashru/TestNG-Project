package Classes;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.decorators.WebDriverDecorator;

import java.util.List;

public class ResetPassword {

    @FindBy(css = "a")
    public List <WebElement> resetBtn; // get 0

    @FindBy(css = "[type=email]")
    WebElement inputMail;

    @FindBy(css = "[type=submit]")
    WebElement resetLink;


    @FindBy(css = "[type=password]")
    public List <WebElement> resetPassword1; // get 0

    @FindBy(css = "[type=password]")
    public List <WebElement> resetPassword2; // get 1


    @FindBy(css = "[type=submit]")
    public WebElement clickReset;

    public ResetPassword(WebDriver driver){
        PageFactory.initElements(driver,this);
    }

    public void doResetPassword(String mail){
        inputMail.sendKeys(mail);
        resetLink.click();
    }

    public void password(String pass1, String pass2){

        resetPassword1.get(0).sendKeys(pass1);
        resetPassword2.get(1).sendKeys(pass2);
    }
}
