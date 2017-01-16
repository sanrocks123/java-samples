package com.sanjeev.car.workshop.beans;

import java.util.List;

/**
 * @author Sanjeevkumar_Saxena
 *
 */

public class Employee {

    private String name;
    private String designation;
    private List<Task> tasks;

    public Employee(String name, String designation) {
        this.name = name;
        this.designation = designation;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     *            the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the designation
     */
    public String getDesignation() {
        return designation;
    }

    /**
     * @param designation
     *            the designation to set
     */
    public void setDesignation(String designation) {
        this.designation = designation;
    }

    /**
     * @return the tasks
     */
    public List<Task> getTasks() {
        return tasks;
    }

    /**
     * @param tasks
     *            the tasks to set
     */
    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

}
