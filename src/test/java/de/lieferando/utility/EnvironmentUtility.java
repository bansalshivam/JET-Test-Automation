package de.lieferando.utility;

import java.util.Date;

public class EnvironmentUtility {

    public static String getReportDirectory() {
        return System.getProperty("user.dir") + "/target/Reports/";

    }

    public static String getReportName() {
        Date timeStamp = new Date();
        return timeStamp.toString().replace(":", "_").replace(" ", "_") + ".html";
    }


}
