package com.phantasm.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.phantasm.entity.Currencies;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.stream.Collectors;

/**
 * REST Controller to call fixer.io API and update the DB.<br/>
 * Connects directly to the API and populates the DB.
 *
 * @author Raghav Sharma {raghav.sharma_1995@outlook.com}
 */
public class ApiService {

    private static final Logger LOG = LoggerFactory.getLogger(ApiService.class);

    private static final String API_URL = "https://api.fixer.io/latest";

    private final String JDBC_URL = "jdbc:postgresql://localhost:5432/";
    private final String DATABASE_NAME = "Currency";
    private final String JDBC_USERNAME = "postgres";
    private final String JDBC_PASSWORD = "passw0rd";

    public static Currencies getRates() {
        try {
            // Set the API url
            final URL apiUrl = new URL(API_URL);

            // Fetch latest exchange rates
            final HttpsURLConnection api = (HttpsURLConnection) apiUrl.openConnection();

            // Set Request Method as GET
            api.setRequestMethod("GET");

            // Fetch latest exchange rates
            final InputStream apiInputStream = api.getInputStream();

            // Open response stream
            final BufferedReader responseBufferedReader = new BufferedReader(new InputStreamReader(apiInputStream));

            // Collect response
            final String JSON_RESPONSE = responseBufferedReader.lines().collect(Collectors.joining("\n"));

            // Close the stream
            responseBufferedReader.close();

            // Map to POJO
            ObjectMapper mapper = new ObjectMapper();

            // Return POJO
            return mapper.readValue(JSON_RESPONSE, Currencies.class);
        } catch (MalformedURLException e) {
            LOG.error("Problem with API URl.", e);
        } catch (IOException e) {
            LOG.error("Error opening connection to API.", e);
        }

        return null;
    }

    Connection connect() {
        LOG.info("Connecting to PostgreSQL server...");

        Connection conn = null;
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(JDBC_URL + DATABASE_NAME, JDBC_USERNAME, JDBC_PASSWORD);
            LOG.info("Connected to the PostgreSQL server successfully.");
        } catch (SQLException e) {
            LOG.error("Could not connect to PostgreSQL server,", e);
        } catch (ClassNotFoundException e) {
            LOG.error("Class.forName() threw an exception", e);
        }
        return conn;
    }
}
