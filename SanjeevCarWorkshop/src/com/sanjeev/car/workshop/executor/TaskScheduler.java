package com.sanjeev.car.workshop.executor;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.Callable;

import com.sanjeev.car.workshop.beans.Task;
import com.sanjeev.car.workshop.beans.TaskPrioritazation;
import com.sanjeev.car.workshop.factory.TaskComparatorFactory;

/**
 * @author Sanjeevkumar_Saxena
 *
 */
public class TaskScheduler implements Callable<Map.Entry<String, List<Task>>> {

    private Map.Entry<String, List<Task>> entry;
    private TaskPrioritazation taskProritazation;

    /**
     * @param entry
     * @param taskProritazation
     */
    public TaskScheduler(Entry<String, List<Task>> entry, TaskPrioritazation taskProritazation) {
        this.entry = entry;
        this.taskProritazation = taskProritazation;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.util.concurrent.Callable#call()
     */
    @Override
    public Map.Entry<String, List<Task>> call() throws Exception {
        Comparator<Task> comparator = TaskComparatorFactory.getComparator(taskProritazation);
        Collections.sort(entry.getValue(), comparator);
        return entry;
    }

}
