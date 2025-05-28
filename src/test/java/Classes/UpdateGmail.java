package Classes;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class UpdateGmail {

    @FindBy(css = "[type=button]")
    WebElement profileIcon;

    @FindBy(css = "[role=menuitem]")
    List<WebElement> profileClick; // get 0

    @FindBy(css = "[type=button]")
    List<WebElement> editBtn; // get 1

    @FindBy(css = "[type=email]")
    WebElement editEmail;

    @FindBy(css = "[type=button]")
    List<WebElement> update; // get 2

    public UpdateGmail(WebDriver driver){
        PageFactory.initElements(driver,this);
    }

    public void doUpdateGmail(String mail){
        profileIcon.click();
        profileClick.get(0).click();
        editBtn.get(1).click();
        editEmail.sendKeys(Keys.CONTROL + "a", Keys.BACK_SPACE);
        editEmail.sendKeys(mail);
        update.get(2).click();
    }
}
