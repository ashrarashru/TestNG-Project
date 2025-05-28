package Classes;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.devtools.v134.page.Page;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class LoginUser {
    @FindBy(id="email")
    WebElement txtEmail;

    @FindBy(id="password")
    WebElement txtPassword;

    @FindBy(css="[type=submit]")
    WebElement submitBtn;

    @FindBy(css="[type=button]") // get 0
    List<WebElement> profileIcon;

    @FindBy(css="[role=menuitem]") // get 1
    List<WebElement> logoutBtn;

    public LoginUser(WebDriver driver){
        PageFactory.initElements(driver,this);
    }

    public void doLogin(String email, String password){
        txtEmail.sendKeys(email);
        txtPassword.sendKeys(password);
        submitBtn.click();
    }

    public void doLogout(){
        profileIcon.get(0).click();
        logoutBtn.get(1).click();
    }
}
