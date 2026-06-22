package edu.fu.dao;

import edu.fu.entities.Department;

import java.util.List;

public interface DepartmentDao {
    Department findById(long id);

    Department create(Department department);

    Department update(Department department);

    void delete(Long id);

    List<Department> findAll();

    public List<Department> findByName(String name);

}
