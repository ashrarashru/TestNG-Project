package Classes;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.devtools.v134.input.model.MouseButton;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class AddItem {

    @FindBy(className = "add-cost-button")
    public WebElement addBtn;

    @FindBy(id="itemName")
    WebElement itemName;

    @FindBy(css = "[type=button]")
    List <WebElement> quantity; // get 2

    @FindBy(id = "amount")
    WebElement amount;

    @FindBy(id = "purchaseDate")
    WebElement date;


    @FindBy(id = "month")
    WebElement month;

    @FindBy(id = "remarks")
    WebElement remarks;

    @FindBy(css = "[type=submit]")
    WebElement submit;

    public AddItem(WebDriver driver){
        PageFactory.initElements(driver,this);
    }

    public void itemAdd(String item, String quantity2, String mon, String remark){
        itemName.sendKeys(item);
        quantity.get(2).click();
        amount.sendKeys(quantity2);
        //date.click();
        month.sendKeys(mon);
        remarks.sendKeys(remark);
        submit.click();
    }

    public void itemAdd(String item, String quantity2){
        itemName.sendKeys(item);
        amount.sendKeys(quantity2);
        submit.click();
    }

}
