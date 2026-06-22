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
import java.util.Optional;
import java.util.Set;

@Repository
public class DepartmentDaoImpl implements DepartmentDao {
    private EntityManager entityMgt;

    public DepartmentDaoImpl() {
        entityMgt = DbContext.getEntityManager();
    }

    /**
     * HoaNK - HE195013 - Test git
     */
    @Override

    public Department findById(long id) {
        try {
            // Tìm kiếm và bọc kết quả vào Optional để xử lý null an toàn
            return Optional.ofNullable(entityMgt.find(Department.class, id))
                    .orElseThrow(() -> new RuntimeException("Không tìm thấy phòng ban với ID: " + id));
        } catch (RuntimeException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new RuntimeException("Hệ thống gặp sự cố khi truy vấn", ex);
        }
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<Department> findByName(String name) {
        try {
            // Lấy EntityManager và tạo TypedQuery trực tiếp, không cần unwrap Session
            entityMgt = DbContext.getEntityManager();

            return entityMgt.createNamedQuery("findDepartmentByName", Department.class)
                    .setParameter("name", name)
                    .getResultList();
        } catch (Exception ex) {
            throw new RuntimeException("Lỗi khi tìm kiếm phòng ban theo tên: " + name, ex);
        }
    }

    @Override
    public Department create(Department department) {
        EntityTransaction transaction = null;

        try {
            transaction = entityMgt.getTransaction();
            transaction.begin();

            entityMgt.persist(department);

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
    public List<Department> findAll() {
        return List.of();
    }
}
