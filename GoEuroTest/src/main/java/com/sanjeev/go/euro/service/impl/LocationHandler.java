// PACKAGE/IMPORTS --------------------------------------------------
package com.sanjeev.go.euro.service.impl;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.Properties;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;

import com.sanjeev.go.euro.exceptions.BaseException;
import com.sanjeev.go.euro.exceptions.PropertyKeyNotFoundException;
import com.sanjeev.go.euro.services.LocationServices;
import com.sanjeev.go.euro.util.PropertiesLoader;

/**
 * @author Sanjeevkumar_Saxena
 *
 */
public class LocationHandler {

    private static final XLogger log = XLoggerFactory.getXLogger(LocationHandler.class);
    private static final String CSV_FILE_NAME = "go.euro.csv.file.name";
    private static final String KEY_NAMES = "go.euro.key.list";
    private LocationServices locSvc;
    private Properties prop;
    private String[] keyList;

    /**
    * 
    */
    public LocationHandler() {
        init();
    }

    /**
     * 
     */
    protected void init() {
        log.debug("Initializing Location handler...");
        locSvc = new LocationServiceImpl();
        prop = PropertiesLoader.INSTANCE.loadProperties();
        keyList = getPropertyValue(KEY_NAMES).split(",");

        Path path = Paths.get(getPropertyValue(CSV_FILE_NAME));
        if (!Files.exists(path)) {
            try {
                log.debug(String.format("File '%s' does not exist. Creating new file...", getPropertyValue(CSV_FILE_NAME)));
                Files.createFile(path);
            } catch (IOException e) {
                throw new BaseException(String.format("Error creating file '%s'.  Error is - %s ", path.getFileName(), e.getMessage()));
            }
        }
    }

    /**
     * 
     */
    protected String getPropertyValue(String key) {
        if (null == prop.getProperty(key)) {
            throw new PropertyKeyNotFoundException(String.format("Error - Property key '%s' is missing from properties file.", CSV_FILE_NAME));
        }
        return prop.getProperty(key);
    }

    /**
     * @param cityName
     * 
     */
    public void start(String cityName) {
        JSONArray result = locSvc.getCityDetails(cityName);

        try (PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(getPropertyValue(CSV_FILE_NAME), false)));) {
            for (Iterator<Object> it = result.iterator(); it.hasNext();) {
                pw.write(formatObject((JSONObject) it.next()));
            }
        } catch (IOException e) {
            throw new BaseException(String.format("Error writing to file. Error is %s", e.getMessage()));
        }

        System.out.println("Done! please check output file in current directory.");
    }

    /**
     * @param result
     * @return
     */
    protected String formatObject(JSONObject result) {
        StringBuilder sb = new StringBuilder();
        for (String key : keyList) {
            sb = walkThroughJsonAndGetValue(result, sb, key);
        }
        sb.replace(sb.lastIndexOf(",") - 1, sb.lastIndexOf(","), "\n");
        return sb.toString();
    }

    /**
     * @param sb
     * @param key
     */
    private StringBuilder walkThroughJsonAndGetValue(JSONObject result, StringBuilder sb, String key) {
        String jKeyName;
        for (Iterator<String> it = result.keys(); it.hasNext();) {
            jKeyName = it.next();
            if (jKeyName.equals(key)) {
                sb.append(result.get(key)).append(",");
                break;
            } else if (result.get(jKeyName) instanceof JSONObject) {
                sb = walkThroughJsonAndGetValue(result.getJSONObject(jKeyName), sb, key);
            }
        }
        return sb;
    }

}
