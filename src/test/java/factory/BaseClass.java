package factory;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BaseClass {

    public static WebDriver driver;
    public static Properties prop;

    public static WebDriver initializeBrowser() throws IOException {

        FileInputStream file = new FileInputStream("./src/test/resources/config.properties");
        prop = new Properties();
        prop.load(file);

        String browser = prop.getProperty("browser");

        switch (browser.toLowerCase()) {

            case "chrome":
                ChromeOptions options = new ChromeOptions();

                // ✅ Disable notifications (VERY IMPORTANT)
                options.addArguments("--disable-notifications");

                driver = new ChromeDriver(options);
                break;

            case "edge":
                driver = new EdgeDriver();
                break;

            case "firefox":
                driver = new FirefoxDriver();
                break;

            default:
                System.out.println("Invalid browser... launching Chrome");
                driver = new ChromeDriver();
        }

        driver.manage().window().maximize();
        driver.get(prop.getProperty("appURL"));

        return driver;
    }

    public static void tearDown() {
        driver.quit();
    }
}