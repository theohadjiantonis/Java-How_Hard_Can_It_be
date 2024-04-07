package utils;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;

public class GithubCommitAPI {

    public static void updateCsvFileInGithub(String base64Content, String filePath, String branch, String token, String repoName, String userName, String email) {
        //constract API request URL
        String url = String.format("https://api.github.com/repos/%s/%s/contents/%s", userName, repoName, filePath);
        //message for commit
        String message = "Updating CSV file";
        //retrieve SHA string
        String sha = GithubGetAPI.getFileSHA(url, token);

        // JSON payload for the request
        String payload = String.format("{"
                + "\"message\": \"%s\", "
                + "\"committer\": {"
                + "\"name\": \"%s\", "
                + "\"email\": \"%s\"}, "
                + "\"content\": \"%s\", "
                + "\"sha\": \"%s\", "
                + "\"branch\": \"%s\"}", message, userName, email, base64Content, sha, branch);
        //sent the commit request
        ValidatableResponse response = RestAssured.given()
                .header("Authorization", "token " + token)
                .header("Content-Type", "application/json")
                .body(payload)
                .when()
                .put("https://api.github.com/repos/" + userName + "/" + repoName + "/contents/" + filePath)
                .then()
                .statusCode(200);

        System.out.println("Update CSV");
    }
}