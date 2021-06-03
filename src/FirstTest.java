import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import sun.security.util.Length;

import java.util.List;

public class FirstTest extends CapabilitiesHelper{


    @Test
    public void testAssertSearchResult()
    {
        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_container"),
                "Java",
                "Cannot find search input",
                10
        );
        waitForElementPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_results_list']//*[@text='Object-oriented programming language']"),
                "Cannot find 'Object-oriented programming language' topic searching by 'Java'",
                10
        );
        List <WebElement> search_results = driver.findElementsByXPath("//*[@resource-id='org.wikipedia:id/page_list_item_title']");
        int count = search_results.size();
        if (count != 0){
        } else
            Assert.fail("list of result empty");
        waitForElementAndClick(
                By.id("org.wikipedia:id/search_close_btn"),
                "Cannot find clear button",
                5
        );
        assertElementHasText(
                By.id("org.wikipedia:id/search_src_text"),
                "Search Wikipedia",
                15
        );
        waitForElementNotPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title']"),
                "Search result still view on page",
                10
        );
        waitForElementPresent(
                By.id("org.wikipedia:id/search_empty_message"),
                "Cannot find text 'Search wiki in more lang'",
                5
        );
    }

    @Test
    public void testAssertTextInInput()
    {
        assertElementHasText(
                By.xpath("//*[@class='android.widget.TextView']"),
                "Search Wikipedia",
                15
        );
    }

    @Test
    public void TestCancelSearch()
    {
        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_container"),
                "Java",
                "Cannot find search input",
                10
        );
        waitForElementAndClear(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find search field",
                10
        );
        waitForElementAndClick(
                By.xpath("//*[@class='android.widget.ImageButton']"),
                "Cannot find return button",
                10
        );
        waitForElementNotPresent(
                By.xpath("//*[@class='android.widget.ImageButton']"),
                "Return button still present on the page",
                10
        );
    }


    @Test
    public void firstTest()
    {
//        waitForElementAndClick(
//                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
//                "Cannot find search input in main page",
//                10
//        );
        waitForElementAndSendKeys(
//                By.xpath("//*[@resource-id='org.wikipedia:id/search_src_text']//*[@text='Search Wikipedia']"),
                By.id("org.wikipedia:id/search_container"),
                "Java",
                "Cannot find search input",
                10
        );
        waitForElementPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_results_list']//*[@text='Object-oriented programming language']"),
                "Cannot find 'Object-oriented programming language' topic searching by 'Java'",
                10
                );

    }

    @Test
    public void testCompareArticleTitle()
    {
        waitForElementAndSendKeys(
//                By.xpath("//*[@resource-id='org.wikipedia:id/search_src_text']//*[@text='Search Wikipedia']"),
                By.id("org.wikipedia:id/search_container"),
                "Java",
                "Cannot find search input",
                10
        );
        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_results_list']//*[@text='Object-oriented programming language']"),
                "Cannot find article java lang",
                10
        );
        WebElement title_element = waitForElementPresent(
                By.xpath("//*[@class='android.view.View']//*[@text='Java (programming language)']"),
                "Cannot find title 'Java'",
                10
        );
        String article_title = title_element.getAttribute("text");
        Assert.assertEquals(
                "We see unexpected title",
                "Java (programming language)",
                article_title
        );

    }

}
