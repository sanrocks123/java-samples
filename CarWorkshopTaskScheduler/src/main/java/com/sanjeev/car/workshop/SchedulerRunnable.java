
package com.sanjeev.car.workshop;

import java.util.Collections;
import java.util.Comparator;
import java.util.concurrent.Callable;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;

import com.sanjeev.car.workshop.beans.Employee;
import com.sanjeev.car.workshop.beans.Task;
import com.sanjeev.car.workshop.enums.TaskPrioritization;
import com.sanjeev.car.workshop.exceptions.InvalidUsageException;

/**
 * @author Sanjeevkumar_Saxena
 *
 */
public class SchedulerRunnable implements Callable<Employee> {

    private static final XLogger log = XLoggerFactory.getXLogger(SchedulerRunnable.class);
    private Employee employee = null;
    private TaskPrioritization taskPrioritazation = null;

    /**
     * @param employee
     * @param taskPrioritazation
     */

    public SchedulerRunnable(Employee employee, TaskPrioritization taskPrioritazation) {
        this.employee = employee;
        this.taskPrioritazation = taskPrioritazation;
    }

    public Employee call() throws Exception {
        log.entry("SchedulerRunnable :: call");

        switch (taskPrioritazation) {
            case TIME_TAKEN_DESC_ORDER:
                prioritzedEmployeeTaskBasedOnTimeTaken();
                break;
            case SERVICE_FEE_DESC_ORDER:
                prioritzedEmployeeTaskBasedOnServiceFee();
                break;
            default:
                throw new InvalidUsageException("Invalid Prioritization method.");
        }

        log.exit("SchedulerRunnable :: call");
        return employee;
    }

    private void prioritzedEmployeeTaskBasedOnTimeTaken() {
        Collections.sort(employee.getTasks(), new Comparator<Task>() {
            public int compare(Task task1, Task task2) {
                return sortInDescendingOrder(task1.getTimeTakenInHrs(), task2.getTimeTakenInHrs());
            };
        });
    }

    private void prioritzedEmployeeTaskBasedOnServiceFee() {
        Collections.sort(employee.getTasks(), new Comparator<Task>() {
            public int compare(Task task1, Task task2) {
                return sortInDescendingOrder(task1.getServiceFee(), task2.getServiceFee());
            };
        });
    }

    private int sortInDescendingOrder(int num1, int num2) {
        return num1 > num2 ? -1 : (num1 < num2) ? 1 : 0;
    }

}
