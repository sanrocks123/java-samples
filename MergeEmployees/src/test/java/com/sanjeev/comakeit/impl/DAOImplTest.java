package com.sanjeev.comakeit.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.sanjeev.comakeit.domain.Employee;

import mockit.Expectations;
import mockit.Mocked;
import mockit.Tested;
import mockit.integration.junit4.JMockit;

@RunWith(JMockit.class)
public class DAOImplTest {

    @Tested
    private DAOImpl daoImpl;

    @Test
    public void testMerge(@Mocked DBSingleton dbSingleton) {

        List<Employee> iSAPEmployees = new ArrayList<>();
        Employee e1 = new Employee("001", "XXX", "Infrastructure", "8888888888");
        iSAPEmployees.add(e1);
        Employee e2 = new Employee("004", "WWW", "Security", "7777777777");
        iSAPEmployees.add(e2);
        Employee e3 = new Employee("003", "ZZZ", "Catering", "9999999999");
        iSAPEmployees.add(e3);

        new Expectations(DAOImpl.class) {
            {
                Map<String, Employee> dbEmployees = new HashMap<>();
                dbEmployees.put("003", new Employee("003", "ZZZ", "Catering", "9975629765"));
                dbEmployees.put("002", new Employee("001", "XXX", "Infrastructure", "8888888888"));
                dbSingleton.getEmployees();
                result = dbEmployees;

                daoImpl.update((Employee) any, (Employee) any);
                result = e3;
            }
        };

        Map<String, Employee> actual = new DAOImpl().merge(iSAPEmployees);
        Map<String, Employee> expected = new HashMap<>();
        expected.put("001", e1);
        expected.put("004", e2);
        expected.put("003", e3);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testUpdate() {
        Employee oldEmp = new Employee("001", "XXX", "Infrastructure", "8888888888");
        Employee newEmp = new Employee("001", "Sanjeev", "Infrastructure", "8888888888");
        Employee actual = daoImpl.update(oldEmp, newEmp);
        Assert.assertEquals(newEmp, actual);
    }

    @Test
    public void testWriteToFile() {
        Employee e1 = new Employee("001", "XXX", "Infrastructure", "8888888888");
        Employee e2 = new Employee("004", "WWW", "Security", "7777777777");
        Employee e3 = new Employee("003", "ZZZ", "Catering", "9999999999");

        Map<String, Employee> expected = new HashMap<>();
        expected.put("001", e1);
        expected.put("004", e2);
        expected.put("003", e3);

        daoImpl.writeToFile(expected);
    }

    @Test
    public void testUniqueEmployees() {

        List<Employee> e1 = new ArrayList<Employee>();
        e1.add(new Employee("001", "XXX", "Infrastructure", "8888888888"));
        e1.add(new Employee("004", "WWW", "Security", "7777777777"));
        e1.add(new Employee("003", "ZZZ", "Catering", "9999999999"));

        List<Employee> e2 = new ArrayList<Employee>();
        e2.add(new Employee("001", "XXX", "Infrastructure", "8888888888"));

        List<Employee> expected = new ArrayList<>();
        expected.add(new Employee("001", "XXX", "Infrastructure", "8888888888"));
        expected.add(new Employee("004", "WWW", "Security", "7777777777"));
        expected.add(new Employee("003", "ZZZ", "Catering", "9999999999"));

        List<Employee> actual = daoImpl.addUnique(e1, e2);
        Assert.assertEquals(expected, actual);
    }

}
