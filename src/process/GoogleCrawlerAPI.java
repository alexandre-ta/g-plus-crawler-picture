/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package process;

import com.google.gson.Gson;
import googlepluscrawler.GooglePlusAPIException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import json.java.tools.JSONArray;
import json.java.tools.JSONObject;
import model.UserModel;
import model.ActivityModel;
import tools.LogFile;

/**
 * Google Crawler API
 * @author Alexta
 */
public class GoogleCrawlerAPI {

    /**
     * Type
     */
    public enum type {
        USER,
        ACTIVITY
    };

    /**
     * Get list of users containing value parameter
     * @param name
     * @return
     */
    public List<UserModel> getUserIdByName(String name) {
        // Initialize our return value
        List<UserModel> result = new ArrayList<>();
        String json = getStringResponse(name, type.USER);
        //
        JSONObject jso = new JSONObject(json);
        JSONArray ja = jso.getJSONArray("items");
        // Convert the JSON request to our class object
        Gson gson = new Gson();
        for (int i = 0; i < ja.length(); i++) {
            JSONObject jsonSection = ja.getJSONObject(i);
            UserModel tmp = gson.fromJson(jsonSection.toString(), UserModel.class);
            result.add(tmp);
        }
        return result;
    }

    /**
     * Get list of activity by user id
     * @param userId
     * @return
     */
    public List<ActivityModel> getActivityIdByUserId(String userId) {
        // Initialize our return value
        List<ActivityModel> result = new ArrayList<>();
        String json = getStringResponse(userId, type.ACTIVITY);
        //
        JSONObject jso = new JSONObject(json);
        JSONArray ja = jso.getJSONArray("items");
        // Convert the JSON request to our class object
        Gson gson = new Gson();
        for (int i = 0; i < ja.length(); i++) {
            JSONObject jsonSection = ja.getJSONObject(i);
            ActivityModel tmp = gson.fromJson(jsonSection.toString(), ActivityModel.class);
            result.add(tmp);
        }
        System.out.println(result.size());
        return result;
    }

    /**
     * Get Json response by passing parameter into formatted url
     * @param parameter
     * @param choice
     * @return 
     */
    private String getStringResponse(String parameter, type choice) {
        // Initialize our return value
        String json = "";
        URLConnection gpAPI = null;
        BufferedReader reader;
        try {
            // Open a connection to the URL at our URI API request string
            switch (choice) {
                case ACTIVITY:
                    gpAPI = new URL(config.Config.URL_G_PEOPLE + "/" + parameter + "/activities/public?key=" + config.Config.API_KEY).openConnection();
                    break;
                case USER:
                    gpAPI = new URL(config.Config.URL_G_PEOPLE + "?query=" + parameter + "&key=" + config.Config.API_KEY).openConnection();
                    break;
            }
            // Declare an HTTPURLConnection resource, used to get the response code (and in the error case, evaluate)
            HttpURLConnection gpConnection = ((HttpURLConnection) gpAPI);
            // Determine if the request was a success (Code 200)
            if (gpConnection.getResponseCode() != 200) {
                // Request was not successful, pull error message from the server
                reader = new BufferedReader(new InputStreamReader(gpConnection.getErrorStream()));

                String input;
                while ((input = reader.readLine()) != null) {
                    json += input;
                }

                reader.close();
                // Generate a custom exception that parses the JSON output and displays to the user
                throw new GooglePlusAPIException(json);
            }
            // Get the response text from the server
            reader = new BufferedReader(new InputStreamReader(gpAPI.getInputStream()));
            
            String input;
            while ((input = reader.readLine()) != null) {
                json += input;
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
            LogFile.getInstance().write(LogFile.TypeLog.ERROR, parameter, e.getMessage());
        } finally {
            return json;
        }
    }
}
