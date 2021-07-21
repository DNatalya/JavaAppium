package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.SearchPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class IosSearchPageObject extends SearchPageObject {

    static {
        SEARCH_INIT_ELEMENT = "xpath://XCUIElementTypeSearchField[@name='Search Wikipedia']";
        SEARCH_INPUT = "xpath://XCUIElementTypeSearchField[@value='Search Wikipedia']";
        SEARCH_CANCEL_BUTTON = "id:Clear text";
        SEARCH_RESULT_LIST = "xpath://XCUIElementTypeCollectionView";
        SEARCH_RESULT_ELEMENT = "xpath://XCUIElementTypeOther";
        SEARCH_RESULT_TPL = "xpath://XCUIElementTypeStaticText[contains(@name,'{TITLE}')]"  +
                "//following-sibling::XCUIElementTypeStaticText[contains(@value,'{DESCRIPTION}')]";
        SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath://XCUIElementTypeStaticText[contains(@name,'{SUBSTRING}')]";
    }

    public IosSearchPageObject(RemoteWebDriver driver) {
        super(driver);
    }
}