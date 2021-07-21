package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Step;
import lib.Platform;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class NavigationUI extends MainPageObject{

    protected static String MY_LISTS_LINK,
                            OPEN_NAVIGATION;

    public NavigationUI (RemoteWebDriver driver){
        super(driver);
    }

    @Step("Waiting for lists button and click")
    public void clickMyLists()
    {
        screenshot(this.takeScreenshot("navigation"));
        if(Platform.getInstance().isMW())
        {
            this.tryClickElementWithFewAttempts(OPEN_NAVIGATION,
                    "Cannot find navigation button to my lists", 5);
        }
        else {
            this.waitForElementAndClick(
            MY_LISTS_LINK,
           "Cannot find navigation button to my lists",5);
            }
    }

    @Step("Waiting for navigation button and click")
    public void OpenNavigation(){
        if(Platform.getInstance().isMW()){
            this.waitForElementAndClick(OPEN_NAVIGATION, "Cannot find and click open navigation button",
                    5);
        }
        else {
            System.out.println("Method OpenNavigation does nothing for platform " +
                    Platform.getInstance().getPlatformVar());
        }
    }
}
