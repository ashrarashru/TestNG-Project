package Classes;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class UserRegistration {

    @FindBy(css="a")
    public List<WebElement> registrationBtn; //get 1

    @FindBy(id="firstName")
    WebElement txtFirstName;

    @FindBy(id="lastName")
    WebElement txtLastName;

    @FindBy(id="email")
    WebElement txtEmail;

    @FindBy(id="password")
    WebElement txtPassword;

    @FindBy(id="phoneNumber")
    WebElement txtPhnNum;

    @FindBy(id="address")
    WebElement txtAddress;

    @FindBy(css = "[type=radio]") // get 0
    List<WebElement> genderMale;

    @FindBy(css = "[type=checkbox]")
    WebElement terms;

    @FindBy(css = "[type=submit]")
    WebElement submit;


    public UserRegistration(WebDriver driver){
        PageFactory.initElements(driver,this);
    }

    public void doRegistration(String fName, String lName, String email, String password, String phone, String address){
        txtFirstName.sendKeys(fName);
        txtLastName.sendKeys(lName);
        txtEmail.sendKeys(email);
        txtPassword.sendKeys(password);
        txtPhnNum.sendKeys(phone);
        txtAddress.sendKeys(address);
        genderMale.get(0).click();
        terms.click();
        submit.click();
    }
}
