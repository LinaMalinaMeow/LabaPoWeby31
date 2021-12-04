package test;

import lombok.Data;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.SystemException;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
@ApplicationScoped
@Data
public class ExampleBean {

    private Employee newEmployee = new Employee();

    private List<Employee> employees = new ArrayList<>();

    public List<Employee> getEmployees() {
        loadEmployees();
        return employees;
    }

    @ManagedProperty("#{database}")
    private DataBaseManager dataBaseManager;


    @PostConstruct
    private void loadEmployees() {
        employees = dataBaseManager.loadEmployees();
    }

    public void addEmployee() {
        if (Validator.isValidDate(newEmployee)) {
            try {
                dataBaseManager.addEmployeeToDB(newEmployee);
            } catch (Exception e) {
                e.printStackTrace();
            }
            employees.add(newEmployee);
        }
        newEmployee = new Employee();
    }

    public void clearEmployee() {
        try {
            for (Employee p : employees) {
                dataBaseManager.clearBD(p);
            }
        } catch (SystemException | NotSupportedException | HeuristicRollbackException | HeuristicMixedException e) {
            e.printStackTrace();
        } finally {
            employees.clear();
        }
    }
}