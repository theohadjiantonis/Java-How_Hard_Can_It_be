package utils;

import io.restassured.http.ContentType;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class GithubPullRequestAPI {
    public static String createPullRequest(String sourceBranch, String targetBranch, String title, String body, String token, String userName, String repo) {
        Response response = RestAssured.given()
                .auth()
                .oauth2(token)
                .baseUri("https://api.github.com")
                .contentType(ContentType.JSON)
                .body("{\"title\": \"" + title + "\", \"body\": \"" + body + "\", \"head\": \"" + sourceBranch + "\", \"base\": \"" + targetBranch + "\"}")
                .post("/repos/" + userName + "/" + repo + "/pulls");

        System.out.println("Pull Request Response: ");
        response.prettyPrint();
        // Extract the PR id from the response. The actual extraction logic might need to be adjusted based on the actual response structure.
        // Assuming the response contains a JSON field named "id" at the root.
        String prId = response.jsonPath().getString("id");
        return prId;
    }
}
