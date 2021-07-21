package tests;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import lib.CoreTestCase;
import lib.Platform;
import lib.ui.*;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.MyListsPageObjectFactory;
import lib.ui.factories.NavigationUIFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Assert;
import org.junit.Test;

@Epic("Tests for lists")
public class MyListsTests extends CoreTestCase {

    private static String name_of_folder = "programming language";
    private static final String login = "",
                                password = "";

    @Test
    @Features(value={@Feature(value="search"), @Feature(value="article"),@Feature(value="lists")})
    @DisplayName("Work with articles in list")
    @Description("Add articles to list and delete one of them, expected other is exist")
    @Step("Starting test testWorkWithList")
    @Severity(value= SeverityLevel.NORMAL)
    public void testWorkWithList()
    {
        SearchPageObject SearchPageObject =  SearchPageObjectFactory.get(driver);
        ArticlePageObject ArticlePageObject =  ArticlePageObjectFactory.get(driver);
        NavigationUI NavigationUI = NavigationUIFactory.get(driver);
        MyListsPageObject MyListsPageObject = MyListsPageObjectFactory.get(driver);

        String item_name = "Java";
        String item_name_for_delete = "Kotlin";

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(item_name_for_delete);
        SearchPageObject.clickByArticleWithSubstring(name_of_folder);
        ArticlePageObject.waitForTitleElement();
        String article_title = ArticlePageObject.getArticleTitle();
        ArticlePageObject.addArticleToNewList(name_of_folder);
        if (Platform.getInstance().isMW())
        {
            AuthorizationPageObject Auth = new AuthorizationPageObject(driver);
            Auth.clickAuthButton();
            Auth.enterLoginData(login, password);
            Auth.submitForm();

            ArticlePageObject.waitForTitleElement();
            Assert.assertEquals("We are not on the same page after the login", article_title,
                        ArticlePageObject.getArticleTitle());
        }
        ArticlePageObject.closeArticle();

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(item_name + " (" + name_of_folder + ")");
        SearchPageObject.clickByArticleWithSubstring(name_of_folder);
        ArticlePageObject.waitForTitleElement();
        ArticlePageObject.addArticleToMyList(name_of_folder);
        ArticlePageObject.closeArticle();

        if (Platform.getInstance().isAndroid())
        {
            NavigationUI.clickMyLists();
            MyListsPageObject.openFolderByName(name_of_folder);
            MyListsPageObject.swipeByArticleToDelete(item_name_for_delete);
            MyListsPageObject.openArticleFromList(item_name);

            Assert.assertEquals("Unexpected title", item_name + " (" + name_of_folder + ")", article_title);
        }
        else if (Platform.getInstance().isIos())
        {
            NavigationUI.clickMyLists();
            MyListsPageObject.closePopUp();
            MyListsPageObject.swipeByArticleToDelete(item_name_for_delete);
            Assert.assertTrue("There is no lists or more 1",MyListsPageObject.getAmountOfFoundedLists() == 1);
        }
        else
        {
            NavigationUI.OpenNavigation();
            NavigationUI.clickMyLists();
            MyListsPageObject.swipeByArticleToDelete(item_name_for_delete);
            Assert.assertTrue("There is no lists or more 1",MyListsPageObject.getAmountOfFoundedLists() == 1);
        }
    }
}
