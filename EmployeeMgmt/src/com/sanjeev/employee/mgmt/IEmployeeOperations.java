package com.sanjeev.employee.mgmt;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;

/**
 * @author Sanjeev
 * @Since Feb 16, 2015
 * @Version 1.0
 */

public interface IEmployeeOperations
{
	public List<Employee> readEmployees(String fileName) throws FileNotFoundException;

	public Map<Integer,List<Employee>> retrieveEmployeeGroupByDeptHavingMaxSal(List<Employee> empList);
}
