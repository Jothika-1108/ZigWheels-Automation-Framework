package pageObjects;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utilities.ExcelUtility;

import java.time.Duration;
import java.util.List;

public class UsedCarsPage {

    WebDriver driver;
    WebDriverWait wait;

    public UsedCarsPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Navigation
    By moreMenu = By.xpath("//span[contains(text(),'MORE')]");
    By usedCars = By.xpath("//a[@data-track-label='nav-used-car']");
    By chennai = By.xpath("//a[text()='Chennai']");

    // DIRECT locator using DOM
    By popularModels = By.xpath("//ul[contains(@class,'popularModels')]//label");

    // ========================== ACTION ==========================

    public void navigateToChennaiUsedCars() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Hover MORE
        WebElement more = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//span[contains(text(),'MORE')]")));

        new Actions(driver).moveToElement(more).perform();

        // Click Used Cars
        WebElement usedCars = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[@data-track-label='nav-used-car']")));

        usedCars.click();

        // Wait for popup container
        WebElement popup = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[contains(@class,'modal-content')]")));

        // Locate Chennai using attribute
        WebElement chennai = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//a[@data-value='Chennai']")));

        // Scroll to element
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView(true);", chennai);

        // JS CLICK
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].click();", chennai);
    }


    // ========================== EXTRACTION ==========================

    public void getPopularModels() {

        // ✅ Header
        ExcelUtility.createCarHeader();

        List<WebElement> models = wait.until(
                ExpectedConditions.presenceOfAllElementsLocatedBy(popularModels));

        System.out.println("===== Popular Models in Chennai =====");

        for (WebElement model : models) {

            String modelName = model.getText().trim();

            if (!modelName.isEmpty()) {

                System.out.println(modelName);

                // ✅ Write to Excel
                ExcelUtility.writeCarData(modelName);
            }
        }

    }
}
