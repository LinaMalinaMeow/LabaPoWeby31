package test;

import lombok.Data;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.transaction.*;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
@ApplicationScoped
@Data
public class ExampleBean {
    @ManagedProperty("#{database}")
    private DataBaseManager dataBaseManager;
    private Employee newEmployee = new Employee();

    private List<Employee> employees = new ArrayList<Employee>();

    @PostConstruct
    private void loadEmployees() {
        employees = dataBaseManager.loadEmployee();
    }

    public void addEmployee() {
        System.out.println(newEmployee.toString());
        if (Validator.isValidDate(newEmployee)){
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
            for (Employee p:employees) {
                dataBaseManager.clearBD(p);
            }
            employees.clear();
        } catch (SystemException | NotSupportedException | HeuristicRollbackException | HeuristicMixedException | RollbackException e) {
            e.printStackTrace();
        }
        employees.clear();
    }
}