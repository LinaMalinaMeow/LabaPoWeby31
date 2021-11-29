package test;

import lombok.Data;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
@ViewScoped
@Data
public class ExampleBean {
    @ManagedProperty("db")
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
            employees.add(newEmployee);
        }
        newEmployee = new Employee();
    }

    public void clearEmployee() {
        employees.clear();
    }
}