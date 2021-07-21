package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.remote.RemoteWebDriver;


public class WelcomePageObject extends MainPageObject {
    private static final String
            STEP_LEARN_MORE_LINK = "id:Learn more about Wikipedia",
            STEP_NEW_WAYS_TO_EXPLORE_TEXT = "id:New ways to explore",
            STEP_ADD_OR_EDIT_PREFERRED_LANG_LINK ="id:Add or edit preferred languages",
            STEP_LEARN_MORE_ABOUT_DATA_COLLECTED_LINK="id:Learn more about data collected",
            NEXT_LINK = "id:Next",
            SKIP_BUTTON = "id:Skip",
            GET_STARTED_BUTTON = "id:Learn more about data collected";

    public WelcomePageObject (RemoteWebDriver driver){
        super(driver);
    }

    @Step("Waiting for 'Learn more about Wikipedia' link")
    public void waitForLearnMoreLink(){
        this.waitForElementPresent(STEP_LEARN_MORE_LINK,
                "Cannot find 'Learn more about Wikipedia' link", 10);
    }

    @Step("Waiting for 'New ways to explore' link")
    public void waitForNewWayToExploreText(){
        this.waitForElementPresent(STEP_NEW_WAYS_TO_EXPLORE_TEXT,
                "Cannot find 'New ways to explore' link", 10);
    }

    @Step("Waiting for 'Add or edit preferred languages' link")
    public void waitForAddOrEditPrefferedLangText(){
        this.waitForElementPresent(STEP_ADD_OR_EDIT_PREFERRED_LANG_LINK,
                "Cannot find 'Add or edit preferred languages' link", 10);
    }

    @Step("Waiting for 'Learn more about data collected' link")
    public void waitForLearnMoreAboutDataCollectionText(){
        this.waitForElementPresent(STEP_LEARN_MORE_ABOUT_DATA_COLLECTED_LINK,
                "Cannot find 'Learn more about data collected' link", 10);
    }

    @Step("Waiting for 'Next' button and click")
    public void clickNextButton(){
        this.waitForElementAndClick(NEXT_LINK,
                "Cannot find ans click 'Next' link", 10);
    }

    @Step("Waiting for 'Get started' link and click")
    public void clickGetStarted(){
        this.waitForElementAndClick(GET_STARTED_BUTTON,
                "Cannot find and click 'Get started' link", 10);
    }

    @Step("Waiting for 'Skip' button and click")
    public void clickSkip(){
        this.waitForElementAndClick(SKIP_BUTTON,
                "Cannot find and click 'Skip' link", 10);
    }
}


