import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by laurentmeert on 25/04/2017.
 */
public class IosSauceTest {

    private IOSDriver driver;
    private static final String USERNAME = "laurentmeert";
    private static final String ACCESS_KEY = "fb32916f-e02b-4961-b9d2-aa0a3d053a5f";
    private static final String stringURL = "https://" + USERNAME + ":" + ACCESS_KEY + "@ondemand.saucelabs.com:443/wd/hub";


    @Before
    public void setup() {
        driver = getIosDriver();
    }

    @After
    public void finish() {
        driver.quit();
    }

    private IOSDriver getIosDriver() {
        DesiredCapabilities caps = DesiredCapabilities.iphone();
        caps.setCapability("appiumVersion", "1.6.3");
        caps.setCapability("deviceName","iPhone 7 Simulator");
        caps.setCapability("deviceOrientation", "portrait");
        caps.setCapability("platformVersion","10.2");
        caps.setCapability("platformName", "iOS");
        caps.setCapability("browserName", "");
        caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, "XCUITest");
        caps.setCapability("app","sauce-storage:OCS.zip");
        caps.setCapability("name" , "TEST IOS SAUCELABS");

        URL url;

        try {
            url = new URL(stringURL);
        } catch (MalformedURLException e) {
            System.out.print("URL won't work...");
            url = null;
            e.printStackTrace();
        }

        return new IOSDriver(url , caps);
    }


    @Test
    public void goToLoginScreen() {

        WebDriverWait driverWait = new WebDriverWait(driver, 15L);

        driverWait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept();

        driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("burger_icon")));

        WebElement burger = driver.findElement(By.id("burger_icon"));

        burger.click();

        driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("textView5")));

        WebElement signOrReg = driver.findElement(By.id("textView5"));
        signOrReg.click();

    }
}
