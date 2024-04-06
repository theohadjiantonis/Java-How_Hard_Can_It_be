package utils;

import modelClasses.Company;
import java.util.List;

public class ConvertListToCSV {
    public static String convertToCSV(List<Company> companies) {
        StringBuilder sb = new StringBuilder("Name,Number of Employees,Number of Customers,Country\n");
        for (Company company : companies) {
            sb.append(company.getName()).append(",")
                    .append(company.getNumberOfEmployees()).append(",")
                    .append(company.getNumberOfCustomers()).append(",")
                    .append(company.getCountry()).append("\n");
        }
        return sb.toString();
    }
}