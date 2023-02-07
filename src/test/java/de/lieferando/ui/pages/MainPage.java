package de.lieferando.ui.pages;

import de.lieferando.utility.LoggerUtility;
import de.lieferando.utility.WebHelper;

import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPage {

    private final WebDriver driver;
    private final WebHelper webHelper;

    //Locators
    private final By acceptCookies = By.xpath("//button[@data-qa='privacy-settings-action-info']");
    private final By searchBox = By.id("combobox-input_0");
    private final By searchedSuggestion = By.xpath("//li[@data-qa='location-panel-results-item-element']");

    public MainPage(WebDriver driver) {
        this.driver = driver;
        webHelper = new WebHelper(driver);
    }
    final LoggerUtility log = new LoggerUtility(LogManager.getLogger(getClass()));

    /**
     * Method to accept cookies section
     */
    public void acceptCookies() {
        webHelper.waitForElementVisibility(acceptCookies).click();
    }

    /**
     * Method to search restaurant by address
     *
     * @return Object of restaurant page
     */
    public RestaurantListingPage searchRestaurantByAddress(String address) {
        log.infoMSG("Starting to Enter address in search box");
        webHelper.waitForElementVisibility(searchBox).sendKeys(address);
        log.debugMSG("Deleting Cookies");
        driver.manage().deleteAllCookies();
        log.infoMSG("Fetching restaurant with the given address");
        webHelper.waitForElementVisibility(searchedSuggestion).click();
        return new RestaurantListingPage(driver);
    }
}
