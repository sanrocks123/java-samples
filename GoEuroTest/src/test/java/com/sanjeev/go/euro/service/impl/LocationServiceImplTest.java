package com.sanjeev.go.euro.service.impl;

import org.json.JSONArray;
import org.testng.annotations.Test;

import mockit.Tested;

public class LocationServiceImplTest {

    @Tested
    private LocationServiceImpl locServ;

    @Test(enabled = false)
    public void testGetCityDetails() {

        JSONArray actual = locServ.getCityDetails("Berlin");
        System.out.println(actual);
    }
}
