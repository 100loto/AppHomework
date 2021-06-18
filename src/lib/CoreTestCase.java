package lib;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import junit.framework.TestCase;
import org.openqa.selenium.By;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

public class CoreTestCase extends TestCase {

    protected AppiumDriver driver;
    private static String AppiumURL = "http://127.0.0.1:4723/wd/hub";

    private String class_PN = "Android";
    private String class_DN = "HONOR_AUTO";
    private String class_PV = "8";
    private String class_AP = "org.wikipedia";
    private String class_AA = ".main.MainActivity";
    private String class_APP = "/Users/a.vagin/Desktop/JavaAppium/AppHomework/apks/org.wikipedia.apk";
    private String class_AT = "Appium";

    @Override
    protected void setUp() throws Exception
    {
        super.setUp();
        DesiredCapabilities capabilities = new DesiredCapabilities();

        System.out.println("Start Session");

        capabilities.setCapability("platformName",class_PN);
        capabilities.setCapability("deviceName",class_DN);
        capabilities.setCapability("platformVersion",class_PV);
        capabilities.setCapability("automationName",class_AT);
        capabilities.setCapability("appPackage",class_AP);
        capabilities.setCapability("appActivity",class_AA);
        capabilities.setCapability("app",class_APP);

        driver = new AndroidDriver(new URL(AppiumURL), capabilities);
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
