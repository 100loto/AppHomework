import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.ScreenOrientation;
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
    public void testTwoArticlesToListAndDeleteOne()
    {
        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_container"),
                "Java",
                "Cannot find search input",
                10
        );
        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_results_list']//*[@text='Island of Indonesia']"),
                "Cannot find article java island",
                10
        );
        waitForElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "Cannot find title",
                10
        );
        waitForElementAndClick(
                By.xpath("//android.widget.ImageView[@content-desc='More options']"),
                "Cannot find add_to_bm button",
                10
        );
        waitForElementAndClick(
                By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.LinearLayout[3]/android.widget.RelativeLayout/android.widget.TextView"),
                "Cannot find option to add read_list",
                10
        );
        waitForElementAndClick(
                By.id("org.wikipedia:id/onboarding_button"),
                "Cannot find GOT_IT button",
                5
        );
        waitForElementAndClear(
                By.id("org.wikipedia:id/text_input"),
                "Cannot find text input to set folder name",
                5
        );

        String name_of_folder = "Test folder";
        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/text_input"),
                name_of_folder,
                "Cannot put text in yo thr input for set folder name",
                5
        );
        waitForElementAndClick(
                By.id("android:id/button1"),
                "Cannot find ok button",
                5
        );
        waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Cannot close article, cannot find X link",
                20
        );
        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot click search input",
                5
        );
        waitForElementAndClick(
                By.id("org.wikipedia:id/voice_search_button"),
                "Cannot find clear search button",
                5
        );
        waitForElementAndClick(
                By.id("android:id/button1"),
                "Cannot find ok button",
                5
        );
        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                "Linkin park",
                "Cannot find search input",
                10
        );
        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_results_list']//*[@text='American rock band']"),
                "Cannot find article American alternative rock band",
                10
        );
        String title_from_NotDelete_article_before = waitForElementAndGetAttribute(
                By.id("org.wikipedia:id/view_page_title_text"),
                "text",
                "Cannot find title of article",
                15
        );
        waitForElementAndClick(
                By.xpath("//android.widget.ImageView[@content-desc='More options']"),
                "Cannot find add_to_bm button",
                10
        );
        waitForElementAndClick(
                By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.LinearLayout[3]/android.widget.RelativeLayout/android.widget.TextView"),
                "Cannot find option to add read_list",
                10
        );
        waitForElementAndClick(
                By.xpath("//*[@text='Test folder']"),
                "Cannot find Test folder",
                10
        );
        waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Cannot close article, cannot find X link",
                20
        );
        waitForElementAndClick(
                By.xpath("//android.widget.FrameLayout[@content-desc='My lists']"),
                "Cannot find navigate button to My List",
                20
        );
        waitForElementAndClick(
                By.xpath("//*[@text='org.wikipedia:id/item_title']"),
                "Cannot find navigate button to My List",
                20
        );
        swipeElementToLeft(
                By.xpath("//*[@text='Java']"),
                "Cannot find saved article"
        );
        waitForElementNotPresent(
                By.xpath("//*[@text='Java']"),
                "Cannot delete article",
                5
        );
        int amount_of_search_results = getAmountOfElements(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']")
        );
        Assert.assertTrue(
                "We found too few results!",
                amount_of_search_results == 1
        );
        waitForElementPresent(
                By.xpath("//*[@text='Linkin Park']"),
                ""
        );
        waitForElementAndClick(
                By.xpath("//*[@text='Linkin Park']"),
                "",
                5
        );
        String title_from_NotDelete_article_after = waitForElementAndGetAttribute(
                By.id("org.wikipedia:id/view_page_title_text"),
                "text",
                "Cannot find title of article",
                15
        );
        Assert.assertEquals(
                "Article title deference",
                title_from_NotDelete_article_before,
                title_from_NotDelete_article_after
        );
        
    }


    @Test
    public void testCheckSearchArticleInBackground()
    {
        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_container"),
                "Java",
                "Cannot find search input",
                10
        );
        waitForElementPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_results_list']//*[@text='Island of Indonesia']"),
                "Cannot find article java lang",
                10
        );
        driver.runAppInBackground(2);
        waitForElementPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_results_list']//*[@text='Island of Indonesia']"),
                "Cannot find article after returning from background",
                10
        );
    }

    @Test
    public void testChangeScreenOrientationOnSearchResults()
    {
        String search_line = "Java";
        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_container"),
                search_line,
                "Cannot find search input",
                10
        );
        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_results_list']//*[@text='Object-oriented programming language']"),
                "Cannot find article 'java lang' topic searching by " + search_line,
                15
        );
        String title_before_rotation = waitForElementAndGetAttribute(
                By.id("org.wikipedia:id/view_page_title_text"),
                "text",
                "Cannot find title of article",
                15
        );
        driver.rotate(ScreenOrientation.LANDSCAPE);

        String title_after_rotation = waitForElementAndGetAttribute(
                By.id("org.wikipedia:id/view_page_title_text"),
                "text",
                "Cannot find title of article",
                15
        );
        Assert.assertEquals(
                "Article title have been changed after screen rotation",
                title_before_rotation,
                title_after_rotation
        );
        driver.rotate(ScreenOrientation.PORTRAIT);

        String title_after_second_rotation = waitForElementAndGetAttribute(
                By.id("org.wikipedia:id/view_page_title_text"),
                "text",
                "Cannot find title of article",
                15
        );
        Assert.assertEquals(
                "Article title have been changed after screen rotation",
                title_before_rotation,
                title_after_second_rotation
        );
    }


    @Test
    public void testAmountOfEmptySearch()
    {
        String search_line = "zzzzzzzzzzzz";
        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_container"),
                search_line,
                "Cannot find search input",
                10
        );
        String search_result_locator = "//*[@resource-id='org.wikipedia:id/fragment_search_results']/*[@resource-id='org.wikipedia:id/search_results_container']";
        String empty_result_label = "//*[@text='No results found']";
        waitForElementPresent(
                By.xpath(empty_result_label),
                "Cannot find empty result label by the request " + search_line,
                15
        );
        assertElementNotPresent(
                By.xpath(search_result_locator),
                "We've found some results by request " + search_line
        );

    }


    @Test
    public void testAmountOfNotEmptySearch()
    {
        String search_line = "Linkin park Diskography";
        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_container"),
                search_line,
                "Cannot find search input",
                10
        );
        String search_result_locator = "//*[@resource-id='org.wikipedia:id/fragment_search_results']/*[@resource-id='org.wikipedia:id/search_results_container']";
        waitForElementPresent(
                By.xpath(search_result_locator),
                "Cannot find anything by the request " + search_line,
                15
        );
        int amount_of_search_results = getAmountOfElements(
                By.xpath(search_result_locator)
        );
        Assert.assertTrue(
                "We found too few results!",
                amount_of_search_results > 0
        );


    }


    @Test
    public void testFirstArticleToMyList()
    {
        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_container"),
                "Java",
                "Cannot find search input",
                10
        );
        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_results_list']//*[@text='Island of Indonesia']"),
                "Cannot find article java lang",
                10
        );
        waitForElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "Cannot find title 'Java'",
                10
        );
        waitForElementAndClick(
                By.xpath("//android.widget.ImageView[@content-desc='More options']"),
                "Cannot find add_to_bm button",
                10
        );
        waitForElementAndClick(
                By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.LinearLayout[3]/android.widget.RelativeLayout/android.widget.TextView"),
                "Cannot find option to add read_list",
                10
        );
        waitForElementAndClick(
                By.id("org.wikipedia:id/onboarding_button"),
                "Cannot find GOT_IT button",
                5
        );
        waitForElementAndClear(
                By.id("org.wikipedia:id/text_input"),
                "Cannot find text input to set folder name",
                5
        );

        String name_of_folder = "Learning programing";
        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/text_input"),
                name_of_folder,
                "Cannot put text in yo thr input for set folder name",
                5
        );
        waitForElementAndClick(
                By.id("android:id/button1"),
                "Cannot find ok button",
                5
        );
        waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Cannot close article, cannot find X link",
                20
        );
        waitForElementAndClick(
                By.xpath("//android.widget.FrameLayout[@content-desc='My lists']"),
                "Cannot find navigate button to My List",
                20
        );
        waitForElementAndClick(
                By.id("org.wikipedia:id/item_title"),
                "Cannot find navigate button to My List",
                20
        );
        swipeElementToLeft(
                By.xpath("//*[@text='Java']"),
                "Cannot find saved article"
        );
        waitForElementNotPresent(
                By.xpath("//*[@text='Java']"),
                "Cannot delete article",
                5
        );






    }

    @Test
    public void testSwipeArticle()
    {
        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_container"),
                "Appium",
                "Cannot find search input",
                10
        );
        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title'][@text='Appium']"),
                "Cannot find article Appium",
                10
        );
        swipeUpToFindElement(
          By.xpath("//*[@text='View article in browser']"),
          "Cannot find the end of the article",
                20
        );

    }

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
