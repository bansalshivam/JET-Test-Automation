package de.lieferando.utility;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class WebHelper {

    final Wait<WebDriver> wait;
    public WebHelper(WebDriver driver) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(60));
    }

    /**
     * Method to wait for element visibility.
     *
     * @return Return element after visibility
     */
    public WebElement waitForElementVisibility(By elementLocator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(elementLocator));
    }

    /**
     * Method to wait for visibility of two or more elements.
     *
     * @return Return elements after visibility
     */
    public List<WebElement> waitForAllElementsVisibility(By elementLocator) {
        return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(elementLocator));
    }

    /**
     * Method to prepare list of texts of present within element
     *
     * @return List of texts
     */
    public List<String> prepareTextOfElements(List<WebElement> webElements) {
        List<String> minimumOrderValue = new ArrayList<>();
        for(WebElement webElement: webElements) {
            minimumOrderValue.add(webElement.getText());
        }
        return minimumOrderValue;
    }
}