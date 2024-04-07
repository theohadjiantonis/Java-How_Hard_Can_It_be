package utils;

import io.restassured.http.ContentType;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;

public class GithubPullRequestAPI {
    public static Integer createPullRequest(String sourceBranch, String targetBranch, String title, String body, String token, String userName, String repo) {
        ValidatableResponse response = RestAssured.given()
                .auth()
                .oauth2(token)
                .baseUri("https://api.github.com")
                .contentType(ContentType.JSON)
                .body("{\"title\": \"" + title + "\", \"body\": \"" + body + "\", \"head\": \"" + sourceBranch + "\", \"base\": \"" + targetBranch + "\"}")
                .when()
                .post("/repos/" + userName + "/" + repo + "/pulls")
                .then()
                .statusCode(201);

        System.out.println("Pull Request");
        // Extract and return the number of the PR which will be used in the merge payload
        return response.extract().path("number");
    }
}
