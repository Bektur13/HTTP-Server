package com.bektur;

import com.bektur.config.Configuration;
import com.bektur.config.ConfigurationManager;

// Driver class for the HTTP Server
public class HttpServer {
    public static void main(String[] args) {
        System.out.println("Starting");
        System.out.println("Starting");

        ConfigurationManager.getInstance().loadConfigurationFile("src/main/resources/http.json");
        Configuration conf = ConfigurationManager.getInstance().getCurrentConfiguration();

        System.out.println("Using port: " + conf.getRoot());
        System.out.println("Using WebRoot: " + conf.getWebroot());
        System.out.println("he");
    }
}