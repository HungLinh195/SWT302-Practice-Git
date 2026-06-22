package edu.fu.main;

import edu.fu.dao.DepartmentDao;
import edu.fu.dao.DepartmentDaoImpl;
import edu.fu.entities.Department;

public class DepartmentTest {
    public static void main(String[] args) {
        DepartmentDao  departmentDao = new DepartmentDaoImpl();
        Department department = new Department();
        department.setDepartmentName("SE");
        department.setDescription("Software Engineering");

        System.out.println(departmentDao.create(department));

    }
}
