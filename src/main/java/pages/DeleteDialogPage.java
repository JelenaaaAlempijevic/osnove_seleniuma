package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DeleteDialogPage extends BasePage {

    public DeleteDialogPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public void waitForDialogToBeVisible() {
        wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.id("delete")));
    }

    public void waitForDialogToBeInvisible() {
        wait.until(ExpectedConditions
                .invisibilityOfElementLocated(By.id("delete")));
    }

    public WebElement getDeleteButton() {
        return driver.findElement(By.id("del"));
    }
}