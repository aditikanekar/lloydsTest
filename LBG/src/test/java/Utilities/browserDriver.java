package Utilities;

import ExtentReports.ExtentTestManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertFalse;

public class browserDriver implements WebDriver {
    private static final String objectFileName = null;
    public static WebDriver driver;
    private final String chromeDriverPath = "src/test/resources/BrowserDrivers/chromedriver";
    private final String reportConfigPath = "src/test/resources/extent-config.xml";
    public String browserName;
    public Properties prop;
    ExtentTestManager extentReportManager = new ExtentTestManager();

    public browserDriver() {
    }

    public browserDriver(String browserName) {
        this.browserName = browserName;
        createDriver(browserName);
    }

    public browserDriver(String browserName, String objectFileName) throws IOException {
        this.browserName = browserName;
        //objectReader(objectFileName);
        createDriver(browserName);
    }

    private WebDriver createDriver(String browserName) {
        if (browserName.equalsIgnoreCase("CHROME"))
            return chromeDriver();

        throw new RuntimeException("invalid browser name");
    }


    public WebDriver chromeDriver() {

        try {
            System.setProperty("webdriver.chrome.driver",
                    chromeDriverPath);
            ChromeOptions c = new ChromeOptions();
            driver = new ChromeDriver(c);
            return driver;
        } catch (Exception ex) {
            throw new RuntimeException
                    ("Cannot create chrome driver");
        }
    }

    @Override
    public String toString() {
        return this.browserName;
    }

    @Override
    public void get(String url) {

    }

    @Override
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    @Override
    public String getTitle() {
        return driver.getTitle();
    }

    @Override
    public List<WebElement> findElements(By by) {
        return null;
    }

    @Override
    public WebElement findElement(By locator) {
        return driver.findElement(locator);
    }

    @Override
    public String getPageSource() {
        return null;
    }

    @Override
    public void close() {
        driver.close();
    }

    @Override
    public void quit() {
        driver.quit();
    }

    @Override
    public Set<String> getWindowHandles() {
        return driver.getWindowHandles();
    }

    @Override
    public String getWindowHandle() {
        return null;
    }

    @Override
    public TargetLocator switchTo() {
        return driver.switchTo();
    }

    @Override
    public Navigation navigate() {
        return driver.navigate();
    }

    @Override
    public Options manage() {
        return null;
    }

    public Object getScreenshotAs(OutputType target)
            throws WebDriverException {
        return ((TakesScreenshot) driver)
                .getScreenshotAs(target);
    }

    public void hoverOverElement(String element) {
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(By.xpath(element))).perform();
    }

    public WebElement waitForElement(WebElement elementLocator) {
        WebElement webElement = null;
        int timeout = 20; //in seconds
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        try {
            webElement = wait.until(ExpectedConditions.visibilityOf(elementLocator));
        } catch (WebDriverException e) {
            //do nothing, don't want to log this
        }
        if (webElement == null) {
            assertFalse(true, "WebElement not found within " + timeout + " seconds. Failing test!" + " of element: " + elementLocator + "\nCurrent page: " + driver.getCurrentUrl());
        }
        return webElement;
    }

    public void scrollUntilElementIsVisible(String locatorKey, String element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", getElement(locatorKey, element));
    }

    public void objectReader(String objectFileName) throws IOException {
        if (prop == null)
            prop = new Properties();
        File file = new File(objectFileName);
        FileInputStream fis = new FileInputStream(file);
        prop.load(fis);
    }

    public WebElement getElement(String locatorKey, String locator) {
        WebElement e = null;
        String objectLocator = prop.getProperty(locator);
        switch (locatorKey) {
            case "id":
                e = driver.findElement(By.id(objectLocator));
                break;
            case "xpath":
                e = driver.findElement(By.xpath(objectLocator));
                break;
            case "css":
                e = driver.findElement(By.cssSelector(objectLocator));
                break;
            case "name":
                e = driver.findElement(By.name(objectLocator));
                break;
            case "tagname":
                e = driver
                        .findElement(By.tagName(objectLocator));
                break;
            case "classname":
                e = driver.findElement(By.className(objectLocator));
                break;
            default:
                Assert.fail("Locator not correct - " + locator);
        }
        return e;
    }

    public void launchUrl(String url) {
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get(url);
    }

    public void click(WebElement elementLocator) {
        WebElement webElement = waitForElement(elementLocator);
        try {
            webElement.click();
        } catch (Exception ex) {

        }
    }

    public void waitForPageToLoad() {
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

        JavascriptExecutor js = (JavascriptExecutor) driver;
        String state = (String) js.executeScript("return document.readyState");

        while (!state.equals("complete")) {
            driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

            state = (String) js.executeScript("return document.readyState");
        }

    }

    public boolean isVisible(String locatorKey) {
        boolean value = false;
        if (locatorKey.endsWith("_xpath")) {
            value = driver.findElement(By.xpath(prop.getProperty(locatorKey)))
                    .isDisplayed();
            Assert.assertTrue(value);
        }

        return value;
    }
}
