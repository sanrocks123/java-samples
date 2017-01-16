
package com.sanjeev.car.workshop.beans;

/**
 * @author Sanjeevkumar_Saxena
 *
 */

public class Task {

    private static final String DELIMITTER = "\t\t";
    private static final String NEXTLINE = "\n";
    private String taskName = null;
    private Integer timeTakenInHrs = null;
    private Integer serviceFee = null;

    public Task(String taskName, Integer serviceFee, Integer timeTakenInHrs) {
        this.taskName = taskName;
        this.serviceFee = serviceFee;
        this.timeTakenInHrs = timeTakenInHrs;
    }

    /**
     * @return the taskName
     */
    public String getTaskName() {
        return taskName;
    }

    /**
     * @param taskName
     *            the taskName to set
     */
    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    /**
     * @return the timeTakenInHrs
     */
    public Integer getTimeTakenInHrs() {
        return timeTakenInHrs;
    }

    /**
     * @param timeTakenInHrs
     *            the timeTakenInHrs to set
     */
    public void setTimeTakenInHrs(Integer timeTakenInHrs) {
        this.timeTakenInHrs = timeTakenInHrs;
    }

    /**
     * @return the serviceFee
     */
    public Integer getServiceFee() {
        return serviceFee;
    }

    /**
     * @param serviceFee
     *            the serviceFee to set
     */
    public void setServiceFee(Integer serviceFee) {
        this.serviceFee = serviceFee;
    }

    @Override
    public String toString() {
        return new StringBuilder().append(DELIMITTER).append(this.taskName).append(DELIMITTER).append(this.timeTakenInHrs).append(DELIMITTER).append(this.serviceFee)
                .append(NEXTLINE).toString();
    }

}
