package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.SearchPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AndroidSearchPageObject extends SearchPageObject {

 static {
            SEARCH_INIT_ELEMENT = "xpath://*[contains(@text, 'Search Wikipedia')]";
             SEARCH_INPUT = "id:org.wikipedia:id/search_src_text";
             SEARCH_CANCEL_BUTTON = "id:org.wikipedia:id/search_close_btn";
             SEARCH_RESULT_LIST = "xpath://*[@resource-id='org.wikipedia:id/search_results_list']";
             SEARCH_RESULT_ELEMENT = "xpath://*[@resource-id='org.wikipedia:id/page_list_item_container']";
             SEARCH_RESULT_TPL = "xpath://android.widget.TextView[contains(@text,'{TITLE}')]" +
                     "//following-sibling::android.widget.TextView[contains(@text,'{DESCRIPTION}')]";
             SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath://*[@resource-id='org.wikipedia:id/page_list_item_container']" +
                     "//*[contains(@text,'{SUBSTRING}')]";
 }
    public AndroidSearchPageObject (RemoteWebDriver driver){
        super(driver);
    }
}
