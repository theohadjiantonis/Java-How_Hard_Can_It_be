package utils;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class GithubApiHealthCheck {

    public static void connectionChecker() {
        //this was added more of like a sanity check just to be sure that error responses from Github weren't due to not being able to hit GithubAPIs
        String apiUrl = "https://api.github.com";
        Response response = RestAssured.given()
                .get(apiUrl);
        // Print the response body
        System.out.println("HealthCheck Response Body: " + response.toString());
    }
}