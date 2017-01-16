
package com.sanjeev.car.workshop.beans;

import java.util.List;

/**
 * @author Sanjeevkumar_Saxena
 *
 */

public interface ISchedule {

    public Employee assignTasksToEmployee(Employee employee, List<Task> taskList);

    public List<Employee> getScheduledEmployeeList();
}
