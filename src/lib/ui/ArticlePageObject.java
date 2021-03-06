package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ArticlePageObject extends MainPageObject{

    public static final String
            FOLDER_BY_NAME_TPL = "//*[@text='{FOLDER_NAME}']";

    private static final String
            TITLE = "id:org.wikipedia:id/view_page_title_text",
            FOOTER_ELEMENT = "xpath://*[@text='View page in browser']",
            OPTIONS_BUTTON = "xpath://android.widget.ImageView[@content-desc='More options']",
            OPTIONS_ADD_TO_MY_LIST_BUTTON = "xpath:/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.LinearLayout[3]/android.widget.RelativeLayout/android.widget.TextView",
            ADD_TO_MY_LIST_OVERLAY = "id:org.wikipedia:id/onboarding_button",
            MY_LIST_NAME_INPUT = "id:org.wikipedia:id/text_input",
            MY_LIST_OK_BUTTON = "id:android:id/button1",
            CLOSE_ARTICLE_BUTTON = "xpath://android.widget.ImageButton[@content-desc='Navigate up']";




    public ArticlePageObject(AppiumDriver driver)
    {
        super(driver);
    }



    public void swipeToFooter()
    {
        this.swipeUpToFindElement(
                (FOOTER_ELEMENT),
                "Cannot find the end of article",
                20
        );
    }

    public void addArticleToMyList(String name_of_folder)
    {
        this.waitForElementAndClick(
                (OPTIONS_BUTTON),
                "Cannot find add_to_bm button",
                10
        );
        this.waitForElementAndClick(
                (OPTIONS_ADD_TO_MY_LIST_BUTTON),
                "Cannot find option to add read_list",
                10
        );
        this.waitForElementAndClick(
                (ADD_TO_MY_LIST_OVERLAY),
                "Cannot find GOT_IT button",
                5
        );
        this.waitForElementAndClear(
                (MY_LIST_NAME_INPUT),
                "Cannot find text input to set folder name",
                5
        );
        this.waitForElementAndSendKeys(
                (MY_LIST_NAME_INPUT),
                name_of_folder,
                "Cannot put text in yo thr input for set folder name",
                5
        );
        this.waitForElementAndClick(
                (MY_LIST_OK_BUTTON),
                "Cannot find ok button",
                5
        );
    }

    public void addArticleToCreatedMyList(String name_of_folder)
    {
        this.waitForElementAndClick(
                (OPTIONS_BUTTON),
                "Cannot find add_to_bm button",
                10
        );
        this.waitForElementAndClick(
                (OPTIONS_ADD_TO_MY_LIST_BUTTON),
                "Cannot find option to add read_list",
                10
        );
        this.waitForElementAndClick(
                (FOLDER_BY_NAME_TPL.replace("FOLDER_NAME", name_of_folder)),
                "Cannot find Test folder",
                10
        );
    }

    public void closeArticle()
    {
        this.waitForElementAndClick(
                (CLOSE_ARTICLE_BUTTON),
                "Cannot close article, cannot find X link",
                20
        );
    }

    public void assertTitlePresentInstantly()
    {
    this.assertElementPresent(
            (TITLE),
            "Article title not found instantly"
            );
    }

    public String saveArticleTitle()
    {
        String article_title = this.waitForElementAndGetAttribute((TITLE), "text", "Cannot find title of article", 15);
        return article_title;
    }

}
