package com.sanjeev.car.workshop.beans;

/**
 * @author Sanjeevkumar_Saxena
 *
 */

public class Task {

    private String name;
    private Integer serviceFee;
    private Integer timeTaken;

    public Task(String name, Integer serviceFee, Integer timeTaken) {
        this.name = name;
        this.serviceFee = serviceFee;
        this.timeTaken = timeTaken;
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

    /**
     * @return the timeTaken
     */
    public Integer getTimeTaken() {
        return timeTaken;
    }

    /**
     * @param timeTaken
     *            the timeTaken to set
     */
    public void setTimeTaken(Integer timeTaken) {
        this.timeTaken = timeTaken;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("TaskName - ").append(name).append(",");
        sb.append("ServiceFee - ").append(serviceFee).append(",");
        sb.append("TimeTaken - ").append(timeTaken);
        return sb.toString();
    }

}
