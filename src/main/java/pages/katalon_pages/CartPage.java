package pages.katalon_pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.BasePage;

import java.util.List;

public class CartPage extends BasePage {

    public CartPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public List<WebElement> getTableRows() {
        return driver.findElements(By.className("cart_item"));
    }

    public void waitForNumberOfRowsToBe(int expectedNumOfRows) {
        wait.until(ExpectedConditions
                .numberOfElementsToBe(By.className("cart_item"), expectedNumOfRows));
    }

    public WebElement getRemoveItemButtonByRow(int row) {
        return driver.findElement(
                By.xpath("//tbody/tr[contains(@class, 'cart_item')]["+row+"]/td/a"));
    }
}
