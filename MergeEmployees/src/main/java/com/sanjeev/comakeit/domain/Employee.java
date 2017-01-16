
// PACKAGE/IMPORTS --------------------------------------------------
package com.sanjeev.comakeit.domain;

/**
 * @author Sanjeevkumar_Saxena
 *
 */
public class Employee {

    private String code;
    private String name;
    private String department;
    private String number;

    /**
     * @param string
     * @param string2
     * @param string3
     * @param string4
     */
    public Employee(String code, String name, String department, String number) {
        this.code = code;
        this.name = name;
        this.department = department;
        this.number = number;
    }

    @Override
    public boolean equals(Object obj) {
        if (null == obj)
            return false;
        if (obj instanceof Employee) {
            Employee emp = (Employee) obj;
            return this.getCode().equals(emp.getCode()) && this.getName().equals(emp.getName()) && this.getDepartment().equals(emp.getDepartment())
                    && this.getNumber().equals(emp.getNumber());
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hashCode = 10;
        hashCode = hashCode * this.getCode().hashCode();
        hashCode = hashCode * this.getName().hashCode();
        hashCode = hashCode * this.getDepartment().hashCode();
        hashCode = hashCode * this.getNumber().hashCode();
        return hashCode * 128;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.code).append(",");
        sb.append(this.name).append(",");
        sb.append(this.department).append(",");
        sb.append(this.number);
        return sb.toString();
    }

    /**
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code
     *            the code to set
     */
    public void setCode(String code) {
        this.code = code;
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
     * @return the department
     */
    public String getDepartment() {
        return department;
    }

    /**
     * @param department
     *            the department to set
     */
    public void setDepartment(String department) {
        this.department = department;
    }

    /**
     * @return the number
     */
    public String getNumber() {
        return number;
    }

    /**
     * @param number
     *            the number to set
     */
    public void setNumber(String number) {
        this.number = number;
    }

}
