package lib.ui;

import io.qameta.allure.Step;
import lib.Platform;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class MyListsPageObject extends MainPageObject{

    protected static String ITEM_LIST, ITEM, CLOSE_POP_UP, FOLDER_BY_NAME_TPL, ARTICLE_BY_TITLE_TPL, REMOVE_FROM_SAVED_BUTTON;

    private static String getFolderXpathByName(String folder_name){
        return FOLDER_BY_NAME_TPL.replace("{FOLDER_NAME}", folder_name);
    }

    private static String getArticleXpathByTitle(String item_name){
        return ARTICLE_BY_TITLE_TPL.replace("{ARTICLE_TITLE}", item_name);
    }
    private static String getRemoveLocatorByTitle(String article_title){
        return REMOVE_FROM_SAVED_BUTTON.replace("{TITLE}", article_title);
    }

    public MyListsPageObject (RemoteWebDriver driver){
        super(driver);
    }

    @Step("Waiting for folder with name '{name_of_folder}'and click")
    public void openFolderByName(String name_of_folder){
        String FolderNameXpath = getFolderXpathByName(name_of_folder);
        this.waitForElementAndClick(FolderNameXpath,
                "Cannot find folder by name " + name_of_folder,5);
    }

    @Step("Waiting for article with title '{item_name}' to appear")
    public void waitForArticleToAppearByTitle(String item_name){
        String ArticleTitleXpath = getArticleXpathByTitle(item_name);
        this.waitForElementPresent(ArticleTitleXpath,
                "Cannot find saved article by title "+item_name + "'", 5);
    }

    @Step("Waiting for article with title '{item_name}' to disappear")
    public void waitForArticleToDisappearByTitle(String item_name){
        String ArticleTitleXpath = getArticleXpathByTitle(item_name);
        this.waitForElementNotPresent(ArticleTitleXpath,
                "Article with title '"+item_name + "' is still present", 5);
    }

    @Step("Delete article by click button for mobile web or by swipe in other case")
    public void swipeByArticleToDelete(String item_name){
        String ArticleTitleXpath = getArticleXpathByTitle(item_name);
        waitForArticleToAppearByTitle(item_name);
        this.swipeElementToLeft(ArticleTitleXpath,
                "Cannot delete saved article");
        if(Platform.getInstance().isAndroid()) {
            waitForArticleToDisappearByTitle(item_name);
        }
        else if (Platform.getInstance().isIos())
        {
            this.clickElementToTheRightUpperCorner(ArticleTitleXpath, "Cannot find saved article");
        }
        else
        {
            String remove_locator = getRemoveLocatorByTitle(item_name);
            this.waitForElementAndClick(remove_locator,
                    "Cannot click button to remove article from saved",
                    10);
            driver.navigate().refresh();
        }
        screenshot(this.takeScreenshot("list_after_delete_article"));
    }

    @Step("Waiting for article and click in list page")
    public void openArticleFromList(String item_name){
        String ArticleNameXpath = getArticleXpathByTitle(item_name);
        screenshot(this.takeScreenshot("article_from_list"));
        this.waitForElementAndClick(
                ArticleNameXpath,
                "Cannot open article",5);
    }

    @Step("Wait for popup and click")
    public void closePopUp(){
        this.waitForElementAndClick(
                CLOSE_POP_UP,
                "Cannot find popup and close it",5);
    }

    public int getAmountOfFoundedLists()
    {
        return this.countFoundedItems(ITEM_LIST, ITEM, "There is no lists");
    }
}
