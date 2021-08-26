import com.tv.beers.BeerResponseValidation;
import com.tv.utils.Utility;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.text.ParseException;

import static io.restassured.RestAssured.*;

public class BeerTest {
    Utility utility = new Utility();
    BeerResponseValidation responseValidation = new BeerResponseValidation();

    @Test(groups = "SMOKE")
    public void shouldGetAllBeers() throws IOException {

        Response allBeers = given().spec(utility.requestSpecification()).
                get("v2/beers");

        Assert.assertEquals(allBeers.getStatusCode(), 200);
        responseValidation.shouldAssertRequiredFileds(allBeers);

        System.out.println("smoke ran successfully");
    }

    @Test(groups = "SMOKE")
    public void shouldGetAllBeersBrewedBefore() throws IOException, ParseException {

        String brewedDate = "02/2008";
        Response allBeers = given().spec(utility.requestSpecification()).
                queryParam("brewed_before", brewedDate).
                get("v2/beers");

        responseValidation.shouldValidateAllBeersBrewedBefore(allBeers, brewedDate);

        Assert.assertEquals(allBeers.getStatusCode(), 200);
        responseValidation.shouldAssertRequiredFileds(allBeers);
        System.out.println("smoke ran successfully");
    }

    @Test(groups = "REGRESSION")
    public void shouldGetAllBeersWithSpecificAbv() throws IOException {

        double abv = 6;
        Response allBeers = given().spec(utility.requestSpecification()).
                queryParam("abv_gt", abv).
                get("v2/beers");

        responseValidation.shouldValidateAllBeersWithAbv(allBeers, abv);

        Assert.assertEquals(allBeers.getStatusCode(), 200);
        responseValidation.shouldAssertRequiredFileds(allBeers);
        System.out.println("regression ran successfully");
    }

    @Test(groups = "REGRESSION")
    public void shouldGetAllBeersAtSpecificPage() throws IOException {

        Response allBeers = given().spec(utility.requestSpecification()).
                queryParam("page", "2").
                queryParam("per_page", "5").
                get("v2/beers");

        Assert.assertEquals(responseValidation.shouldGetAllBeersResponse(allBeers).length, 5);

        Assert.assertEquals(allBeers.getStatusCode(), 200);
        responseValidation.shouldAssertRequiredFileds(allBeers);
        System.out.println("regression ran successfully");
    }
}