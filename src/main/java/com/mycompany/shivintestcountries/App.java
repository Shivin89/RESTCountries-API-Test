package com.mycompany.shivintestcountries;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


/**
 *
 * @author Shivin Saraf
 */
public class App {
    
    private static final ObjectMapper MAPPER = new ObjectMapper();

    public static void main(String[] args)
    {
        try {
            Scanner scan = new Scanner(System.in);
            System.out.println("Enter name or code: ");            
            String parameter = scan.nextLine().toUpperCase();
            int count = 0;
            
            if(Util.isValidInput(parameter)) {
                if(parameter.length() == 1 ||parameter.isEmpty()) {
                    System.out.print("Invalid Name Or Code:");                        
                   
                } else {
                    String url = "https://restcountries.eu/rest/v2/all";
                    List<Country> countries = Arrays.asList(MAPPER.readValue(Util.executeGetRequest(url), Country[].class));
                    for (Country country : countries) {
                        switch(parameter.length())
                        {
                            case 2:
                                if (country.getAlpha2Code().toUpperCase().equals(parameter)) {
                                    count++;
                                    System.out.print(String.format("COUNTRY: %s\r\n",country.getName()));
                                    System.out.print(String.format("CAPITAL: %s\r\n",country.getCapital()));
                                }
                                break;
                            case 3:
                                if (country.getAlpha3Code().toUpperCase().equals(parameter)) {
                                    count++;
                                    System.out.print(String.format("COUNTRY: %s\r\n",country.getName()));
                                    System.out.print(String.format("CAPITAL: %s\r\n",country.getCapital()));
                                }                            
                                break;
                            default:
                                if (country.getName().toUpperCase().contains(parameter)) {
                                    count++;
                                    //System.out.print("CAPITAL: " + found.get(0).getCapital());
                                    System.out.print(String.format("COUNTRY: %s\r\n",country.getName()));
                                    System.out.print(String.format("CAPITAL: %s\r\n",country.getCapital()));
                                }
                        }
                    }
                    if(count < 1)
                    {
                        System.out.print("Country not found");
                    }
                }
            } else {
                System.out.print("Invalid Input");
            }            
        } catch (Exception ex) {
            System.out.print(ex.getLocalizedMessage());
        }
    }   
}