/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.shivintestcountries;

import java.io.IOException;
import java.util.regex.Pattern;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 *
 * @author Shivin Saraf
 */
public class Util {
    private static Pattern p = Pattern.compile("^[a-zA-Z]*$"); 
                
    public static String executeGetRequest(String URL) throws IOException{
        try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
            HttpGet get = new HttpGet(URL);
            try (CloseableHttpResponse httpresponse = httpclient.execute(get)) {
                String response = EntityUtils.toString(httpresponse.getEntity());
                return response;
            }
        }
    } 
    
    public static boolean isValidInput(String s) {
        return p.matcher(s).find();
    }
}
