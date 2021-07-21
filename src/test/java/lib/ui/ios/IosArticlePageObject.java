package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.ArticlePageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class IosArticlePageObject extends ArticlePageObject {
    static {
        TITLE = "id:Kotlin";
        FOOTER_ELEMENT = "id:View article in browser";
        OPTION_ADD_TO_MY_LIST = "id:Save for later";
        BUTTON_OK = "xpath://*[@text='OK']";
        CLOSE_ARTICLE_BUTTON = "xpath://XCUIElementTypeButton[@name='W']";
    }

    public IosArticlePageObject(RemoteWebDriver driver) {
        super(driver);
    }

}
