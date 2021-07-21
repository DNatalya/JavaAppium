package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.MyListsPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class IosMyListsPageObject extends MyListsPageObject {
    static {
        ITEM_LIST = "xpath://XCUIElementTypeCollectionView";
        ITEM = "xpath://XCUIElementTypeCell";
        CLOSE_POP_UP="xpath://XCUIElementTypeButton[@name='Close']";
        ARTICLE_BY_TITLE_TPL = "xpath://XCUIElementTypeStaticText[contains(@name,'{ARTICLE_TITLE}')]";
    }

    public IosMyListsPageObject(RemoteWebDriver driver) {
        super(driver);
    }
}
