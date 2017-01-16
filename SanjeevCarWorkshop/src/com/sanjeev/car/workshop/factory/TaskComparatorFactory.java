package com.sanjeev.car.workshop.factory;

import java.util.Comparator;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import com.sanjeev.car.workshop.beans.Task;
import com.sanjeev.car.workshop.beans.TaskPrioritazation;

/**
 * @author Sanjeevkumar_Saxena
 *
 */
public class TaskComparatorFactory {

    private static Comparator<Task> serviceFeeBasedComparator;
    private static Comparator<Task> timeTakenBasedComparator;
    private static Lock lock = new ReentrantLock();

    /**
     * @param taskProritazation
     * @return
     */
    public static Comparator<Task> getComparator(TaskPrioritazation taskProritazation) {
        Comparator<Task> comparator = null;
        switch (taskProritazation) {

            case SERVICE_FEE:
                if (null == serviceFeeBasedComparator) {
                    lock.lock();
                    try {
                        if (null == serviceFeeBasedComparator)
                            serviceFeeBasedComparator = new Comparator<Task>() {
                                @Override
                                public int compare(Task o1, Task o2) {
                                    return (o1.getServiceFee() > o2.getServiceFee() ? -1 : o1.getServiceFee() < o2.getServiceFee() ? 1 : 0);
                                }
                            };
                    } finally {
                        lock.unlock();
                    }
                }
                comparator = serviceFeeBasedComparator;
                break;

            case TIME_TAKEN:
                if (null == timeTakenBasedComparator) {
                    lock.lock();
                    try {
                        if (null == timeTakenBasedComparator)
                            timeTakenBasedComparator = new Comparator<Task>() {
                                @Override
                                public int compare(Task o1, Task o2) {
                                    return (o1.getTimeTaken() > o2.getTimeTaken() ? -1 : o1.getTimeTaken() < o2.getTimeTaken() ? 1 : 0);
                                }
                            };
                    } finally {
                        lock.unlock();
                    }
                }
                comparator = timeTakenBasedComparator;
                break;

            default:
                throw new RuntimeException("Unsupported TaskPrioritization!");
        }
        return comparator;
    }

}
