package de.lieferando.ui.testcases;

import de.lieferando.dataprovider.JetDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class SearchAndFilterRestaurantTest extends BaseTest {

    @Test(dataProvider = "addressProvider", dataProviderClass = JetDataProvider.class, description = "User can search and filter restaurant by delivery cost")
    public void userFilterSearchedRestaurantByDeliveryCost(String address) {
        restaurantListingPage = mainPage.searchRestaurantByAddress(address);
        restaurantListingPage.filterRestaurantByMinimumOrder();
        List<String> restaurantMinimumOrderPrices = restaurantListingPage.fetchRestaurantPrices();
        for(String minimumPrices: restaurantMinimumOrderPrices) {
            Assert.assertTrue(Integer.parseInt(minimumPrices.replaceAll(
                    "[^0-9]", "")) <= 1000,"Restaurants are not filtered correctly.");
        }
    }

    @Test(dataProvider = "addressProvider", dataProviderClass = JetDataProvider.class, description = "User can filter restaurant by category")
    public void userFilterRestaurantByCategory(String address) {
        List<String> restaurantCuisines = mainPage.searchRestaurantByAddress(address).selectRestaurantCategory();
        for(String restaurantCuisine: restaurantCuisines) {
            Assert.assertTrue(restaurantCuisine.contains("Italian"));
        }
    }
}
