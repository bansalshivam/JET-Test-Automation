package de.lieferando.utility;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import static de.lieferando.utility.EnvironmentUtility.*;

public class ExtentReportManager {

    private static ExtentReports extent;

    public static ExtentReports getInstance() {
        if(extent == null) {
            ExtentSparkReporter reporter = new ExtentSparkReporter(getReportDirectory() + getReportName());
            reporter.config().setDocumentTitle("Automation Setup");
            reporter.config().setReportName("Sanity");
            extent = new ExtentReports();
            extent.attachReporter(reporter);
            extent.setSystemInfo("Company", "Just Eat Takeaway");
            extent.setSystemInfo("Brand", "Lieferando");
            extent.setSystemInfo("OS", System.getProperty("os.name"));
            extent.setSystemInfo("OS", System.getProperty("user.name"));
        }
        return extent;
    }
}
