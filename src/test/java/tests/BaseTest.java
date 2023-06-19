package tests;

import driver.BrowserConfigs;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

import static pageObjects.ProductPage.PRODUCT_LIST_CSS;

public class BaseTest {

    protected ChromeDriver driver = null;


    @BeforeClass
    public void init() {

        driver = BrowserConfigs.getChromeDriver();
    }

    @AfterClass(alwaysRun = true)
    public void afterMethod() {
        if (driver != null) {
            driver.quit();
        }
    }

    protected FluentWait<ChromeDriver> getWait(ChromeDriver driver) {

        return new FluentWait<>(driver).withTimeout(Duration.ofSeconds(5)).pollingEvery(Duration.ofMillis(500)).ignoring(StaleElementReferenceException.class);

    }

    protected boolean isElementPresent(By locator) {
        try {
            driver.findElement(locator);
            return true;
        }catch (NoSuchElementException e){
            return false;
        }
    }
}
