package de.lieferando.api.testcases;

import de.lieferando.api.response.ChickenResponse;
import org.testng.annotations.Test;

import java.util.Objects;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class ChickenApiTest extends BaseTest {

    @Test(description = "User is able to feed chickens")
    public void userFeedChickenFirstTime() {
        ChickenResponse response = coopChickenService.feedChicken();
        assertThat(response.getAction(), is ("chickens-feed"));
        assertThat(response.getSuccess(), is(Boolean.TRUE));
        assertThat(response.getMessage(), is(testData.happyChickenMessage()));
    }

    @Test(description = "Warning when chickens are fed too much")
    public void userFeedChickenTwoTimes() {
        coopChickenService.feedChicken();
        ChickenResponse response = coopChickenService.feedChicken();
        assertThat(response.getAction(), is("chickens-feed"));
        assertThat(response.getSuccess(), is(Boolean.TRUE));
        assertThat(response.getMessage(), is(testData.chickenIsFullMessage()));
    }

    @Test(description = "User is able to collect eggs")
    public void testCollectEggs() {
        ChickenResponse response = coopChickenService.collectEggs();
        assertThat(response.getAction(), is("eggs-collect"));
        assertThat(response.getSuccess(), is(Boolean.TRUE));
        assertThat(response.getData(), greaterThan(0));
        assertThat(response.getMessage(), is(testData.collectedEggsMessage(response.getData())));
    }

    @Test(description = "Verify daily collected eggs counter")
    public void testCollectEggsAndCount() throws InterruptedException {
        Integer initialEggsNumber = coopChickenService.countCollectedEggs().getData();
        Integer collectedEggs = coopChickenService.collectEggs().getData();
        int requestLimit = 5;
        // might be avoided if we have possibility to reset the state
        while (Objects.isNull(collectedEggs) && requestLimit > 0) {
            ChickenResponse coopResponse = coopChickenService.collectEggs();
            collectedEggs = coopResponse.getData();
            requestLimit--;
            Thread.sleep(5000);
        }
        int expectedSumOfEggs = initialEggsNumber + collectedEggs;
        ChickenResponse response = coopChickenService.countCollectedEggs();
        assertThat(response.getAction(), is("eggs-count"));
        assertThat(response.getSuccess(), is(Boolean.TRUE));
        assertThat(response.getData(), is(expectedSumOfEggs));
        assertThat(response.getMessage(), is(testData.dailyCollectedEggsMessage(expectedSumOfEggs)));
    }

    @Test(description = "User is not able to collect eggs too many times")
    public void testFailToCollectEggs() {
        coopChickenService.collectEggs();
        ChickenResponse response = coopChickenService.collectEggs();
        assertThat(response.getAction(), is("eggs-collect"));
        assertThat(response.getSuccess(), is(Boolean.TRUE));
        assertThat(response.getMessage(), is(testData.failToCollectEggsMessage()));
        assertThat(response.getData(), is(nullValue()));
    }
}
