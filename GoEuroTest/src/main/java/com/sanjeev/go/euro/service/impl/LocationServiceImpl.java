package com.sanjeev.go.euro.service.impl;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;

import com.sanjeev.go.euro.exceptions.BaseException;
import com.sanjeev.go.euro.exceptions.MissingArgumentException;
import com.sanjeev.go.euro.services.LocationServices;
import com.sanjeev.go.euro.util.PropertiesLoader;

/**
 * @author Sanjeevkumar_Saxena
 *
 */
public class LocationServiceImpl implements LocationServices {

    private static XLogger log = XLoggerFactory.getXLogger(LocationServiceImpl.class);

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.sanjeev.go.euro.interfaces.LocationServices#getCityDetails(java.lang.
     * String)
     */
    @Override
    public JSONArray getCityDetails(String cityName) {
        log.debug(String.format("Input to getCitydDetails API value : '%s'", cityName));
        JSONArray result;
        if (cityName.isEmpty()) {
            throw new MissingArgumentException("City Name is missing. It is required to process location details.");
        }
        try {
            String formattedGetLocationApiUrl = String.format(PropertiesLoader.INSTANCE.loadProperties().getProperty("go.euro.location.api.url"), cityName);
            log.debug("Calling location API using formaated URL - " + formattedGetLocationApiUrl);

            URL url = new URL(formattedGetLocationApiUrl);
            HttpURLConnection httpConnection = (HttpURLConnection) url.openConnection();
            httpConnection.setRequestMethod("GET");

            if (httpConnection.getResponseCode() != 200) {
                throw new BaseException("Failed to call location API. Error code is - " + httpConnection.getResponseCode());
            }

            result = new JSONArray(IOUtils.toString(httpConnection.getInputStream(), "UTF-8"));
            httpConnection.disconnect();
            log.debug("API Call Result : " + result);

        } catch (MalformedURLException e) {
            throw new BaseException("Error in formatting URL. Error is - " + e.getMessage());

        } catch (IOException e) {
            throw new BaseException("Error in connecting to URL. Error is - " + e.getMessage());

        } catch (JSONException e) {
            throw new BaseException("Error in converting location API response to JSON object. Error is - " + e.getMessage());
        }
        return result;
    }

}
