package com.stefanini.taskmanager.services.utils;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ReadFromProperties {
    static Logger logger = Logger.getLogger(ReadFromProperties.class);
    private static InputStream inputStream;
    private static Properties prop;

    public static Properties read(String filename) throws IOException {
        try {
            prop = new Properties();
            inputStream = ReadFromProperties.class.getClassLoader().getResourceAsStream(filename);
            if (inputStream != null) {
                prop.load(inputStream);
            } else {
                logger.error("property file " + filename + " not found in the classpath");
            }
            return prop;
        } catch (Exception e) {
            logger.error(e);
        } finally {
            if (inputStream != null)
                inputStream.close();
        }
        return prop;
    }
}
