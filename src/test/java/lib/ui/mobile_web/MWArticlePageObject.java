package lib.ui.mobile_web;

import lib.ui.ArticlePageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWArticlePageObject extends ArticlePageObject {
    static {
        TITLE = "css:#content h1";
        FOOTER_ELEMENT = "css:footer";
        OPTION_ADD_TO_MY_LIST = "xpath://*[@id='ca-watch']";
        CLOSE_ARTICLE_BUTTON = "xpath://div[@class='branding-box']";
        OPTION_REMOVE_FROM_MY_LIST_BUTTON = "xpath://*[contains(@title,'Remove this page')]";
    }

    public MWArticlePageObject(RemoteWebDriver driver) {
        super(driver);
    }

}
