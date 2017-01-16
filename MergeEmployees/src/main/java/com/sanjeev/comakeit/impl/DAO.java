package com.sanjeev.comakeit.impl;

import java.util.List;
import java.util.Map;

import com.sanjeev.comakeit.domain.Employee;

/**
 * @author Sanjeevkumar_Saxena
 *
 */
public interface DAO {

    public Map<String, Employee> merge(List<Employee> iSAPEmployees);

    public Employee update(Employee oldEmp, Employee newEmp);

    public void writeToFile(Map<String, Employee> employees);

}
