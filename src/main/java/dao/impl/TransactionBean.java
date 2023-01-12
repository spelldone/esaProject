package dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import java.util.function.Consumer;

public class TransactionBean {

    @PersistenceContext(unitName = "default")
    protected EntityManager entityManager;

    public TransactionBean() {}

    public TransactionBean(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    protected void executeInsideTransaction(Consumer<EntityManager> action) {

        EntityTransaction tx = getEntityManager().getTransaction();
        try {
            tx.begin();

            action.accept(getEntityManager());

            tx.commit();
        } catch (RuntimeException e) {
            tx.rollback();

            throw e;
        }
    }

    protected EntityManager getEntityManager() {
        return this.entityManager;
    }
}
