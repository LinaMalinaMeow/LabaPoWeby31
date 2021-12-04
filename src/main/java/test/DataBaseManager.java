package test;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.RollbackException;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.SystemException;
import java.util.List;

@ManagedBean(name = "database")
@ApplicationScoped
public class DataBaseManager {
    private String PERSISTENCE_UNIT_NAME = "postgres";
    private EntityManager entityManager;
//
//    @PersistenceContext(unitName = PERSISTENCE_UNIT_NAME)
//    private EntityManager entityManager;
//
//    @Resource
//    private UserTransaction userTransaction;

    public DataBaseManager() {
        connection();
    }

    private void connection() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        entityManager = entityManagerFactory.createEntityManager();
    }

    public synchronized void addEmployeeToDB(Employee employee) {
        entityManager.getTransaction().begin();
        entityManager.persist(employee);
        entityManager.getTransaction().commit();
    }

    public synchronized void clearBD(Employee employee) throws SystemException, NotSupportedException, HeuristicRollbackException, HeuristicMixedException, RollbackException {
        entityManager.getTransaction().begin();
        entityManager.remove(entityManager.merge(employee));
        entityManager.getTransaction().commit();
    }

    public List<Employee> loadEmployees() {
        return entityManager.createQuery("SELECT e FROM test.Employee e").getResultList();
    }
}