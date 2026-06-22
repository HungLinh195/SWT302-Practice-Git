package edu.fu.dao;

import edu.fu.entities.Job;
import edu.fu.utils.DbContext;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JobDaoImpl implements JobDao {
    private EntityManager entityManager;

    public JobDaoImpl() {
        entityManager = DbContext.getEntityManager();
    }

    @Override
    public Job findById(Long id) {
        Session session = null;
        try {
            entityManager = DbContext.getEntityManager();
            session = entityManager.unwrap(Session.class);

            return session.find(Job.class, id);

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
            // checked - compile time
            //throw new Exception("Has a error occurred" + e.getMessage());

            // Unchecked Exception - runtime
            throw new RuntimeException("Has a error occurred\" + e.getMessage()");
        } finally {
            if (session != null) {
                session.close();
            }
        }

    }

    @Override
    public Job createJob(Job job) {
        entityManager = DbContext.getEntityManager();
        EntityTransaction tx = null;

        try {
            tx = entityManager.getTransaction();
            tx.begin();

            entityManager.persist(job);

            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            throw new RuntimeException(e);
        }
        return job;
    }

    @Override
    public List<Job> findAllJobs() {

        TypedQuery typedQuery = entityManager.createQuery(
                "SELECT j FROM Job j", Job.class);

        return typedQuery.getResultList();
    }

    @Override
    public boolean isExisted(String title) {
        // Try with resources
        try (Session session = entityManager.unwrap(Session.class);) {
           Long result = session.createQuery("SELECT COUNT(j) FROM Job j WHERE j.title = :title", Long.class)
                    .setParameter("title", title)
                    .getSingleResult();

           return  (result > 0);
        }
    }
}
