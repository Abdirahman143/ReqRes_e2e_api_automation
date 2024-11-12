package com.cbc;

import com.cbc.utils.RestAssuredUtils;
import org.testng.annotations.BeforeClass;

public class BaseTest {


    @BeforeClass
    public void setUp() {
       RestAssuredUtils.setUp();
    }
}
