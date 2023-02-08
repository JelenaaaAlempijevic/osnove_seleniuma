package pages.katalon_pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.BasePage;

public class NavPage extends BasePage {

    public NavPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public WebElement getMyAccountLink() {
        return driver.findElement(By.linkText("MY ACCOUNT"));
    }

    public WebElement getSamplePageLink() {
        return driver.findElement(By.linkText("SAMPLE PAGE"));
    }

    public WebElement getCheckoutLink() {
        return driver.findElement(By.linkText("CHECKOUT"));
    }

    public WebElement getCartLink() {
        return driver.findElement(By.linkText("CART"));
    }

    public WebElement getShopLink() {
        return driver.findElement(By.linkText("SHOP"));
    }


}