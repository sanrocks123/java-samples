
// PACKAGE/IMPORTS --------------------------------------------------
package com.sanjeev.go.euro.main;

import com.sanjeev.go.euro.exceptions.BaseException;
import com.sanjeev.go.euro.service.impl.LocationHandler;

/**
 * @author Sanjeevkumar_Saxena
 *
 */
public class Main {

    /**
     * @param args
     */
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Error - CityName is missing. Please provide city name as first command line argument to program.");
            System.exit(0);
        }

        try {
            System.out.println("Processing... Please wait");
            new LocationHandler().start(args[0]);
        } catch (BaseException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
