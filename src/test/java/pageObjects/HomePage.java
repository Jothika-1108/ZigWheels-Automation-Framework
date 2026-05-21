package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

public class HomePage {

    WebDriver driver;

    // Constructor
    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    // Locators
    By newBikesMenu = By.xpath("//span[contains(text(),'NEW BIKES')]");
    By upcomingBikes = By.xpath("//a[@data-track-label='nav-upcoming-bikes']");

    // Action method
    public void clickUpcomingBikes() {

        // Hover
        Actions act = new Actions(driver);
        act.moveToElement(driver.findElement(newBikesMenu)).perform();

        // Click
        driver.findElement(upcomingBikes).click();
    }
}
