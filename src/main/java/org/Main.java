package org;

import com.google.gson.Gson;
import modelClasses.Company;
import utils.*;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        //generate a list with company objects
        List<Company> companiesList = GenerateRecords.generateCompanies(10);

        //generate the CSV content from the list of Company objects
        String companiesJson = new Gson().toJson(companiesList);
        // Convert the CSV content to Base64, so it can be sent using the API call
        String base64Content = Base64.getEncoder().encodeToString(companiesJson.getBytes(StandardCharsets.UTF_8));
        // Make a REST API call to GitHub to create or update the file
        //ADD TOKEN, USERNAME AND EMAIL BEFORE RUNNING. NEED TO FIGURE OUT A BETTER WAY OF DOING THIS. CHECK IF EMAIL AND USERNAME ARE NEEDED
        String response = GithubCsvUpdater.updateCsvFileInGithub(base64Content, "Companies.csv", "main", "", "Java-How_Hard_Can_It_be", "", "");

        for (Company company : companiesList) {
            System.out.println("Name: " + company.getName() +
                    ", Employees: " + company.getNumberOfEmployees() +
                    ", Customers: " + company.getNumberOfCustomers() +
                    ", Country: " + company.getCountry());
        }
    }
}