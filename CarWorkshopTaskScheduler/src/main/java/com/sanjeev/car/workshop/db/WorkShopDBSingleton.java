
package com.sanjeev.car.workshop.db;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;

import com.sanjeev.car.workshop.beans.Employee;
import com.sanjeev.car.workshop.beans.Task;
import com.sanjeev.car.workshop.enums.EmployeeName;
import com.sanjeev.car.workshop.enums.TaskName;
import com.sanjeev.car.workshop.exceptions.EmployeeAlreadyExistsException;
import com.sanjeev.car.workshop.exceptions.TaskAlreadyExistsException;

/**
 * @author Sanjeevkumar_Saxena
 *
 */

public class WorkShopDBSingleton implements IWorkShopDB {

    private static final XLogger log = XLoggerFactory.getXLogger(WorkShopDBSingleton.class);

    private static WorkShopDBSingleton workshopDb = new WorkShopDBSingleton();
    private Map<String, Employee> employees = null;
    private Map<String, Task> tasks = null;

    private WorkShopDBSingleton() {
        init();
    }

    public static WorkShopDBSingleton getInstance() {
        log.entry("WorkShopDBSingleton :: getInstance() ");
        if (null == workshopDb) {
            Object lockObject = new Object();
            synchronized (lockObject) {
                if (null == workshopDb) {
                    workshopDb = new WorkShopDBSingleton();
                }
            }
        }
        log.exit("WorkShopDBSingleton :: getInstance() ");
        return workshopDb;
    }

    public Map<String, Employee> createEmployees() {

        log.entry("WorkShopDBSingleton :: createEmployees() ");
        Map<String, Employee> employeesMap = new HashMap<String, Employee>();

        Employee emp = new Employee(EmployeeName.JOE.toString(), "Trainee");
        updateEmployeesMap(employeesMap, emp);

        emp = new Employee(EmployeeName.SMITH.toString(), "Expert");
        updateEmployeesMap(employeesMap, emp);

        emp = new Employee(EmployeeName.WALKER.toString(), "Employee");
        updateEmployeesMap(employeesMap, emp);

        log.exit("WorkShopDBSingleton :: createEmployees() ");
        return employeesMap;
    }

    public Map<String, Task> createTasks() {
        log.entry("WorkShopDBSingleton :: createTasks() ");

        Map<String, Task> tasksMap = new HashMap<String, Task>();

        Task task = new Task(TaskName.CAR_WASH.toString(), new Integer(100), new Integer(2));
        updateTasksMap(tasksMap, task);

        task = new Task(TaskName.CAR_REPAIR.toString(), new Integer(1000), new Integer(5));
        updateTasksMap(tasksMap, task);

        task = new Task(TaskName.CAR_PAINT.toString(), new Integer(1100), new Integer(4));
        updateTasksMap(tasksMap, task);

        log.exit("WorkShopDBSingleton :: createTasks() ");
        return tasksMap;
    }

    private void init() {
        this.employees = createEmployees();
        this.tasks = createTasks();
    }

    /**
     * @param employeesMap
     * @param emp
     */
    private void updateEmployeesMap(Map<String, Employee> employeesMap, Employee emp) {

        Employee updatedEmployee = employeesMap.put(emp.getName(), emp);
        if (null == updatedEmployee) {
            log.debug("Employee " + emp.getName() + " created.");
        } else {
            throw new EmployeeAlreadyExistsException("Employee - " + emp.getName() + " is already present in DB.");
        }
    }

    private void updateTasksMap(Map<String, Task> tasksMap, Task task) {

        Task updatedTask = tasksMap.put(task.getTaskName(), task);
        if (null == updatedTask) {
            log.debug("Task " + task.getTaskName() + " created.");
        } else {
            throw new TaskAlreadyExistsException("Task - " + task.getTaskName() + " is already present in DB.");
        }
    }

    /**
     * @return the employees
     */
    public Map<String, Employee> getEmployees() {
        return this.employees;
    }

    /**
     * @return the tasks
     */
    public Map<String, Task> getTasks() {
        return this.tasks;
    }

}
