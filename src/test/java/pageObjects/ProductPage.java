package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ProductPage {

    public static final String PRODUCT_LIST_CSS = "#products-grid tbody tr";
    public static final String EMPTY_PRODUCT_LIST_CSS = "#products-grid tbody tr .dataTables_empty";

    public ProductPage(ChromeDriver driver) {
        PageFactory.initElements(driver, this);

    }


    @FindBy(id = "SearchProductName")
    private WebElement productName;

    @FindBy(id = "search-products")
    private WebElement searchButton;

    @FindBy(css = PRODUCT_LIST_CSS)
    private List<WebElement> productList;

    @FindBy(css = "#products-grid tbody tr td:nth-child(3)")
    private List<WebElement> productTitles;

    public WebElement getProductName() {
        return productName;
    }

    public WebElement getSearchButton() {
        return searchButton;
    }

    public List<WebElement> getProductList() {
        return productList;
    }

    public List<WebElement> getProductTitles() {
        return productTitles;
    }

    public WebElement getEditButtonFromProduct(WebElement product){
        return product.findElement(By.cssSelector("a.btn.btn-default"));
    }
    public WebElement getTitleFromProduct(WebElement product){
        return product.findElement(By.cssSelector("td:nth-child(3)"));
    }

}
