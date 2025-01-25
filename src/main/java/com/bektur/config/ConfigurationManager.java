package com.bektur.config;

import com.bektur.config.util.Json;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.*;

public class ConfigurationManager {

    private static ConfigurationManager myConfigurationManager;
    private static Configuration myCurrentConfiguration;

    private ConfigurationManager() {}

    public static ConfigurationManager getInstance() {
        if (myConfigurationManager == null) {
            myConfigurationManager = new ConfigurationManager();
        }
        return myConfigurationManager;
    }

    public void loadConfigurationFile(String filePath) {
        // Load file as a resource
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(filePath);
        if (inputStream == null) {
            throw new HttpConfigurationException("Configuration file not found: " + filePath);
        }

        // Read file contents
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }

            System.out.println("Loaded config: " + sb.toString()); // Debugging

            // Parse JSON
            JsonNode conf = Json.parse(sb.toString());
            myCurrentConfiguration = Json.fromJson(conf, Configuration.class);

        } catch (JsonProcessingException e) {
            throw new HttpConfigurationException("Error parsing the Configuration File!", e);
        } catch (IOException e) {
            throw new HttpConfigurationException("Error reading the Configuration File!", e);
        }
    }

    public Configuration getCurrentConfiguration() {
        if (myCurrentConfiguration == null) {
            throw new HttpConfigurationException("No Configuration Set!");
        }
        return myCurrentConfiguration;
    }
}