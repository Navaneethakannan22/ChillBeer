package com.tv.beers;

import io.restassured.response.Response;
import org.testng.Assert;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class BeerResponseValidation {

    public BeerResponseBody[] shouldGetAllBeersResponse(Response response) {
        BeerResponseBody[] beerResponseBody = response.as(BeerResponseBody[].class);

        return beerResponseBody;
    }

    public void shouldValidateAllBeersBrewedBefore(Response response, String brewedDate) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/yyyy");
        Date givenDate = simpleDateFormat.parse(brewedDate);
        BeerResponseBody[] allBeers = shouldGetAllBeersResponse(response);

        for (BeerResponseBody beer : allBeers) {
            Date responseDate = simpleDateFormat.parse(beer.getFirst_brewed());
            Assert.assertTrue(responseDate.before(givenDate));
        }
    }

    public void shouldValidateAllBeersWithAbv(Response response, double abvValue) {
        BeerResponseBody[] allBeers = shouldGetAllBeersResponse(response);

        for (BeerResponseBody beer : allBeers) {
            Assert.assertTrue(beer.getAbv() >= abvValue);
        }
    }

    public void shouldAssertRequiredFileds(Response response) {
        BeerResponseBody[] allBeers = shouldGetAllBeersResponse(response);

        Assert.assertNotNull(allBeers[0].getId());
        Assert.assertNotNull(allBeers[0].getName());
        Assert.assertNotNull(allBeers[0].getDescription());
        Assert.assertNotNull(allBeers[0].getAbv());

    }

}
