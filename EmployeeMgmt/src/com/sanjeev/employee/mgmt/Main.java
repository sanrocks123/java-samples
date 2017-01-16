package com.sanjeev.employee.mgmt;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.logging.Logger;

/**
 * @author Sanjeev
 * @Since Feb 17, 2015
 * @Version 1.0
 */

public class Main
{
	private static final Logger log = Logger.getLogger(Main.class.getName());

	public static void main(String[] args)
	{
		IEmployeeOperations empOperations = new EmployeeOperationsImpl();
		String fileName = "employee-records.txt";
		List<Employee> empList = null;
		try
		{
			empList = empOperations.readEmployees(fileName);
		}
		catch(FileNotFoundException ex)
		{
			log.info(fileName + " does not exists in project directory.");
		}

		empOperations.retrieveEmployeeGroupByDeptHavingMaxSal(empList);
		
	}
}
