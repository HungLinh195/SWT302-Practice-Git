package edu.fu.dao;

import edu.fu.entities.Department;
import edu.fu.entities.Job;
import edu.fu.utils.DbContext;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public class DepartmentDaoImpl implements DepartmentDao {
    private EntityManager eM;

    public DepartmentDaoImpl() {

        eM = DbContext.getEntityManager();
    }

    @Override
    public Department findById(long id) {
        // Connection
        Session session = null;
        try {
            // Create new session
            session = eM.unwrap(Session.class);

            // Query: null or not null
            // SELECT d FROM Department d WHERE d.id = :id

            Department department = session.get(Department.class, id);

            // Proxy object - design pattern
            Set<Job> actualJobs = department.getJobs(); // dept_id = 1L

            // query
            System.out.println(department.getDepartmentName() + "\t" + department.getJobs().size());

            return department;
        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage());
        } finally {
            if (session != null) {
                session.close();  // NullPointerException
            }
        }
    }

    @Override
    public List<Department> findByName(String name) {
        // Connection
        Session session = null;
        eM = DbContext.getEntityManager();
        try {
            // Create new session
            session = eM.unwrap(Session.class);

            Query<Department> query = session.createNamedQuery("findDepartmentByName", Department.class);
            query.setParameter("name", name);

            return query.getResultList();
        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage());
        } finally {
            if (session != null) {
                session.close();  // NullPointerException
            }
        }
    }

    @Override
    public Department create(Department department) {
        EntityTransaction transaction = null;

        try {
            transaction = eM.getTransaction();
            transaction.begin();

            eM.persist(department);

            transaction.commit();

            return department;

        } catch (Exception e) {
            transaction.rollback();
            throw new RuntimeException(e);
        }

    }

    @Override
    public Department update(Department department) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<Department> findAll() {
        return List.of();
    }
}
