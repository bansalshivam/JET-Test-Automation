package de.lieferando.utility;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.apache.logging.log4j.Logger;

public class LoggerUtility {

    private static Logger log;
    private final ExtentReports extent = ExtentReportManager.getInstance();
    private static ExtentTest test;

    public LoggerUtility(Logger log) {
        LoggerUtility.log = log;
    }

    /**
     * Method to print start of test case
     *
     */
    public void startTestCase(final String testCaseName) {
        log.info("******************************************************************************");
        log.info("----------------- Starting Test Case  :  " + testCaseName);
        log.info("******************************************************************************");
    }

    /**
     * Method to print end of test case
     *
     */
    public void endTestCase(final String testCaseName) {
        log.info("******************************************************************************");
        log.info("----------------- Ending Test Case    :  " + testCaseName);
        log.info("******************************************************************************");
    }

    /**
     * Method to print info level log
     *
     */
    public void infoMSG(final String message) {
        log.info(message);
    }

    /**
     * Method to print error level log
     *
     */
    public void errorMSG(final String message) {
        log.error(message);
    }

    /**
     * Method to print debug level log
     *
     */
    public void debugMSG(final String message) {
        log.debug(message);
    }

    /**
     * Method to add test case in extent report
     *
     */
    public void initiateTestHTML(final String testCaseName) {
        startTestCase(testCaseName);
        test = extent.createTest(testCaseName).assignCategory("Sanity");
    }

    /**
     * Method to end test case in extent report
     */
    public void endTestHTML() {
        endTestCase(test.getModel().getName());
        if(extent != null) {
            extent.flush();
        }
    }

}
