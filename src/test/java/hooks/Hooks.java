package hooks;

import factory.BaseClass;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import utilities.ExcelUtility;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import java.io.File;
import org.apache.commons.io.FileUtils;

public class Hooks {

    // ✅ Before every scenario → launch browser
    @Before
    public void setup() throws Exception {
        System.out.println("✅ HOOK IS RUNNING ✅");
        BaseClass.initializeBrowser();
    }

    // ✅ After each step → capture screenshot
    @AfterStep
    public void takeScreenshot(Scenario scenario) {

        try {
            TakesScreenshot ts = (TakesScreenshot) BaseClass.driver;

            // Attach to Cucumber report
            byte[] screenshot = ts.getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", "Step Screenshot");

            // Save to screenshots folder
            File src = ts.getScreenshotAs(OutputType.FILE);

            File dest = new File("screenshots/" +
                    scenario.getName().replaceAll(" ", "_") + ".png");

            FileUtils.copyFile(src, dest);

        } catch (Exception e) {
            System.out.println("Screenshot capture failed");
        }
    }

    // ✅ After scenario → close browser + save excel
    @After
    public void tearDown() {
        ExcelUtility.saveExcel();
        BaseClass.tearDown();
    }
}
