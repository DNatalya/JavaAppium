package tests;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import lib.CoreTestCase;
import lib.ui.SearchPageObject;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

@Epic("Tests for searching")
public class SearchTests extends CoreTestCase {

    @Test
    @Features(value={@Feature(value="search")})
    @DisplayName("Compare search input name")
    @Description("Initializing search input and compare text to expected")
    @Step("Starting test testCompareSearchInputName")
    @Severity(value= SeverityLevel.MINOR)
    public void testCompareSearchInputName()
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        String[] names = {"Search…", "Search Wikipedia"};
        SearchPageObject.initSearchInput();
        SearchPageObject.compareSearchInputText(names);
    }

    @Test
    @Features(value={@Feature(value="search")})
    @DisplayName("Search by title and description")
    @Description("Looking for article by title and description in search results")
    @Step("Starting test testSearchByTitleAndDescription")
    @Severity(value= SeverityLevel.NORMAL)
    public void testSearchByTitleAndDescription()
    {
        SearchPageObject SearchPageObject =   SearchPageObjectFactory.get(driver);
        String value="Plumeria";

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(value);
        SearchPageObject.waitForElementByTitleAndDescription("Plumeria obtusa", "Species of");
    }

    @Test
    @Features(value={@Feature(value="search")})
    @DisplayName("Cancel Search")
    @Description("Search anything and then cancel")
    @Step("Starting test testCancelSearch")
    @Severity(value= SeverityLevel.CRITICAL)
    public void testCancelSearch()
    {
        SearchPageObject SearchPageObject =   SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("King");
        SearchPageObject.waitForCancelButtonToAppear();
        SearchPageObject.clickCancelSearch();
        SearchPageObject.waitForResultToDisappear("King");
    }

}
