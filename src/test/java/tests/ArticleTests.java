package tests;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import lib.CoreTestCase;
import lib.Platform;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

@Epic("Tests for articles")
public class ArticleTests extends CoreTestCase {

    @Test
    @Features(value={@Feature(value="search"), @Feature(value="article")})
    @DisplayName("Compare article title with expected one")
    @Description("We open 'Kotlin' article and make sure title is expected")
    @Step("Starting test testAssertTitle")
    @Severity(value= SeverityLevel.BLOCKER)
    public void testAssertTitle()
    {
        SearchPageObject SearchPageObject =  SearchPageObjectFactory.get(driver);
        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);

        String item_name= "Kotlin";
        String name_of_folder = "programming language";

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(item_name);
        SearchPageObject.clickByArticleWithSubstring(name_of_folder);
        ArticlePageObject.waitForTitleElement();

    }

    @Test
    @Features(value={@Feature(value="search"), @Feature(value="article")})
    @DisplayName("Swipe article to the footer")
    @Description("We open an article and swiped to the footer")
    @Step("Starting test testSwipeToFooterWithRotation")
    @Severity(value= SeverityLevel.MINOR)
    public void testSwipeToFooterWithRotation(){
        if (Platform.getInstance().isMW())
        {
        return;
        }

        this.rotateScreenLandscape();

        SearchPageObject SearchPageObject =  SearchPageObjectFactory.get(driver);
        ArticlePageObject ArticlePageObject =  ArticlePageObjectFactory.get(driver);

        String item_name = "Kotlin";
        String name_of_folder = "programming language";

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(item_name);
        this.rotateScreenPortrait();
        SearchPageObject.clickByArticleWithSubstring(name_of_folder);
        this.rotateScreenLandscape();
        ArticlePageObject.swipeToFooter();
    }

}
