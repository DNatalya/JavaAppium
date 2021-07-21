package lib.ui;


import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import io.appium.java_client.AppiumDriver;
import lib.Platform;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class ArticlePageObject extends MainPageObject{

    protected static String TITLE,
                            FOOTER_ELEMENT,
                            OPTIONS_BUTTON,
                            OPTION_ADD_TO_MY_LIST,
                            OPTION_ADD_TO_MY_LIST_OVERLAY,
                            OPTION_REMOVE_FROM_MY_LIST_BUTTON,
                            MY_LIST_NAME_INPUT,
                            MY_LIST_ITEM_TPL,
                            BUTTON_OK,
                            CLOSE_ARTICLE_BUTTON;

    public ArticlePageObject (RemoteWebDriver driver){
        super(driver);
    }

    @Step("Waiting for title element of article")
    public WebElement waitForTitleElement() {
        return this.waitForElementPresent(TITLE,
                "Page title not present",
                5);
    }

    @Step("Get title of article")
    public String getArticleTitle(){
        WebElement title_element =  waitForTitleElement();
        screenshot(this.takeScreenshot("article_title"));
        if (Platform.getInstance().isAndroid())
        {
            return title_element.getText();
        }
        else if (Platform.getInstance().isIos())
        {
            return title_element.getAttribute("name");
        }
        else
        {
            return title_element.getText();
        }
    }

    private static String getListItemNameXpath(String substring)
    {
        return MY_LIST_ITEM_TPL.replace("{SUBSTRING}", substring);
    }

    @Step("Swipe to footer of article page")
    public void swipeToFooter(){
        if (Platform.getInstance().isAndroid()){
        this.swipeUpToFindElement(FOOTER_ELEMENT,
                "Cannot find link to browser ", 40);
        }
        else if (Platform.getInstance().isIos()){
            this.swipeUpTillElementAppear(FOOTER_ELEMENT,
                    "Cannot find link to browser ",
                    40);
        }
        else {
            this.ScrollWebPageTillElementNotVisible(FOOTER_ELEMENT,
                    "Cannot find link to browser ",
                    40);
        }
        screenshot(this.takeScreenshot("article_footer"));
    }

    @Step("Add article to new list")
    public void addArticleToNewList(String name_of_folder)
    {
        if (Platform.getInstance().isAndroid()) {
            this.waitForElementAndClick(
                    OPTIONS_BUTTON,
                    "Cannot find button to open article options", 5);
            this.waitForElementAndClick(
                    OPTION_ADD_TO_MY_LIST,
                    "Cannot find option to add article to reading list", 5);
            this.waitForElementAndClick(
                    OPTION_ADD_TO_MY_LIST_OVERLAY,
                    "Cannot find 'Got it' tip overlay", 5);
            this.waitForElementAndClear(
                    MY_LIST_NAME_INPUT,
                    "Cannot find input to set name articles folder", 5);
            this.waitForElementAndSendKeys(
                    MY_LIST_NAME_INPUT,
                    name_of_folder,
                    "Cannot put text into articles folder", 5);
            this.waitForElementAndClick(
                    BUTTON_OK,
                    "Cannot press 'OK' button", 5);
        }
        else
        {
            this.waitForElementAndClick(OPTION_ADD_TO_MY_LIST,
                    "Cannot find option to add article to reading list", 5);
        }
    }

    @Step("Add article to saved list")
    public void addArticleToMyList(String name_of_folder) {
        if (Platform.getInstance().isAndroid()) {
            this.waitForElementAndClick(
                    OPTIONS_BUTTON,
                    "Cannot find button to open article options", 5);
            this.waitForElementAndClick(
                    OPTION_ADD_TO_MY_LIST,
                    "Cannot find option to add article to reading list", 5);
            String ListItemNameXpath = getListItemNameXpath(name_of_folder);
            this.waitForElementAndClick(
                    ListItemNameXpath,
                    "Cannot find my list to add article in it", 5);
        }
        else if (Platform.getInstance().isIos()) {
            this.waitForElementAndClick(OPTION_ADD_TO_MY_LIST,
                    "Cannot find option to add article to reading list", 5);
        }
        else
        {
            this.removeArticleFromSavedIfItAdded();
        }
    }

    @Step("Remove article from saved list")
    public void removeArticleFromSavedIfItAdded(){
        if(this.isElementPresent(OPTION_REMOVE_FROM_MY_LIST_BUTTON)){
            this.waitForElementAndClick(OPTION_REMOVE_FROM_MY_LIST_BUTTON,
                    "Cannot click button to remove an article from saved", 1);
        }
        this.waitForElementPresent(OPTION_ADD_TO_MY_LIST, "Cannot find button to add an article to saved list " +
                "after removing it from this list before", 5);
    }

    @Step("Close article page")
    public void closeArticle() {
            this.waitForElementAndClick(
                    CLOSE_ARTICLE_BUTTON,
                    "Cannot close article, cannot find X link", 5);
        }
}
