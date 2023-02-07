package de.lieferando.ui.testcases;

import de.lieferando.configuration.TestConfiguration;
import de.lieferando.ui.pages.MainPage;
import de.lieferando.ui.pages.RestaurantListingPage;
import de.lieferando.utility.LoggerUtility;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.aeonbits.owner.ConfigFactory;

import org.apache.logging.log4j.LogManager;

import org.openqa.selenium.WebDriver;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

    final LoggerUtility log = new LoggerUtility(LogManager.getLogger(getClass()));
    MainPage mainPage;
    RestaurantListingPage restaurantListingPage;
    private final TestConfiguration uiConfig = ConfigFactory.create(
            TestConfiguration.class, System.getProperties());
    protected WebDriver driver;

    @AfterMethod
    public void tearDown() {
        log.infoMSG("Closing browser");
        driver.quit();
    }

    @BeforeMethod
    public void logStartOfTest(ITestResult result) {
        log.infoMSG("Setting up browser : " + uiConfig.browser());
        driver = WebDriverManager.getInstance(uiConfig.browser()) .create();
        driver.manage().window().maximize();
        log.infoMSG("Fetching URL : " + uiConfig.webAppBaseUrl());
        driver.get(uiConfig.webAppBaseUrl());
        mainPage = new MainPage(driver);
        mainPage.acceptCookies();
    }
}