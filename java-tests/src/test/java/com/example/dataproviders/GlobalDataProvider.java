package com.example.dataproviders;

import org.testng.annotations.DataProvider;

public class GlobalDataProvider {

    @DataProvider(name = "sampleData")
    public Object[][] getSampleData() {
        return new Object[][] {
            {"user1", "pass1"},
            {"user2", "pass2"}
        };
    }
}
