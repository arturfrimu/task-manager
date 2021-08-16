package com.stefanini.taskmanager.services.database;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

import com.stefanini.taskmanager.services.utils.ReadFromProperties;
import org.apache.log4j.Logger;

public class DataBaseConnection {
    public static DataBaseConnection dataBaseConnection = null;
    private Connection dbConn = null;

    static Logger logger = Logger.getLogger(DataBaseConnection.class);

    private DataBaseConnection() {}

    public static DataBaseConnection getInstance() throws IOException {
        if(dataBaseConnection == null) {
            dataBaseConnection = new DataBaseConnection();
            dataBaseConnection.initDatabaseConnection();
        }
        return dataBaseConnection;
    }

    private void initDatabaseConnection() throws IOException {
        try {
            Properties prop = ReadFromProperties.read("application.properties");
            Class.forName(prop.getProperty("DRIVER"));
            dbConn = DriverManager.getConnection(prop.getProperty("URL"), prop.getProperty("USER"), prop.getProperty("PASSWORD"));
        } catch (Exception e) {
            logger.error("Exception: " + e);
        }
    }

    public Connection getDBConnection() throws Exception {
        if(dbConn == null) {
            logger.error("Db connection not initialized");
        }
        return dbConn;
    }
}
