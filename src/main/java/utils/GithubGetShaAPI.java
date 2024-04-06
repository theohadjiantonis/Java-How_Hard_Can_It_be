package utils;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class GithubGetShaAPI {

    public static String getFileSHA(String url, String token) {

        // Make the REST API call to GitHub to get the file details
        Response response = RestAssured.given()
                .header("Authorization", "token " + token)
                .get(url);

        // Extract the SHA from the response
        String sha = response.jsonPath().getString("sha");

        // Print the response body
        System.out.println("Retrieve Current Sha Response Body: ");
        System.out.println("Response Code: " + response.statusCode());
        response.body().prettyPrint();
        return sha;
    }
}