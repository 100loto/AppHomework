import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.URL;

public class CapabilitiesHelper {

    private AppiumDriver driver;
    private String class_PN = "Android";
    private String class_DN = "Redmi_9";
    private String class_PV = "10 QKQ1.191215.002";
    private String class_AP = "org.wikipedia";
    private String class_AA = ".main.MainActivity";
    private String class_APP = "/Users/a.vagin/Desktop/JavaAppium/apks/org.wikipedia.apk";
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
    }

    @After
    public void tearDown()
    {
        System.out.println("Finish session");
        driver.quit();
    }
}
