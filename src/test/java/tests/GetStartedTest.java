package tests;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import lib.CoreTestCase;
import lib.Platform;
import lib.ui.WelcomePageObject;
import org.junit.Test;

@Epic("Tests for welcome page on iOS")
public class GetStartedTest extends CoreTestCase {

    @Test
    @Features(value={@Feature(value="welcome pages")})
    @DisplayName("Pass through welcome pages")
    @Description("Skip all welcome pages by click on links")
    @Step("Starting test testPassThroughWelcome")
    @Severity(value= SeverityLevel.MINOR)
    public void testPassThroughWelcome()
    {
        if (Platform.getInstance().isAndroid() || Platform.getInstance().isMW()) {
            return;
        }
        WelcomePageObject WelcomePageObject = new WelcomePageObject(driver);

        WelcomePageObject.waitForLearnMoreLink();
        WelcomePageObject.clickNextButton();

        WelcomePageObject.waitForNewWayToExploreText();
        WelcomePageObject.clickNextButton();

        WelcomePageObject.waitForAddOrEditPrefferedLangText();
        WelcomePageObject.clickNextButton();

        WelcomePageObject.waitForLearnMoreAboutDataCollectionText();
        WelcomePageObject.clickGetStarted();
    }
}
