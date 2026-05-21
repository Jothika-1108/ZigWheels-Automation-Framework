package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import utilities.ExcelUtility;

import java.time.Duration;
import java.util.List;

public class UpcomingBikesPage {

    WebDriver driver;

    public UpcomingBikesPage(WebDriver driver) {
        this.driver = driver;
    }

    // ✅ Locators
    By honda = By.xpath("//a[text()='Honda']");
    By bikeCards = By.xpath("//li[contains(@class,'modelItem')]");  // ✅ each card

    // ========================== ACTION ==========================

    public void selectHonda() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // ✅ Scroll to "Upcoming Bikes by Brand"
        WebElement section = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//h3[contains(text(),'Upcoming Bikes by Brand')]")));

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView(true);", section);

        // ✅ Wait & scroll to Honda
        WebElement hondaElement = wait.until(
                ExpectedConditions.elementToBeClickable(honda));

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView(true);", hondaElement);

        // ✅ JS Click (stable)
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].click();", hondaElement);
    }

    // ========================== LOGIC ==========================

    public void filterAndDisplayBikes(int maxPrice) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        List<WebElement> cards = wait.until(
                ExpectedConditions.presenceOfAllElementsLocatedBy(bikeCards));

        System.out.println("===== Bikes under " + maxPrice + " Lakh =====");

        // ✅ Create header once
        ExcelUtility.createBikeHeader();

        for (WebElement card : cards) {

            try {

                String bikeName = card.findElement(
                                By.xpath(".//a[@data-track-label='model-name']"))
                        .getText();

                String priceText = card.findElement(
                                By.xpath(".//div[contains(text(),'Rs')]"))
                        .getText();

                String launch = card.findElement(
                                By.xpath(".//div[contains(text(),'Expected')]"))
                        .getText();

                double priceValue = extractPrice(priceText);

                if (priceValue > 0 && priceValue < maxPrice) {

                    System.out.println(bikeName + " | " + priceText + " | " + launch);

                    // ✅ Write to Excel
                    ExcelUtility.writeBikeData(bikeName, priceText, launch);
                }

            } catch (Exception e) {
                // skip invalid card
            }
        }

    }


    // ========================== PRICE PARSER ==========================

    private double extractPrice(String priceText) {

        try {

            priceText = priceText.replace("Rs.", "").trim();

            // ✅ Case 1: Lakh format
            if (priceText.contains("Lakh")) {

                priceText = priceText.replace("Lakh", "")
                        .replace(",", "")
                        .trim();

                return Double.parseDouble(priceText);
            }

            // ✅ Case 2: Thousand format (e.g. 79,000)
            else {

                priceText = priceText.replace(",", "").trim();

                double value = Double.parseDouble(priceText);

                // convert to Lakh (divide by 100000)
                return value / 100000;
            }

        } catch (Exception e) {
            return 0;
        }
    }
}
