package com.sanjeev.car.workshop.main;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

import com.sanjeev.car.workshop.beans.Task;
import com.sanjeev.car.workshop.beans.TaskPrioritazation;
import com.sanjeev.car.workshop.executor.TaskExecutorService;
import com.sanjeev.car.workshop.executor.TaskExecutorServiceImpl;

/**
 * @author Sanjeevkumar_Saxena
 *
 */
public class Main {

    /**
     * @param args
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        executeScheduler();
        System.out.println("Thanks for trying Car WorkShop Scheduler!");
    }

    private static void executeScheduler() throws InterruptedException, ExecutionException {
        TaskExecutorService taskExecService = new TaskExecutorServiceImpl();
        Map<String, List<Task>> schedule = taskExecService.createSchedule();

        System.out.print("\nMenu Options - \n");
        System.out.println("1\tTask Prioritization by Service Fee");
        System.out.println("2\tTask Prioritization by Time Taken");
        System.out.println("3\tRe Create Schedule");
        System.out.println("4\tExit");
        Scanner sc = new Scanner(System.in);
        System.out.print("\nYour Choice - ");
        try {
            switch (sc.nextInt()) {
                case 1:
                    taskExecService.executeSchedule(schedule, TaskPrioritazation.SERVICE_FEE);
                    break;
                case 2:
                    taskExecService.executeSchedule(schedule, TaskPrioritazation.TIME_TAKEN);
                    break;
                case 3:
                    schedule = taskExecService.createSchedule();
                    executeScheduler();
                    break;
                case 4:
                    System.exit(0);
                    break;
                default:
                    throw new InputMismatchException("Invalid Option");
            }
        } catch (InputMismatchException e) {
            System.out.println("\n\nError! Invalid Option selected. Please retry\n\n");
            executeScheduler();

        } finally {
            sc.close();
        }
    }
}
