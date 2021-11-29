package test;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.*;
import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

@ManagedBean(name = "database")
@ApplicationScoped
public class DataBaseManager {
    private static final String PERSISTENCE_UNIT_NAME = "postgres";

    @PersistenceContext(unitName = PERSISTENCE_UNIT_NAME)
    private EntityManager entityManager;

    @Resource
    private UserTransaction userTransaction;


    public synchronized void addEmployeeToDB(Employee employee) throws Exception {
        userTransaction.begin();
        entityManager.persist(employee);
        userTransaction.commit();
    }

    public synchronized void clearBD(Employee employee) throws SystemException, NotSupportedException, HeuristicRollbackException, HeuristicMixedException, RollbackException {
        userTransaction.begin();
        entityManager.remove(entityManager.merge(employee));
        userTransaction.commit();
    }

    public List<Employee> loadEmployee() {
        return entityManager.createQuery("SELECT e FROM test.Employee e").getResultList();
    }
}
