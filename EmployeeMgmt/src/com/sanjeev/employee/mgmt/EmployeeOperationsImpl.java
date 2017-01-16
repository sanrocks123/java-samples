package com.sanjeev.employee.mgmt;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.logging.Logger;

/**
 * @author Sanjeev
 * @Since Feb 16, 2015
 * @Version 1.0
 */

public class EmployeeOperationsImpl implements IEmployeeOperations
{
	private static final Logger log = Logger.getLogger(EmployeeOperationsImpl.class.getName());

	public List<Employee> readEmployees(String fileName) throws FileNotFoundException
	{
		log.entering(EmployeeOperationsImpl.class.getName(), "readEmployees");
		List<Employee> list = new ArrayList<Employee>();
		final String STR_COMMA = ",";
		Scanner empoyeeScanner = new Scanner(new File(fileName));
		String empoyeeRecord = null;
		String[] empFields = null;
		while(empoyeeScanner.hasNextLine())
		{
			empoyeeRecord = empoyeeScanner.nextLine();
			empFields = empoyeeRecord.split(STR_COMMA);
			list.add(prepareEmployeeRecord(empFields));
		}
		empoyeeScanner.close();
		log.finest(list.toString());
		log.info("Total employees read count : " + list.size() + "\n");
		log.exiting(EmployeeOperationsImpl.class.getName(), "readEmployees");
		return list;
	}

	private Employee prepareEmployeeRecord(String[] empFields)
	{
		Employee emp = null;
		int i = 0;
		emp = new Employee();
		emp.setDeptId(Integer.valueOf(empFields[i++]));
		emp.setEmpName(empFields[i++]);
		emp.setEmpCity(empFields[i++]);
		emp.setSalary(Integer.valueOf(empFields[i++]));
		return emp;
	}

	@Override
	public Map<Integer,List<Employee>> retrieveEmployeeGroupByDeptHavingMaxSal(List<Employee> empList)
	{
		Map<Integer,List<Employee>> empMap = new TreeMap<Integer,List<Employee>>();
		Employee currentEmp = null;

		for(Iterator<Employee> empIterator = empList.iterator() ; empIterator.hasNext() ;)
		{
			currentEmp = empIterator.next();
			if(empMap.containsKey(currentEmp.getDeptId()))
			{
				sortEmployeesBelongingToSameDeptBasedOnSalary(empMap, currentEmp);
			}
			else
			{
				addEmployeeToNewDept(empMap, currentEmp);
			}
		}
		printSortedMap(empMap);
		return empMap;
	}

	private void addEmployeeToNewDept(Map<Integer,List<Employee>> empMap, Employee currentEmp)
	{
		List<Employee> currEmpListMappedToSameDept;
		currEmpListMappedToSameDept = new CopyOnWriteArrayList<Employee>();
		currEmpListMappedToSameDept.add(currentEmp);
		empMap.put(currentEmp.getDeptId(), currEmpListMappedToSameDept);
	}

	private void sortEmployeesBelongingToSameDeptBasedOnSalary(Map<Integer,List<Employee>> empMap, Employee currentEmp)
	{
		Employee existingEmp;
		List<Employee> currEmpListMappedToSameDept;
		currEmpListMappedToSameDept = empMap.get(currentEmp.getDeptId());
		Iterator<Employee> listIterator = currEmpListMappedToSameDept.iterator();
		while(listIterator.hasNext())
		{
			existingEmp = listIterator.next();
			compareSalariesOfEmployess(currentEmp, existingEmp, currEmpListMappedToSameDept);
			empMap.put(currentEmp.getDeptId(), currEmpListMappedToSameDept);
		}
	}

	private void compareSalariesOfEmployess(Employee currentEmp, Employee existingEmp, List<Employee> currEmpListMappedToSameDept)
	{
		if(currentEmp.getSalary() > existingEmp.getSalary())
		{
			currEmpListMappedToSameDept.remove(existingEmp);
			currEmpListMappedToSameDept.add(currentEmp);
		}
		else if(currentEmp.getSalary() == existingEmp.getSalary())
		{
			if(!currentEmp.equals(existingEmp))
			{
				currEmpListMappedToSameDept.add(currentEmp);
			}
		}
	}

	public void printSortedMap(Map<Integer,List<Employee>> sortedMapGroupByDeptIdHavingMaxSal)
	{
		System.out.println("DeptNo \t" + "Name \t" + "City \t" + "Salary\n");
		for(Map.Entry<Integer,List<Employee>> entry : sortedMapGroupByDeptIdHavingMaxSal.entrySet())
		{
			for(Iterator<Employee> it = entry.getValue().iterator() ; it.hasNext() ;)
			{
				System.out.println(it.next());
			}
		}
	}
}
