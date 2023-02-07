package de.lieferando.api.testcases;

import de.lieferando.api.services.CoopChickenService;
import de.lieferando.api.testdata.DetailedTestData;
import de.lieferando.api.testdata.TestData;
import de.lieferando.configuration.TestConfiguration;
import de.lieferando.utility.LoggerUtility;
import io.restassured.RestAssured;
import org.aeonbits.owner.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.testng.ITestResult;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

public class BaseTest {

    final LoggerUtility log = new LoggerUtility(LogManager.getLogger(getClass()));
    CoopChickenService coopChickenService;
    protected final TestData testData;

    public BaseTest() {
        this.testData = new DetailedTestData();
    }

    @BeforeSuite
    public void setUp() {
        TestConfiguration apiConfig = ConfigFactory.create(TestConfiguration.class, System.getProperties());
        RestAssured.baseURI = apiConfig.getApiBaseUrl();
    }

    @BeforeClass
    public void init() {
        coopChickenService  = new CoopChickenService();
    }

    @BeforeMethod
    public void logStartOfTest(ITestResult result) {
        log.initiateTestHTML(result.getMethod().getDescription());
    }
}
