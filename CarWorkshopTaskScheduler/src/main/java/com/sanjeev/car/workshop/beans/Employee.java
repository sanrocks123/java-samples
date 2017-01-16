package com.sanjeev.car.workshop.beans;

import java.util.Iterator;
import java.util.List;

/**
 * @author Sanjeevkumar_Saxena
 *
 */

public class Employee {

    private String name = null;
    private String designation = null;
    private List<Task> tasks = null;

    public Employee(String name, String designation) {
        this.name = name;
        this.designation = designation;
    }

    @Override
    public String toString() {
        return new StringBuilder().append(this.name).append(getFormattedTasks()).toString();
    }

    private String getFormattedTasks() {
        StringBuilder sb = new StringBuilder();
        for (Iterator<Task> it = tasks.iterator(); it.hasNext();) {
            sb.append(it.next());
        }
        return sb.toString();
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
