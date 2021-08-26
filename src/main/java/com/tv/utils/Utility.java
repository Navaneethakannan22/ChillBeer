package com.tv.utils;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Utility {

    public RequestSpecification req;
    public RequestSpecification requestSpecification() throws IOException {
        req=new RequestSpecBuilder().setBaseUri(getGlobalValue("baseUrl"))
                .setContentType(ContentType.JSON).build();
        return req;
    }


    public String getGlobalValue(String key) throws IOException
    {
        Properties prop =new Properties();
        FileInputStream fis =new FileInputStream("/Users/navaneethp/Desktop/Naveen/ChillBeer/src/main/resources/env.properties");
        prop.load(fis);
        return prop.getProperty(key);
    }


    public String getJsonPath(Response response,String key) {
        return response.jsonPath().getString(key);
    }

}
