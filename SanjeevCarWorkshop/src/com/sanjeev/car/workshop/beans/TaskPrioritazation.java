package com.sanjeev.car.workshop.beans;

/**
 * @author Sanjeevkumar_Saxena
 *
 */

public enum TaskPrioritazation {
    TIME_TAKEN("Time Taken(Hours)"), SERVICE_FEE("SERVICE FEE");
    private String name;

    TaskPrioritazation(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
