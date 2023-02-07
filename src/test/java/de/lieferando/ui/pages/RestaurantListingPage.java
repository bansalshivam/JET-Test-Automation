package de.lieferando.ui.pages;

import de.lieferando.utility.WebHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.List;

public class RestaurantListingPage {

    private final WebHelper webHelper;

    //Locators
    private final By minimumOrderFilter = By.xpath("//input[@data-qa='radio-element']/..//span[contains(text(), '10,00')]");
    private final By restaurantMinimumOrder = By.xpath("//div[@data-qa='mov-indicator-content']/span/span");
    private final By restaurantCuisine = By.xpath("//div[@data-qa='restaurant-cuisines']");
    private final By restaurantCategory = By.xpath("//div[text()='Italian']");
    public RestaurantListingPage(WebDriver driver) {
        webHelper = new WebHelper(driver);
    }

    /**
     * Method is clicking on minimum order value filter.
     */
    public void filterRestaurantByMinimumOrder() {
        webHelper.waitForElementVisibility(minimumOrderFilter).click();
    }

    /**
     * Method is fetching all minimum order prices available on restaurant.
     *
     * @return List of prices
     */
    public List<String> fetchRestaurantPrices() {
        return webHelper.prepareTextOfElements(
                webHelper.waitForAllElementsVisibility(restaurantMinimumOrder));
    }

    /**
     * Method to select restaurant by category.
     *
     * @return List of restaurant cuisines
     */
    public List<String> selectRestaurantCategory() {
        webHelper.waitForElementVisibility(restaurantCategory).click();
        return webHelper.prepareTextOfElements(
                webHelper.waitForAllElementsVisibility(restaurantCuisine));

    }
}
