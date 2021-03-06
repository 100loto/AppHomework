import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.net.URL;
import java.util.List;

public class CapabilitiesHelper {

    AppiumDriver driver;
//    private String class_PN = "Android";
//    private String class_DN = "Redmi_9";
//    private String class_PV = "10";
//    private String class_AP = "org.wikipedia";
//    private String class_AA = ".main.MainActivity";
//    private String class_APP = "/Users/a.vagin/Desktop/JavaAppium/AppHomework/apks/org.wikipedia_50355.apk";
//    private String class_AT = "Appium";

    private String class_PN = "Android";
    private String class_DN = "HONOR_AUTO";
    private String class_PV = "8";
    private String class_AP = "org.wikipedia";
    private String class_AA = ".main.MainActivity";
    private String class_APP = "/Users/a.vagin/Desktop/JavaAppium/AppHomework/apks/org.wikipedia.apk";
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
        driver.rotate(ScreenOrientation.PORTRAIT);

//        waitForElementAndClick(
//                By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
//                "no guide",
//                10
//        );

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

    protected void swipeUp(int timeOfSwipe)
    {
        TouchAction action = new TouchAction(driver);
        Dimension size = driver.manage().window().getSize();
        int x = size.width / 2;
        int start_y = (int) (size.height * 0.8);
        int end_y = (int) (size.height * 0.2);
        action
                .press(x, start_y)
                .waitAction(timeOfSwipe)
                .moveTo(x, end_y)
                .release()
                .perform();
    }

    protected void swipeUpQuick()
    {
        swipeUp(200);
    }

    protected void swipeUpToFindElement(By by, String error_message, int max_swipes)
    {
        int already_swiped = 0;
        while (driver.findElements(by).size() == 0){
            if (already_swiped > max_swipes){
                waitForElementPresent(by, "Cannot find element by swiping up. \n" + error_message, 0);
                return;
            }


            swipeUpQuick();
            ++already_swiped;
        }
    }
    protected void swipeElementToLeft(By by, String error_message)
    {
        WebElement element = waitForElementPresent(by, error_message, 10);
        int left_x = element.getLocation().getX();
        int right_x = left_x + element.getSize().getWidth();
        int upper_y = element.getLocation().getY();
        int lower_y = upper_y + element.getSize().getHeight();
        int middle_y = (upper_y + lower_y) / 2;

        TouchAction action = new TouchAction(driver);
        action
                .press(right_x, middle_y)
                .waitAction(150)
                .moveTo(left_x, middle_y)
                .release()
                .perform();

    }

    public int getAmountOfElements(By by)
    {
        List elements = driver.findElements(by);
        return elements.size();
    }

    public void assertElementNotPresent(By by, String error_message)
    {
        int amount_of_elements = getAmountOfElements(by);
        if (amount_of_elements > 0) {
            String default_message = "An element '" + by.toString() + "' supposed to be not present";
            throw new AssertionError(default_message + " " + error_message);
        }
    }

    public void assertElementPresent(By by, String error_message)
    {
        int element_present = getAmountOfElements(by);
        if (element_present == 1) {
            String default_message = "An element '" + by.toString() + "' supposed to be present";
            throw new AssertionError(default_message + " " + error_message);
        }
    }

    public String waitForElementAndGetAttribute(By by, String attribute, String error_message, long timeoutInSeconds)
    {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        return element.getAttribute(attribute);
    }

}

