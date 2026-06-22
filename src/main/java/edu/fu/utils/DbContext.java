package edu.fu.utils;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;

public class DbContext {
    private static EntityManager entityManager;

    public static EntityManager getEntityManager() {

        if (entityManager == null) {
            return Persistence.createEntityManagerFactory("ats-persistence-unit").createEntityManager();
        }

        return entityManager;
    }
}
