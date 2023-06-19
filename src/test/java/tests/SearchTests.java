package tests;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageObjects.LoginPage;
import pageObjects.ProductEditPage;
import pageObjects.ProductPage;
import pageObjects.SideBarPage;

import java.time.Duration;

import static org.testng.Assert.*;
import static pageObjects.ProductPage.EMPTY_PRODUCT_LIST_CSS;
import static pageObjects.ProductPage.PRODUCT_LIST_CSS;

public class SearchTests extends BaseTest {

    @BeforeClass
    public void beforeClass() {
        driver.get("https://admin-demo.nopcommerce.com/");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.getLoginForm().submit();
    }

    @Test(description = "Verify that search is successful when searching for products by name")
    public void testSearchByProductName() {
        SideBarPage sideBarPage = new SideBarPage(driver);
        sideBarPage.getCatalogLink().click();
        sideBarPage.getProductList().click();

        ProductPage productPage = new ProductPage(driver);
        productPage.getProductName().sendKeys("Windows 8 Pro");
        productPage.getSearchButton().click();
        getWait(driver).until(ExpectedConditions.numberOfElementsToBe(By.cssSelector(PRODUCT_LIST_CSS), 1));
        assertEquals(productPage.getProductList().size(), 1, "Search did not return the expected products");
        assertEquals(productPage.getProductTitles().size(), productPage.getProductList().size(), "Product titles list has different size than product list!");
        assertEquals(productPage.getProductTitles().get(0).getText(), "Windows 8 Pro", "Searched Product title is not correct!");
    }

    @Test(description = "Verify that after editing a products, it is not longer in the search list")
    public void testEditProductName() {
        driver.get("https://admin-demo.nopcommerce.com/Admin/Product/List");
        ProductPage productPage = new ProductPage(driver);

        getWait(driver).until(ExpectedConditions.numberOfElementsToBeMoreThan(By.cssSelector(PRODUCT_LIST_CSS), 0));
        assertNotEquals(productPage.getProductList().size(), 0, "No products present!");

        WebElement firstProduct = productPage.getProductList().get(0);
        String firstProductTitle = productPage.getTitleFromProduct(firstProduct).getText();
        productPage.getEditButtonFromProduct(firstProduct).click();
        //edit product name

        ProductEditPage productEditPage = new ProductEditPage(driver);
        productEditPage.getProductName().clear();
        productEditPage.getProductName().sendKeys(RandomStringUtils.randomAlphanumeric(20));
        productEditPage.getSaveButton().click();

        //research the old product
        productPage.getProductName().sendKeys(firstProductTitle);
        productPage.getSearchButton().click();
        getWait(driver).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(EMPTY_PRODUCT_LIST_CSS)));
        assertEquals(productPage.getProductList().size(), 1,"Product list size after edit and search is not expected one");
        assertTrue(isElementPresent(By.cssSelector(EMPTY_PRODUCT_LIST_CSS)),"Product list is not empty when searching for an invalid product name");

    }

}
