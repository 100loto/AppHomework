package lib;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.ios.IOSDriver;
import junit.framework.TestCase;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

public class IOSTestCase extends TestCase {

    protected AppiumDriver driver;
    private static String AppiumURL = "http://127.0.0.1:4723/wd/hub";

    private String class_PN = "tests/IOS";
    private String class_DN = "iPhone 12";
    private String class_PV = "14.5";
    private String class_APP = "/Users/a.vagin/Desktop/JavaAppium/AppHomework/apks/Wikipedia.app";

    @Override
    protected void setUp() throws Exception
    {
        super.setUp();
        DesiredCapabilities capabilities = new DesiredCapabilities();

        System.out.println("Start Session");

        capabilities.setCapability("platformName",class_PN);
        capabilities.setCapability("deviceName",class_DN);
        capabilities.setCapability("platformVersion",class_PV);
        capabilities.setCapability("app",class_APP);

        driver = new IOSDriver(new URL(AppiumURL), capabilities);
        driver.rotate(ScreenOrientation.PORTRAIT);

//        waitForElementAndClick(
//                By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
//                "no guide",
//                10
//        );

    }

    @Override
    protected void tearDown() throws Exception
    {
        System.out.println("Finish session");
        driver.quit();

        super.tearDown();
    }

    protected void rotateScreenPortrait()
    {
        driver.rotate(ScreenOrientation.PORTRAIT);
    }
    protected void rotateScreenLandscape()
    {
        driver.rotate(ScreenOrientation.LANDSCAPE);
    }

    protected void backgroundApp(int seconds)
    {
        driver.runAppInBackground(seconds);
    }
}
