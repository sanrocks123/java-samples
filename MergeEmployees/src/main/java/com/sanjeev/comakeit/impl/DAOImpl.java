// PACKAGE/IMPORTS --------------------------------------------------
package com.sanjeev.comakeit.impl;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.sanjeev.comakeit.domain.Employee;

/**
 * @author Sanjeevkumar_Saxena
 *
 */
public class DAOImpl implements DAO {

    /**
     * @param iSAPEmployees
     * @return
     */
    @Override
    public Map<String, Employee> merge(List<Employee> iSAPEmployees) {
        Map<String, Employee> updatedResults = new HashMap<>();
        Map<String, Employee> dbEmployees = DBSingleton.getInstance().getEmployees();
        Employee oldEmp;
        for (Employee newEmp : iSAPEmployees) {
            oldEmp = dbEmployees.get(newEmp.getCode());
            if (null == oldEmp) {
                updatedResults.put(newEmp.getCode(), newEmp);
            } else {
                updatedResults.put(newEmp.getCode(), update(oldEmp, newEmp));
            }
        }
        return updatedResults;
    }

    /**
     * @param emp
     * @param newEmp
     * @return
     */
    @Override
    public Employee update(Employee oldEmp, Employee newEmp) {
        oldEmp.setCode(newEmp.getCode());
        oldEmp.setName(newEmp.getName());
        oldEmp.setDepartment(newEmp.getDepartment());
        oldEmp.setNumber(newEmp.getNumber());
        return oldEmp;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.sanjeev.comakeit.impl.DAO#writeToFile(java.util.Map)
     */
    @Override
    public void writeToFile(Map<String, Employee> employees) {
        FileWriter fw = null;
        try {
            fw = new FileWriter("./src/main/resources/db-employees.csv");
            for (Map.Entry<String, Employee> entry : employees.entrySet()) {
                fw.write(entry.getValue().toString() + "\n");
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * @param e1
     * @param e2
     * @return
     */
    public List<Employee> addUnique(List<Employee> e1, List<Employee> e2) {
        for (Iterator<Employee> it = e1.iterator(); it.hasNext();) {
            Employee current = it.next();
            if (e2.contains(current)) {
                e2.remove(current);
            }
        }
        e1.addAll(e2);
        return e1;
    }
}
