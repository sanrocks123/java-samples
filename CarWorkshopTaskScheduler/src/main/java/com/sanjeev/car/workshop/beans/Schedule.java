package com.sanjeev.car.workshop.beans;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;

import com.sanjeev.car.workshop.db.IWorkShopDB;
import com.sanjeev.car.workshop.db.WorkShopDBSingleton;
import com.sanjeev.car.workshop.enums.EmployeeName;
import com.sanjeev.car.workshop.enums.TaskName;

/**
 * @author Sanjeevkumar_Saxena
 *
 */

public class Schedule implements ISchedule {

    private static final XLogger log = XLoggerFactory.getXLogger(Schedule.class);
    private List<Employee> employeeList = null;

    public Schedule() {
        this.employeeList = new ArrayList<Employee>();
        createSchedule();
    }

    public void createSchedule() {
        log.entry("Schedule :: createSchedule");

        IWorkShopDB workShopDb = WorkShopDBSingleton.getInstance();
        Map<String, Employee> employeeMap = workShopDb.getEmployees();
        Map<String, Task> taskMap = workShopDb.getTasks();

        assignTasksToEmployee(employeeMap.get(EmployeeName.JOE.toString()), prepareTasksForJOE(taskMap));
        assignTasksToEmployee(employeeMap.get(EmployeeName.SMITH.toString()), prepareTasksForSMITH(taskMap));
        assignTasksToEmployee(employeeMap.get(EmployeeName.WALKER.toString()), prepareTasksForWalker(taskMap));

        log.exit("Schedule :: createSchedule");

    }

    /**
     * @param taskMap
     * @return
     */
    private List<Task> prepareTasksForJOE(Map<String, Task> taskMap) {
        List<Task> taskList = new ArrayList<Task>();
        taskList.add(taskMap.get(TaskName.CAR_WASH.toString()));
        taskList.add(taskMap.get(TaskName.CAR_REPAIR.toString()));
        taskList.add(taskMap.get(TaskName.CAR_PAINT.toString()));
        return taskList;
    }

    /**
     * @param taskMap
     * @return
     */
    private List<Task> prepareTasksForWalker(Map<String, Task> taskMap) {
        List<Task> taskList = new ArrayList<Task>();
        taskList.add(taskMap.get(TaskName.CAR_REPAIR.toString()));
        taskList.add(taskMap.get(TaskName.CAR_PAINT.toString()));
        return taskList;
    }

    /**
     * @param taskMap
     * @return
     */
    private List<Task> prepareTasksForSMITH(Map<String, Task> taskMap) {
        List<Task> taskList = new ArrayList<Task>();
        taskList.add(taskMap.get(TaskName.CAR_REPAIR.toString()));
        return taskList;
    }

    public Employee assignTasksToEmployee(Employee employee, List<Task> taskList) {
        employee.setTasks(taskList);
        this.employeeList.add(employee);
        return employee;
    }

    /**
     * @return the employeeList
     */
    public List<Employee> getScheduledEmployeeList() {
        return employeeList;
    }

}
