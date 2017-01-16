
package com.sanjeev.car.workshop;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;

import com.sanjeev.car.workshop.beans.Employee;
import com.sanjeev.car.workshop.beans.ISchedule;
import com.sanjeev.car.workshop.beans.Schedule;
import com.sanjeev.car.workshop.enums.TaskPrioritization;

/**
 * @author Sanjeevkumar_Saxena
 *
 */

public class Executor {

    private static final XLogger log = XLoggerFactory.getXLogger(Executor.class);
    private static final int MAX_THREAD_POOL_SIZE = 5;

    public void start() {
        executeSchedule();
    }

    private void executeSchedule() {
        log.entry("Executor :: executeSchedule");
        ExecutorService executorService = Executors.newFixedThreadPool(MAX_THREAD_POOL_SIZE);

        List<Future<Employee>> futureEmployeeList = new ArrayList<Future<Employee>>();
        ISchedule schedule = new Schedule();

        for (Employee employee : schedule.getScheduledEmployeeList()) {
            Future<Employee> futureEmployee = executorService.submit(new SchedulerRunnable(employee, TaskPrioritization.SERVICE_FEE_DESC_ORDER));
            futureEmployeeList.add(futureEmployee);
        }

        executorService.shutdown();
        printResults(futureEmployeeList);

        log.exit("Executor :: executeSchedule");
    }

    /**
     * @param futureEmployeeList
     */
    private void printResults(List<Future<Employee>> futureEmployeeList) {
        log.entry("Executor :: printResults");

        System.out.println("\n*****************************************************************");
        System.out.println("Employee Name\tTask Name\t    Time taken\t\tFee\n");
        System.out.println("*******************************************************************\n");

        for (Iterator<Future<Employee>> futureEmployee = futureEmployeeList.iterator(); futureEmployee.hasNext();) {
            try {
                System.out.println(futureEmployee.next().get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

        log.exit("Executor :: printResults");
    }
}
