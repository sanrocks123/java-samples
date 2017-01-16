// PACKAGE/IMPORTS --------------------------------------------------
package com.sanjeev.comakeit.impl;

import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import com.sanjeev.comakeit.domain.Employee;

/**
 * @author Sanjeevkumar_Saxena
 *
 */
public class DBSingleton {

    private static DBSingleton dbSingleton;
    private Map<String, Employee> employees;

    private DBSingleton() {
        employees = loadEmployees();
    }

    /**
     * @return
     */
    public static DBSingleton getInstance() {
        if (null == dbSingleton) {
            Object lock = new Object();
            synchronized (lock) {
                if (null == dbSingleton) {
                    dbSingleton = new DBSingleton();
                }
            }
        }
        return dbSingleton;
    }

    /**
     * @return
     */
    private Map<String, Employee> loadEmployees() {
        System.out.println("Initializing database...Please wait...");
        InputStream is = DBSingleton.class.getResourceAsStream("/db-employees.csv");
        Scanner in = new Scanner(is);
        String[] line;
        employees = new HashMap<>();
        try {
            while (in.hasNext()) {
                line = in.nextLine().split(",");
                System.out.println("Processing line : " + Arrays.asList(line));
                this.employees.put(line[0].trim(), new Employee(line[0].trim(), line[1].trim(), line[2].trim(), line[3].trim()));
            }
        } finally {
            System.out.println("Finished, closing file handler...");
            in.close();
        }
        return this.employees;
    }

    /**
     * @return
     */
    public Map<String, Employee> getEmployees() {
        return this.employees;
    }

}
