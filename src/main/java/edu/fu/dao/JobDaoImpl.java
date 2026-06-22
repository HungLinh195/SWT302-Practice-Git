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
    private EntityManager e;

    public JobDaoImpl() {
        e = DbContext.getEntityManager();
    }

    @Override
    public Job findById(Long id) {
        Session session = null;
        try {
            e = DbContext.getEntityManager();
            session = e.unwrap(Session.class);

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
        e = DbContext.getEntityManager();
        EntityTransaction tx = null;

        try {
            tx = e.getTransaction();
            tx.begin();

            e.persist(job);

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

        TypedQuery typedQuery = e.createQuery(
                "SELECT j FROM Job j", Job.class);

        return typedQuery.getResultList();
    }

    @Override
    public boolean isExisted(String title) {
        // Try with resources
        try (Session session = e.unwrap(Session.class);) {
           Long result = session.createQuery("SELECT COUNT(j) FROM Job j WHERE j.title = :title", Long.class)
                    .setParameter("title", title)
                    .getSingleResult();

           return  (result > 0);
        }
    }
}
