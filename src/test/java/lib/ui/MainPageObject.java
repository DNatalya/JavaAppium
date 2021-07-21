package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import io.qameta.allure.Attachment;
import lib.Platform;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.regex.Pattern;

public class MainPageObject {
    protected RemoteWebDriver driver;

    public MainPageObject(RemoteWebDriver driver){
        this.driver = driver;
    }

    public WebElement waitForElementPresent(String locator, String error_message, long timeoutInSeconds){
        By by = getLocatorByString(locator);
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message+"\n");
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public WebElement waitForElementAndClick(String locator, String error_message, long timeoutInSeconds)
    {
        WebElement element = waitForElementPresent(locator, error_message,timeoutInSeconds);
        element.click();
        return element;
    }

    public WebElement waitForElementAndSendKeys(String locator, String value, String error_message, long timeoutInSeconds)
    {
        WebElement element = waitForElementPresent(locator, error_message, timeoutInSeconds);
        element.sendKeys(value);
        return element;
    }

    public boolean waitForElementNotPresent(String locator, String error_message, long timeoutInSeconds){
        By by = getLocatorByString(locator);
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message+"\n");
        return  wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
    }

    public void assertElementHasText(String locator, String[] expected_value, String error_message){
        WebElement element = waitForElementPresent(locator, error_message, 5);
        String element_text = element.getText();
        int i = 0;
        while (i <= expected_value.length-1)
        {
            if(expected_value[i] != element_text){
                i++;
            }
        }
    }

    public void assertElementContainsText(String locator, String expected_value, String error_message){
        WebElement element = waitForElementPresent(locator, error_message, 5);
        Assert.assertTrue(error_message, element.getText().contains(expected_value));
    }

    public int countFoundedItems(String list, String item,  String error_message) {
        By by = getLocatorByString(item);
        WebElement list_of_items = waitForElementPresent(list, error_message, 20);
        return list_of_items.findElements(by).size();
    }

    public WebElement waitForElementAndClear(String locator, String error_message, long timeoutInSeconds)
    {
        WebElement element = waitForElementPresent(locator, error_message, timeoutInSeconds);
        element.clear();
        return element;
    }


    public void swipeElementToLeft(String locator, String error_message){
        if (driver instanceof AppiumDriver) {
            AppiumDriver driver = (AppiumDriver) this.driver;
            WebElement element = waitForElementPresent(locator,
                    error_message,
                    15);
            int left_x = element.getLocation().getX();
            int right_x = left_x + element.getSize().getWidth();
            int upper_y = element.getLocation().getY();
            int lower_y = upper_y + element.getSize().getHeight();
            int middle_y = (upper_y + lower_y) / 2;
            PointOption pointStart = PointOption.point(right_x, middle_y);
            PointOption pointEnd = PointOption.point(left_x, middle_y);
            TouchAction action = new TouchAction((AppiumDriver) driver);
            action.press(pointStart);
            action.waitAction(WaitOptions.waitOptions(Duration.ofSeconds(300)));
            if (Platform.getInstance().isAndroid()) {
                action.moveTo(pointEnd);
            } else {
                action.moveTo(PointOption.point(right_x / 2, middle_y));
            }
            action.release();
            action.perform();
        }
        else {
            System.out.println("Method swipeElementToLeft does nothing for platform " +
                    Platform.getInstance().getPlatformVar());
        }
    }

    public void swipeUp(int timeOfSwype){
        if (driver instanceof AppiumDriver) {
            AppiumDriver driver = (AppiumDriver) this.driver;
            TouchAction action = new TouchAction((AppiumDriver) driver);
            Dimension size = driver.manage().window().getSize();
            int x = size.width / 2;
            int start_y = (int) (size.height * 0.8);
            int end_y = (int) (size.height * 0.2);
            action
                    .press(PointOption.point(x, start_y))
                    .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(timeOfSwype)))
                    .moveTo(PointOption.point(x, end_y))
                    .release()
                    .perform();
        }
        else {
            System.out.println("Method swipeUp does nothing for platform " +
                    Platform.getInstance().getPlatformVar());
        }
    }

    public void swipeUpQuick(){
        swipeUp(20);
    }

    public void swipeUpToFindElement(String locator, String error_message, int max_swipes) {
        By by = getLocatorByString(locator);
        int already_swiped = 0;
        while (driver.findElements(by).size() == 0) {
            if (already_swiped > max_swipes) {
                waitForElementPresent(locator,
                        "Cannot find element by swiping up. \n" + error_message,
                        0);
                return;
            }
            swipeUpQuick();
            ++already_swiped;
        }
    }


    public boolean isElementLocatedOnTheScreen(String locator){
        int element_location_by_y = this.waitForElementPresent(locator, "Cannot find element by locator",
                10).getLocation().getY();
        if (Platform.getInstance().isMW()){
            JavascriptExecutor JSExecutor = (JavascriptExecutor) driver;
            Object js_result = JSExecutor.executeScript("return window.page.YOffset");
            element_location_by_y = Integer.parseInt(js_result.toString());
        }
        int screen_size_by_y = driver.manage().window().getSize().getHeight();
       return element_location_by_y<screen_size_by_y;
    }

    public void swipeUpTillElementAppear(String locator, String error_message, int max_swipes){
        int already_swiped = 0;
        while (this.isElementLocatedOnTheScreen(locator)) {
            if (already_swiped >max_swipes)
            {
                Assert.assertTrue(error_message, this.isElementLocatedOnTheScreen(locator));
            }
            swipeUpQuick();
            ++already_swiped;
        }
    }

    public boolean isElementPresent(String locator)
    {
        By by = getLocatorByString(locator);
        return driver.findElements(by).size() > 0 ;
    }

    private By getLocatorByString(String locator_with_type){
        String [] exploded_locator = locator_with_type.split(Pattern.quote(":"), 2);
        String by_type = exploded_locator[0];
        String locator = exploded_locator[1];
        if (by_type.equals("xpath"))
        {
            return By.xpath(locator);
        }
        else if (by_type.equals("id")){
            return By.id(locator);
        }
        else if (by_type.equals("css")){
            return By.cssSelector(locator);
        }
        else throw new IllegalArgumentException("Cannot get type of locator: " + locator);
    }

    public void clickElementToTheRightUpperCorner(String locator, String error_message){
        if (driver instanceof AppiumDriver) {
            AppiumDriver driver = (AppiumDriver) this.driver;
            WebElement element = this.waitForElementPresent(locator + "/..", error_message, 5);
            int right_x = element.getLocation().getX();
            int upper_y = element.getLocation().getY();
            int lower_y = upper_y + element.getSize().getHeight();
            int middle_y = (upper_y + lower_y) / 2;
            int width = element.getSize().getWidth();
            int point_to_click_x = (right_x + width) - 3;
            int point_to_click_y = middle_y;

            TouchAction action = new TouchAction( (AppiumDriver) driver);
            action.tap(PointOption.point(point_to_click_x, point_to_click_y)).perform();
        }
        else {
            {
                System.out.println("Method clickElementToTheRightUpperCorner does nothing for platform " +
                        Platform.getInstance().getPlatformVar());
            }

        }
    }

    public void ScrollWebPageUp(){
        if (Platform.getInstance().isMW()){
            JavascriptExecutor JSexecutor = (JavascriptExecutor) driver;
            JSexecutor.executeScript("window.scrollBy(0, 250)");
        }
        else {
            System.out.println("Method ScrollWebPageUp does nothing for platform " +
                    Platform.getInstance().getPlatformVar());
        }
    }

    public void ScrollWebPageTillElementNotVisible(String locator, String error_message, int max_swipes){
        int already_swiped = 0;
        WebElement element = this.waitForElementPresent(locator, error_message, 10);
        while(!this.isElementLocatedOnTheScreen(locator)){
            ScrollWebPageUp();
            ++ already_swiped;
        }
        if (already_swiped > max_swipes){
            Assert.assertTrue(error_message, element.isDisplayed());
        }
    }

    public void tryClickElementWithFewAttempts(String locator, String error_message, int amount_of_attempts){
        int current_attempts =0;
        boolean need_more_attempts = true;
        while (need_more_attempts){
            try {
                this.waitForElementAndClick(locator,error_message, 1 );
                need_more_attempts=false;
            }
            catch (Exception e){
                if (current_attempts> amount_of_attempts){
                    this.waitForElementAndClick(locator,error_message, 1 );
                }
            }
            ++ current_attempts;
        }
    }

     public String takeScreenshot(String name){
        TakesScreenshot ts = (TakesScreenshot)this.driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        String path = System.getProperty("user.dir") + "/"+ name + "Screenshot.png";
            try {
            FileUtils.copyFile(source, new File(path));
            System.out.println("The screenshot was taken: " + path);
            }
            catch (Exception e){
            System.out.println("Cannot take screenshot. Error " + e.getMessage());
            }
        return path;
    }


    @Attachment
    public static byte[] screenshot (String path){
        byte [] bytes = new byte[0];
        try  {
            bytes = Files.readAllBytes(Paths.get(path));
        }
        catch (IOException e){
            System.out.println("Cannot get bytes form screenshot. Error "+ e.getMessage());
        }
        return bytes;
        }

}
