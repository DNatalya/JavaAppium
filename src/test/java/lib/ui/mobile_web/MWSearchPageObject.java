package lib.ui.mobile_web;

import lib.ui.SearchPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWSearchPageObject extends SearchPageObject {
    static {
        SEARCH_INIT_ELEMENT = "css:button#searchIcon";
        SEARCH_INPUT = "xpath://form[@class='search-box']/input[@type='search']";
        SEARCH_CANCEL_BUTTON = "xpath://div[@class='header-action']/button[contains(@class,'cancel')]";
        SEARCH_RESULT_LIST = "css:ul.page-list";
        SEARCH_RESULT_ELEMENT = "css:ul.page-list>li.page-summary";
        SEARCH_RESULT_TPL = "xpath://div[@class='search-results-view']//li[contains(@title,'{TITLE}')]" +
                "//div[@class='wikidata-description'][contains(text(),'{DESCRIPTION}')]";
        SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath://li[contains(@title,'{SUBSTRING}')]";
    }

    public MWSearchPageObject(RemoteWebDriver driver) {
        super(driver);
    }
}
