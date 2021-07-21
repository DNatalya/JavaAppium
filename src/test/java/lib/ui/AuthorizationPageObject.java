package lib.ui;

import io.qameta.allure.Step;
import lib.Platform;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AuthorizationPageObject extends MainPageObject {
    protected static String LOGIN_BUTTON="xpath://*[contains[text(),'Log in']]",
                            LOGIN_INPUT="css:input[name='wpName']",
                            PASSWORD_INPUT="css:input[name='wpPassword']",
                            SUBMIT_BUTTON="css:button#wpLoginAttempt";

    public AuthorizationPageObject (RemoteWebDriver driver){
        super(driver);
    }

    @Step("Waiting for auth button and click")
    public void clickAuthButton(){
        screenshot(this.takeScreenshot("login_form"));
        this.waitForElementPresent(LOGIN_BUTTON, "Cannot find auth button", 15);
        this.waitForElementAndClick(LOGIN_BUTTON, "Cannot find and click auth button", 15);
    }

    @Step("Enter '{login}', '{password}' in auth form")
    public void enterLoginData(String login, String password){
        this.waitForElementAndSendKeys(LOGIN_INPUT, login,
                "Cannot find and put login to the login input", 15);
        this.waitForElementAndSendKeys(PASSWORD_INPUT, password,
                "Cannot find and put password to the password input", 15);
    }

    @Step("Waiting for submit button and click in auth form")
    public void submitForm(){
        this.waitForElementAndClick(SUBMIT_BUTTON, "Cannot find and click submit auth button", 15);
        }
}
