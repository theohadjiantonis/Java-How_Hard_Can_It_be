package org;

import com.google.gson.Gson;
import modelClasses.Company;
import utils.*;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;

public class Main {
    private static final String AUTHTOKEN = "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";
    private static final String USERNAME = "theohadjiantonis";
    private static final String REPONAME = "Java-How_Hard_Can_It_be";
    private static final String EMAIL = "theohadjiantonis@gmail.com";

    public static void main(String[] args) {
        //generate a list with company objects
        List<Company> companiesList = GenerateRecords.generateCompanies(10);

        //generate the CSV content from the list of Company objects
        String companiesJson = new Gson().toJson(companiesList);
        // Convert the CSV content to Base64, so it can be sent using the API call
        String base64Content = Base64.getEncoder().encodeToString(companiesJson.getBytes(StandardCharsets.UTF_8));
        // Make a REST API call to GitHub to create or update the file
        //ADD TOKEN, USERNAME AND EMAIL BEFORE RUNNING. NEED TO FIGURE OUT A BETTER WAY OF DOING THIS. CHECK IF EMAIL AND USERNAME ARE NEEDED
        String response = GithubCommitAPI.updateCsvFileInGithub(base64Content, "Companies.csv", "main", AUTHTOKEN, "Java-How_Hard_Can_It_be", USERNAME, EMAIL);

        //Create pull request and get pull request Id
        String prId = GithubPullRequestAPI.createPullRequest("main", "feature-branch", "merge", "mergin", AUTHTOKEN,USERNAME,REPONAME);
        //Merge code from master to develop
        GithubMergeRequestAPI.mergePullRequest(prId, "Get that CSV in here", AUTHTOKEN,USERNAME,REPONAME);
        for (Company company : companiesList) {
            System.out.println("Name: " + company.getName() +
                    ", Employees: " + company.getNumberOfEmployees() +
                    ", Customers: " + company.getNumberOfCustomers() +
                    ", Country: " + company.getCountry());
        }
    }
}