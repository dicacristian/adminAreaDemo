package pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.v85.page.Page;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductEditPage {

    public ProductEditPage(ChromeDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "Name")
    private WebElement productName;


    @FindBy(css = "button[name='save']")
    private WebElement saveButton;

    public WebElement getProductName() {
        return productName;
    }

    public WebElement getSaveButton() {
        return saveButton;
    }
}