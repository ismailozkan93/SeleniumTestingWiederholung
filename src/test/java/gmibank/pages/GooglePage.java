package gmibank.pages;

import gmibank.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class GooglePage {


    public GooglePage(){
        PageFactory.initElements(Driver.getDriver(),this);
    }

    @FindBy( name = "q" )
    public WebElement searchBox;

    @FindBy(tagName = "a")
    public List<WebElement> alllinks;

    @FindBy(name = "btnK")
    public WebElement searchbtn;
}
