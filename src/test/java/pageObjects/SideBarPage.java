package pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SideBarPage {

    public SideBarPage(ChromeDriver driver){
        PageFactory.initElements(driver, this);
    }



    @FindBy(css = "nav.mt-2 .nav-item.has-treeview:nth-child(2)")
    private WebElement catalogLink;

    @FindBy(css = ".nav-item a[href='/Admin/Product/List']")
    private WebElement productList;

    public WebElement getCatalogLink() {
        return catalogLink;
    }

    public WebElement getProductList() {
        return productList;
    }
}
