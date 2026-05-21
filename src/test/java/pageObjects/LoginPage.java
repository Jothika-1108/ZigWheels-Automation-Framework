package pageObjects;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {

    WebDriver driver;
    WebDriverWait wait;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    // ================= LOCATORS =================

    By loginIcon = By.id("des_lIcon");
    By googleBtn = By.className("googleSignIn");
    By emailField = By.id("identifierId");
    By nextBtn = By.xpath("//span[text()='Next']");
    By errorMsg = By.xpath("//div[contains(text(),'valid email')]");

    // ================= ACTIONS =================

    public void clickLoginIcon() {
        WebElement login = wait.until(
                ExpectedConditions.presenceOfElementLocated(loginIcon));

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].click();", login);
    }

    public void clickGoogle() {

        WebElement google = wait.until(
                ExpectedConditions.presenceOfElementLocated(googleBtn));

        Actions actions = new Actions(driver);
        actions.moveToElement(google).pause(1000).perform();

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].click();", google);

        System.out.println("✅ Google button clicked");
    }

    public void switchToGoogleWindow() {

        new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(d -> d.getWindowHandles().size() > 1);

        for (String handle : driver.getWindowHandles()) {
            driver.switchTo().window(handle);
        }

        System.out.println("✅ Switched to Google window");
    }

    public void enterEmail(String email) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(emailField))
                .sendKeys(email);
    }

    public void clickNext() {
        wait.until(ExpectedConditions.elementToBeClickable(nextBtn))
                .click();
    }

    // ✅ ONLY VALIDATION (NO SCREENSHOT HERE)
    public String getErrorMessage() {

        WebElement error = wait.until(
                ExpectedConditions.visibilityOfElementLocated(errorMsg));

        String errorText = error.getText();

        System.out.println("===== LOGIN ERROR =====");
        System.out.println(errorText);

        return errorText;
    }
}