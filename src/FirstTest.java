import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import sun.security.util.Length;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FirstTest extends CapabilitiesHelper{

//Написать тест, который делает поиск по какому-то слову. Например, JAVA. Затем убеждается, что в каждом результате поиска есть это слово.
// 1. Поиск
// 2. Список выдачи
// 3n . Перебор выдачи на наличие совпадения "Java"
// resource-id	org.wikipedia:id/page_list_item_title
// //*[@resource-id='org.wikipedia:id/page_list_item_title']//*[@class='android.view.ViewGroup']


    @Test
    public void testSearchResultContainsSearchRequest()
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

        for (int i=0; i<search_results.size(); i++){
            String words = search_results.get(i).getAttribute("text");
            assert words.contains("Java");
        }
        }






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
