package com.sanjeev.car.workshop.executor;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.sanjeev.car.workshop.beans.Employee;
import com.sanjeev.car.workshop.beans.Task;
import com.sanjeev.car.workshop.beans.TaskPrioritazation;
import com.sanjeev.car.workshop.factory.CarWorkShopDbSingleton;

/**
 * @author Sanjeevkumar_Saxena
 *
 */

public class TaskExecutorServiceImpl implements TaskExecutorService {

    private static final Integer MAX_THREAD_POOL_SIZE = 5;

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.sanjeev.car.workshop.executor.TaskExecutorService#createSchedule()
     */
    @Override
    public Map<String, List<Task>> createSchedule() {

        System.out.println("\n********************************************************************************************");
        System.out.println("Car WorkShop Schedule as of - " + Calendar.getInstance().getTime());

        Map<String, List<Task>> schedule = new HashMap<>();
        List<Task> task = null;
        List<Employee> employess = CarWorkShopDbSingleton.getInstance().getEmployees();
        List<Task> taskList = CarWorkShopDbSingleton.getInstance().getTasks();

        int taskListSize = taskList.size();
        int randomTaskSize;
        Random random = new Random();

        for (Iterator<Employee> it = employess.iterator(); it.hasNext();) {
            randomTaskSize = random.nextInt(taskListSize);
            task = new ArrayList<>();
            for (int i = randomTaskSize; i >= 0; i--) {
                task.add(taskList.get(i));
            }
            schedule.put(it.next().getName(), task);
        }

        printSchedule(schedule);
        return schedule;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.sanjeev.car.workshop.executor.TaskExecutorService#executeSchedule()
     */
    @Override
    public Map<String, List<Task>> executeSchedule(Map<String, List<Task>> schedule, TaskPrioritazation taskProritazation) throws InterruptedException, ExecutionException {

        ExecutorService execService = Executors.newFixedThreadPool(MAX_THREAD_POOL_SIZE);
        List<Future<Map.Entry<String, List<Task>>>> prioritizedScheduleList = new ArrayList<>();

        try {
            for (Map.Entry<String, List<Task>> entry : schedule.entrySet()) {
                Future<Map.Entry<String, List<Task>>> prioritizedSchedule = execService.submit(new TaskScheduler(entry, taskProritazation));
                prioritizedScheduleList.add(prioritizedSchedule);
            }
        } finally {
            execService.shutdown();
        }

        Map<String, List<Task>> prioritizedScheduleMap = new HashMap<>();
        for (Iterator<Future<Map.Entry<String, List<Task>>>> it = prioritizedScheduleList.iterator(); it.hasNext();) {
            Map.Entry<String, List<Task>> map = it.next().get();
            prioritizedScheduleMap.put(map.getKey(), map.getValue());
        }

        System.out.println("\nTask Prioritization based on " + taskProritazation.getName());
        printSchedule(prioritizedScheduleMap);

        return prioritizedScheduleMap;
    }

    /**
     * @param prioritizedScheduleMap
     */
    private void printSchedule(Map<String, List<Task>> prioritizedScheduleMap) {

        StringBuilder sb = new StringBuilder();
        sb.append("\nEmpName").append("\t\t").append("Task Name").append("\t").append("Service Fee($)").append("\t").append("Time Taken(Hrs)\n");
        System.out.println(sb.toString());

        Task task;
        for (Map.Entry<String, List<Task>> emp : prioritizedScheduleMap.entrySet()) {
            System.out.print(emp.getKey());
            for (Iterator<Task> it = emp.getValue().iterator(); it.hasNext();) {
                task = it.next();
                sb = new StringBuilder();
                sb.append("\t\t").append(task.getName()).append("\t\t").append(task.getServiceFee()).append("\t\t").append(task.getTimeTaken());
                System.out.println(sb.toString());
            }
            System.out.println();
        }
    }

}
