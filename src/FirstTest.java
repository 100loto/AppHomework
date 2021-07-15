import lib.CoreTestCase;
import lib.ui.*;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.util.List;


public class FirstTest extends CoreTestCase {

    /* Удалить после рефакторинга всех тестов */
    private MainPageObject MainPageObject;


    protected void setUp() throws Exception
    {
        super.setUp();

        MainPageObject = new MainPageObject(driver);
    }
    /* Удалить после рефакторинга всех тестов */


    

    @Test
    public void testSearchResultContainsSearchRequest()
    {
        MainPageObject.waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_container"),
                "Java",
                "Cannot find search input",
                10
        );
        MainPageObject.waitForElementPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_results_list']//*[@text='Object-oriented programming language']"),
                "Cannot find 'Object-oriented programming language' topic searching by 'Java'",
                10
        );
        List <WebElement> search_results = driver.findElementsByXPath("//*[@resource-id='org.wikipedia:id/page_list_item_title']");

        for (int i=0; i<search_results.size(); i++){
            String words = search_results.get(i).getAttribute("text");
            assert words.contains("Java");
        }
    }



    @Test
    public void testAssertTextInInput()
    {
        MainPageObject.assertElementHasText(
                By.xpath("//*[@class='android.widget.TextView']"),
                "Search Wikipedia",
                15
        );
    }

}
