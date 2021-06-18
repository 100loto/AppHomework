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
    public void testAssertTitleWithoutWait()
    {
        String search_line = "Java";
        MainPageObject.waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_container"),
                search_line,
                "Cannot find search input",
                10
        );
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_results_list']//*[@text='Object-oriented programming language']"),
                "Cannot find article java island",
                10
        );
        MainPageObject.assertElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "Article title not found"
        );
    }

    @Test
    public void testTwoArticlesToListAndDeleteOne()
    {
        MainPageObject.waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_container"),
                "Java",
                "Cannot find search input",
                10
        );
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_results_list']//*[@text='Island of Indonesia']"),
                "Cannot find article java island",
                10
        );
        MainPageObject.waitForElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "Cannot find title",
                10
        );
        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.ImageView[@content-desc='More options']"),
                "Cannot find add_to_bm button",
                10
        );
        MainPageObject.waitForElementAndClick(
                By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.LinearLayout[3]/android.widget.RelativeLayout/android.widget.TextView"),
                "Cannot find option to add read_list",
                10
        );
        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/onboarding_button"),
                "Cannot find GOT_IT button",
                5
        );
        MainPageObject.waitForElementAndClear(
                By.id("org.wikipedia:id/text_input"),
                "Cannot find text input to set folder name",
                5
        );

        String name_of_folder = "Test folder";
        MainPageObject.waitForElementAndSendKeys(
                By.id("org.wikipedia:id/text_input"),
                name_of_folder,
                "Cannot put text in yo thr input for set folder name",
                5
        );
        MainPageObject.waitForElementAndClick(
                By.id("android:id/button1"),
                "Cannot find ok button",
                5
        );
        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Cannot close article, cannot find X link",
                20
        );
        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot click search input",
                5
        );
        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/voice_search_button"),
                "Cannot find clear search button",
                5
        );
        MainPageObject.waitForElementAndClick(
                By.id("android:id/button1"),
                "Cannot find ok button",
                5
        );
        MainPageObject.waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                "Linkin park",
                "Cannot find search input",
                10
        );
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_results_list']//*[@text='American rock band']"),
                "Cannot find article American alternative rock band",
                10
        );
        String title_from_NotDelete_article_before = MainPageObject.waitForElementAndGetAttribute(
                By.id("org.wikipedia:id/view_page_title_text"),
                "text",
                "Cannot find title of article",
                15
        );
        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.ImageView[@content-desc='More options']"),
                "Cannot find add_to_bm button",
                10
        );
        MainPageObject.waitForElementAndClick(
                By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.LinearLayout[3]/android.widget.RelativeLayout/android.widget.TextView"),
                "Cannot find option to add read_list",
                10
        );
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@text='Test folder']"),
                "Cannot find Test folder",
                10
        );
        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Cannot close article, cannot find X link",
                20
        );
        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.FrameLayout[@content-desc='My lists']"),
                "Cannot find navigate button to My List",
                20
        );
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@text='org.wikipedia:id/item_title']"),
                "Cannot find navigate button to My List",
                20
        );
        MainPageObject.swipeElementToLeft(
                By.xpath("//*[@text='Java']"),
                "Cannot find saved article"
        );
        MainPageObject.waitForElementNotPresent(
                By.xpath("//*[@text='Java']"),
                "Cannot delete article",
                5
        );
        int amount_of_search_results = MainPageObject.getAmountOfElements(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']")
        );
        assertTrue(
                "We found too few results!",
                amount_of_search_results == 1
        );
        MainPageObject.waitForElementPresent(
                By.xpath("//*[@text='Linkin Park']"),
                ""
        );
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@text='Linkin Park']"),
                "",
                5
        );
        String title_from_NotDelete_article_after = MainPageObject.waitForElementAndGetAttribute(
                By.id("org.wikipedia:id/view_page_title_text"),
                "text",
                "Cannot find title of article",
                15
        );
        assertEquals(
                "Article title deference",
                title_from_NotDelete_article_before,
                title_from_NotDelete_article_after
        );
    }


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
    public void testAssertSearchResult()
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
        int count = search_results.size();
        if (count != 0){
        } else
            fail("list of result empty");
        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/search_close_btn"),
                "Cannot find clear button",
                5
        );
        MainPageObject.assertElementHasText(
                By.id("org.wikipedia:id/search_src_text"),
                "Search Wikipedia",
                15
        );
        MainPageObject.waitForElementNotPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title']"),
                "Search result still view on page",
                10
        );
        MainPageObject.waitForElementPresent(
                By.id("org.wikipedia:id/search_empty_message"),
                "Cannot find text 'Search wiki in more lang'",
                5
        );
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
