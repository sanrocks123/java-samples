package com.sanjeev.car.workshop.executor;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import com.sanjeev.car.workshop.beans.Task;
import com.sanjeev.car.workshop.beans.TaskPrioritazation;

/**
 * @author Sanjeevkumar_Saxena
 *
 */
public interface TaskExecutorService {

    public Map<String, List<Task>> createSchedule();

    public Map<String, List<Task>> executeSchedule(Map<String, List<Task>> schedule, TaskPrioritazation taskProritazation) throws InterruptedException, ExecutionException;

}
