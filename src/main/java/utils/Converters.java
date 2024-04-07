package utils;

import modelClasses.Company;

import java.util.Base64;
import java.util.List;

public class Converters {
    public static String convertListToCSV(List<Company> companies) {
        //convert the list into a csv format
        StringBuilder sb = new StringBuilder("Name, Number of Employees, Number of Customers, Country\n");
        for (Company company : companies) {
            sb.append(company.getName()).append(",")
                    .append(company.getNumberOfEmployees()).append(",")
                    .append(company.getNumberOfCustomers()).append(",")
                    .append(company.getCountry()).append("\n");
        }
        return sb.toString();
    }

    public static String convertBase64ToCSV(String encodedContents) {

        //remove artifacts
        encodedContents = encodedContents.replaceAll("\\s+", "");
        //convert into a byte array
        byte[] decodedBytes = Base64.getDecoder().decode(encodedContents);
        //convert to a readable string
        String originalString = new String(decodedBytes);
        return originalString;
    }
}