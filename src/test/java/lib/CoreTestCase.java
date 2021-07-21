package lib;

import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Step;
import junit.framework.TestCase;
import lib.ui.WelcomePageObject;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.FileOutputStream;
import java.util.Properties;


public class CoreTestCase {
    protected RemoteWebDriver driver;

    @Before
    @Step("Run driver and session")
    public void setUp() throws Exception{
        driver = Platform.getInstance().getDriver();
        this.createAllurePropertyFile();
        this.rotateScreenPortrait();
        this.skipWelcomePageForIosApp();
        this.openWikiWebPageForMobileWeb();
    }

    @After
    @Step ("Remove driver and session")
    public void tearDown()  {
        driver.quit();
    }

    @Step ("Rotate screen to portrait mode")
    protected void rotateScreenPortrait(){

        if (driver instanceof AppiumDriver)
        {
            AppiumDriver  driver = (AppiumDriver) this.driver;
            driver.rotate(ScreenOrientation.PORTRAIT);
        }
        else
        {
            System.out.println("Method rotateScreenPortrait does nothing for platform " +
                    Platform.getInstance().getPlatformVar());
        }

    }

    @Step ("Rotate screen to landscape mode")
    protected void rotateScreenLandscape(){
        if (driver instanceof AppiumDriver) {
            AppiumDriver driver = (AppiumDriver) this.driver;
            driver.rotate(ScreenOrientation.LANDSCAPE);
        }
        else
        {
            System.out.println("Method rotateScreenLandscape does nothing for platform " +
                    Platform.getInstance().getPlatformVar());
        }
    }

    @Step ("Skip Welcome page for iOS")
    private void skipWelcomePageForIosApp(){
        if (Platform.getInstance().isIos())
        {
            AppiumDriver driver = (AppiumDriver) this.driver;
            WelcomePageObject WelcomePageObject = new WelcomePageObject(driver);
            WelcomePageObject.clickSkip();
        }
    }

    @Step ("Open Wiki URL for Mobile Web (Method does nothing for iOS and android)")
    protected void openWikiWebPageForMobileWeb(){
        if (Platform.getInstance().isMW()){
            driver.get("https://en.m.wikipedia.org");
        }
        else
        {
            System.out.println("Method openWikiWebPageForMobileWeb does nothing for platform " +
                    Platform.getInstance().getPlatformVar());
        }
    }

    private void createAllurePropertyFile(){
        String path =System.getProperty("allure.results.directory");
        try{
            Properties props = new Properties();
            FileOutputStream fos= new FileOutputStream(path + "/environment.properties");
            props.setProperty("environment", Platform.getInstance().getPlatformVar());
            props.store(fos,"See allure docs for environment");
            fos.close();
        } catch (Exception e){
            System.err.println("IO Problem when writing allure properties file");
            e.printStackTrace();
        }
    }
}
