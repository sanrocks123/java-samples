package com.sanjeev.car.workshop.factory;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import com.sanjeev.car.workshop.beans.Employee;
import com.sanjeev.car.workshop.beans.Task;

/**
 * @author Sanjeevkumar_Saxena
 *
 */
public class CarWorkShopDbSingleton {

    private static CarWorkShopDbSingleton carWorkShopDbSingleton;
    private List<Employee> employees;
    private List<Task> tasks;

    private CarWorkShopDbSingleton() {
        employees = loadEmployees();
        tasks = loadTasks();
    }

    public static CarWorkShopDbSingleton getInstance() {
        if (null == carWorkShopDbSingleton) {
            Lock lock = new ReentrantLock();
            lock.lock();
            try {
                if (null == carWorkShopDbSingleton) {
                    carWorkShopDbSingleton = new CarWorkShopDbSingleton();
                }
            } finally {
                lock.unlock();
            }
        }
        return carWorkShopDbSingleton;
    }

    /**
     * @return
     */
    private List<Employee> loadEmployees() {
        List<Employee> employeeList = new CopyOnWriteArrayList<>();
        Employee e1 = new Employee("Joe", "Trainee");
        employeeList.add(e1);
        e1 = new Employee("Smith", "Expert");
        employeeList.add(e1);
        e1 = new Employee("Walker", "Employee");
        employeeList.add(e1);
        return employeeList;
    }

    /**
     * @return
     */
    private List<Task> loadTasks() {
        List<Task> tasks = new CopyOnWriteArrayList<>();

        Task t1 = new Task("Car-Wash", 100, 2);
        tasks.add(t1);
        t1 = new Task("Car-Paint", 1100, 4);
        tasks.add(t1);
        t1 = new Task("Car-Repair", 1000, 5);
        tasks.add(t1);
        t1 = new Task("Car-ReBranding", 190, 8);
        tasks.add(t1);
        t1 = new Task("Car-Interiors", 1200, 7);
        tasks.add(t1);

        return tasks;
    }

    /**
     * @return the employees
     */
    public List<Employee> getEmployees() {
        return employees;
    }

    /**
     * @return the tasks
     */
    public List<Task> getTasks() {
        return tasks;
    }
}
