package de.lieferando.dataprovider;

import org.testng.annotations.DataProvider;

public class JetDataProvider {

    @DataProvider(name = "addressProvider")
    public static Object[][] addressData()
    {
        return new Object[][] {
                {"Handjerystraße, 12489 Berlin"}
        };
    }

    @DataProvider(name = "categoryProvider")
    public static Object[][] categoryData()
    {
        return new Object[][] {
                {"Italian"}
        };
    }

}
