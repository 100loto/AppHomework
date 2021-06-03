import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;

public class CapabilitiesHelper {

    AppiumDriver driver;
    private String class_PN = "Android";
    private String class_DN = "Redmi_9";
    private String class_PV = "10";
    private String class_AP = "org.wikipedia";
    private String class_AA = ".main.MainActivity";
    private String class_APP = "/Users/a.vagin/Desktop/JavaAppium/AppHomework/apks/org.wikipedia_50355.apk";
    private String class_AT = "Appium";




    @Before
    public void setUp() throws Exception
    {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        System.out.println("Start Session");

        capabilities.setCapability("platformName",class_PN);
        capabilities.setCapability("deviceName",class_DN);
        capabilities.setCapability("platformVersion",class_PV);
        capabilities.setCapability("automationName",class_AT);
        capabilities.setCapability("appPackage",class_AP);
        capabilities.setCapability("appActivity",class_AA);
        capabilities.setCapability("app",class_APP);

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);

        waitForElementAndClick(
                By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "no guide",
                10
        );

    }

    @After
    public void tearDown()
    {
        System.out.println("Finish session");
        driver.quit();
    }

    public WebElement waitForElementPresent(By by, String error_message, long timeoutInSeconds)
    {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public WebElement waitForElementPresent(By by, String error_message)
    {
        return waitForElementPresent(by, error_message, 5);
    }

    public WebElement waitForElementAndClick(By by, String error_message, long timeoutInSeconds)
    {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.click();
        return element;
    }

    public WebElement waitForElementAndSendKeys(By by, String value, String error_message, long timeoutInSeconds)
    {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.sendKeys(value);
        return element;
    }

    public boolean waitForElementNotPresent(By by, String error_message, long timeoutInSeconds)
    {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by)
        );
    }

    public WebElement waitForElementAndClear(By by, String error_message, long timeoutInSeconds)
    {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.clear();
        return element;
    }

    public WebElement assertElementHasText(By by, String expected_text, long timeoutInSecond)
    {
        WebElement element = waitForElementPresent(by, expected_text, timeoutInSecond);
        String actual_text = element.getAttribute("text");
        Assert.assertEquals("Element empty (words)", expected_text, actual_text);
        return element;
    }

}

