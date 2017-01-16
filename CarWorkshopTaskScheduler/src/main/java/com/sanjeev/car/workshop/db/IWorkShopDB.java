package com.sanjeev.car.workshop.db;

import java.util.Map;

import com.sanjeev.car.workshop.beans.Employee;
import com.sanjeev.car.workshop.beans.Task;

/**
 * @author Sanjeevkumar_Saxena
 *
 */
public interface IWorkShopDB {

    public Map<String, Employee> createEmployees();

    public Map<String, Task> createTasks();

    public Map<String, Employee> getEmployees();

    public Map<String, Task> getTasks();
}
