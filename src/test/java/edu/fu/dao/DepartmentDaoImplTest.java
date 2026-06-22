package edu.fu.dao;

import edu.fu.entities.Department;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

class DepartmentDaoImplTest {

    private static DepartmentDao departmentDao;

    @BeforeAll
    public static void setup() {
        departmentDao = new DepartmentDaoImpl();
    }

    @Test
    void findById() {
        // Input
        Long id = 1L;

        // Actual Result
        Department actualResult = departmentDao.findById(id);

        System.out.println(actualResult);

        // Compare actual vs expected
        Assertions.assertNotNull(actualResult);
    }

    @Test
    void findByName() {
        String departmentName = "SE";

        List<Department> departments = departmentDao.findByName("%" + departmentName + "%");

        departments.forEach((d) -> {
            System.out.println(d.getDepartmentName() + "\t" + d.getJobs());
        });

        Assertions.assertNotNull(departments);
    }

    @Test
    void create() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    @Test
    void findAll() {
    }
}