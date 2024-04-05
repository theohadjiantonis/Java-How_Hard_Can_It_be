package utils;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class GithubApiHealthCheck {

    public static void connectionChecker() {
        String apiUrl = "https://api.github.com";
        Response response = RestAssured.given()
                .get(apiUrl);
        // Print the response body
        System.out.println("HealthCheck Response Body: " + response.toString());
    }
}