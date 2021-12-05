package test;

import lombok.Data;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
@ApplicationScoped
@Data
public class ExampleBean {

    private Employee newEmployee = new Employee();

    private List<Employee> employees = new ArrayList<>();


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
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            employees.clear();
        }
    }
    public List<Employee> getEmployees() {
        loadEmployees();
        return employees;
    }
}