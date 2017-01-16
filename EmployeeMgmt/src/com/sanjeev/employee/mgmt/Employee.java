package com.sanjeev.employee.mgmt;

/**
 * @author Sanjeev
 * @Since Feb 16, 2015
 * @Version 1.0
 */

public class Employee implements Comparable<Employee>
{
	private int deptId;
	private String empName = null;
	private String empCity = null;
	private int salary;

	public boolean equals(Object obj)
	{
		Employee emp = (Employee) obj;
		if(this == emp)
		{
			return true;
		}
		if(isEmployeeNull(emp))
		{
			return false;
		}
		return isEmployeeObjectsEqual(this, emp);
	}

	public int hashCode()
	{
		int hash = 7;
		hash = (37 * hash) + deptId;
		hash = (null == empName) ? 0 : empName.hashCode();
		hash = (null == empCity) ? 0 : empCity.hashCode();
		hash = (37 * hash) + salary;
		return hash;
	}

	private boolean isEmployeeObjectsEqual(Employee source, Employee target)
	{
		boolean isEqual = false;
		if(source.getDeptId() == target.getDeptId() && source.getEmpName().equals(target.getEmpName()) && source.getEmpCity().equals(target.getEmpCity()) && source.getSalary() == target.getSalary())
		{
			isEqual = true;
		}
		return isEqual;
	}

	private boolean isEmployeeNull(Employee emp)
	{
		return null == emp || null == emp.getEmpName() || null == emp.getEmpCity();
	}

	public int compareTo(Employee e)
	{
		return (deptId > e.getDeptId() ? 1 : (deptId < e.getDeptId()) ? -1 : 0);
	}

	public int getDeptId()
	{
		return deptId;
	}

	public void setDeptId(int deptId)
	{
		this.deptId = deptId;
	}

	public String getEmpName()
	{
		return empName;
	}

	public void setEmpName(String empName)
	{
		this.empName = empName;
	}

	public String getEmpCity()
	{
		return empCity;
	}

	public void setEmpCity(String empCity)
	{
		this.empCity = empCity;
	}

	public int getSalary()
	{
		return salary;
	}

	public void setSalary(int salary)
	{
		this.salary = salary;
	}

	public String toString()
	{
		String TAB = "\t";
		StringBuffer sb = new StringBuffer();
		sb.append("");
		sb.append(deptId).append(TAB);
		sb.append(empName).append(TAB);
		sb.append(empCity).append(TAB);
		sb.append(salary);
		return sb.toString();
	}
}
