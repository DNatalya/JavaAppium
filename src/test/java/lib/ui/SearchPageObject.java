package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.remote.RemoteWebDriver;

import static org.junit.Assert.fail;


abstract public class SearchPageObject extends MainPageObject{

            protected static String
             SEARCH_INIT_ELEMENT,
             SEARCH_INPUT,
             SEARCH_CANCEL_BUTTON,
             SEARCH_RESULT_LIST,
             SEARCH_RESULT_ELEMENT,
             SEARCH_RESULT_TPL,
             SEARCH_RESULT_BY_SUBSTRING_TPL;


    public SearchPageObject (RemoteWebDriver driver){
        super(driver);
    }

    private static String getResultSearchElement(String substring)
    {
        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }

    private static String getListItemXpathByText(String title, String description){
        return SEARCH_RESULT_TPL.replace("{TITLE}",title).replace("{DESCRIPTION}",description);
    }

    @Step("Initializing the search input")
    public void initSearchInput(){
        this.waitForElementAndClick(SEARCH_INIT_ELEMENT,
                "Cannot find and click Search init element",
                10);
        this.waitForElementPresent(SEARCH_INPUT,
                "Cannot find Search input after click",
                10);
    }

    @Step("Comapre text of the search input")
    public void compareSearchInputText(String[] names){
        screenshot(this.takeScreenshot("search_input"));
        this.assertElementHasText(SEARCH_INPUT, names, "Unexpected name of Search Wikipedia input");
    }

    @Step("Waiting for search input and enter the '{search_line}'")
    public void typeSearchLine(String search_line) {
        this.waitForElementAndSendKeys(SEARCH_INPUT, search_line,
                "Cannot find and type into search input",
                10);
    }

    @Step("Waiting for search result")
    public void waitForSearchResult(String substring){
        String search_result_xpath = getResultSearchElement(substring);
            this.waitForElementPresent(search_result_xpath,
                    "Cannot find search result with substring "+ substring,
                    15);
    }

    @Step("Waiting for article with substring and click")
    public void clickByArticleWithSubstring(String substring){
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementAndClick(search_result_xpath,
                "Cannot find and click search result with substring "+ substring,
                20);
    }

    @Step("Waiting for cancel button to appear")
    public void waitForCancelButtonToAppear(){
        this.waitForElementPresent(SEARCH_CANCEL_BUTTON,
                "Cannot find Search close button",
                5);
    }

    @Step("Waiting for cancel button to disappear")
    public void waitForResultToDisappear(String substring){
        String search_result_xpath = getResultSearchElement(substring);
        screenshot(this.takeScreenshot("search_result_before_cancel"));
        this.waitForElementNotPresent(search_result_xpath,
                "Search result is still present",
                5);
    }

    @Step("Waiting for cancel button and click")
    public void clickCancelSearch(){
        this.waitForElementAndClick(SEARCH_CANCEL_BUTTON,
                "Cannot find  and click Search close button",
                5);
    }

    public int getAmountOfFoundArticles(){
        return this.countFoundedItems(
                SEARCH_RESULT_LIST,
                SEARCH_RESULT_ELEMENT,
                "List of items not present on the page");
    }

    @Step("Waiting for article by title '{title}' and description '{description}'")
    public void waitForElementByTitleAndDescription(String title, String description) {
        String list_item_xpath = getListItemXpathByText(title, description);
        screenshot(this.takeScreenshot("search_result"));
        if (getAmountOfFoundArticles()>=3) {
            waitForElementPresent(list_item_xpath,
                    "There is no topics with title '" + title + "' and description '" + description + "'",
                    10);
        }
        else fail("Less than 3 articles were founded");
    }

}
