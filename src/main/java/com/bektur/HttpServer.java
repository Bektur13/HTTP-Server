package com.bektur;

import com.bektur.config.Configuration;
import com.bektur.config.ConfigurationManager;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpServer {
    public static void main(String[] args) {
        System.out.println("Starting HTTP Server...");

        ConfigurationManager.getInstance().loadConfigurationFile("http.json");
        Configuration conf = ConfigurationManager.getInstance().getCurrentConfiguration();

        System.out.println("Using port: " + conf.getRoot());
        System.out.println("Using WebRoot: " + conf.getWebroot());

        try (ServerSocket serverSocket = new ServerSocket(conf.getRoot())) {
            while (true) {
                try (Socket socket = serverSocket.accept();
                     InputStream inputStream = socket.getInputStream();
                     OutputStream outputStream = socket.getOutputStream()) {

                    String html = "<html><head><title>Page Title</title></head><body><h1>This is a Heading</h1><p>This is a paragraph.</p></body></html>";
                    final String crlf = "\r\n";
                    String response = "HTTP/1.1 200 OK" + crlf +
                            "Content-Length: " + html.getBytes().length + crlf +
                            "Content-Type: text/html" + crlf +
                            crlf +
                            html + crlf;
                    outputStream.write(response.getBytes());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Server error", e);
        }
    }
}