package edu.fu.dao;

import edu.fu.entities.Department;
import edu.fu.utils.DbContext;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DepartmentDaoImpl implements DepartmentDao {

    // EntityManager nên được giữ xuyên suốt vòng đời của DAO
    private final EntityManager entityManager;

    public DepartmentDaoImpl() {
        this.entityManager = DbContext.getEntityManager();
    }

    @Override
    public Department findById(long id) {
        try {
            // Sử dụng trực tiếp EntityManager của JPA, không cần unwrap sang Hibernate Session
            // Hàm find() tự động trả về null nếu không tìm thấy, không lo NullPointerException
            return entityManager.find(Department.class, id);
        } catch (Exception ex) {
            throw new RuntimeException("Lỗi khi tìm phòng ban theo ID: " + id, ex);
        }
        // KHÔNG đóng entityManager hoặc session ở đây vì nó được quản lý tập trung bởi DbContext
    }

    @Override
    public List<Department> findByName(String name) {
        try {
            // Sử dụng TypedQuery chuẩn JPA thay vì Query của Hibernate
            TypedQuery<Department> query = entityManager.createNamedQuery("findDepartmentByName", Department.class);
            query.setParameter("name", "%" + name + "%"); // Hỗ trợ tìm kiếm gần đúng nếu cần

            return query.getResultList();
        } catch (Exception ex) {
            throw new RuntimeException("Lỗi khi tìm phòng ban theo tên: " + name, ex);
        }
    }

    @Override
    public Department create(Department department) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(department);
            transaction.commit();
            return department;
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw new RuntimeException("Lỗi khi tạo mới phòng ban", e);
        }
    }

    @Override
    public Department update(Department department) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            // merge() dùng để cập nhật thực thể (hoặc thêm mới nếu chưa tồn tại)
            Department updatedDepartment = entityManager.merge(department);
            transaction.commit();
            return updatedDepartment;
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw new RuntimeException("Lỗi khi cập nhật phòng ban", e);
        }
    }

    @Override
    public void delete(Long id) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            Department department = entityManager.find(Department.class, id);
            if (department != null) {
                entityManager.remove(department);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw new RuntimeException("Lỗi khi xóa phòng ban theo ID: " + id, e);
        }
    }

    @Override
    public List<Department> findAll() {
        try {
            // Sử dụng JPQL để lấy toàn bộ danh sách phòng ban
            String jpql = "SELECT d FROM Department d";
            return entityManager.createQuery(jpql, Department.class).getResultList();
        } catch (Exception ex) {
            throw new RuntimeException("Lỗi khi lấy danh sách toàn bộ phòng ban", ex);
        }
    }
}