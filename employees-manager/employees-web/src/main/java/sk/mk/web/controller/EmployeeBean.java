package sk.mk.web.controller;

import sk.mk.ejb.manager.EmployeeManager;
import sk.mk.persistence.entity.Employee;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: matejkobza
 * Date: 11.3.2014
 * Time: 18:36
 */
@ManagedBean
@ViewScoped
public class EmployeeBean implements Serializable {

    private static final long serialVersionUID = -345474247810022869L;

    private List<Employee> employees;
    private Employee employee;
//    private List<Employee> possibleSupervisors;
    private Long supervisorId;

    @EJB
    private EmployeeManager employeeManager;

    @PostConstruct
    public void init() {
        this.employees = employeeManager.list();
        this.employee = new Employee();
        this.supervisorId = null;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public void saveOrUpdate() {
        if (this.supervisorId != null) {
            for (Employee s : this.employees) {
                if (s.getId().equals(supervisorId)) {
                    employee.setSupervisor(s); // this could be done better, never mind
                }
            }
        }
        this.employeeManager.createOrUpdate(employee);
        this.init();
    }

    public void load(Long id) {
        this.employee = employeeManager.find(id);
    }

    public void delete(Long id) {
        employeeManager.delete(id);
        this.init();
    }

    public void reset() {
        this.employee = new Employee();
    }

    public List<Employee> getPossibleSupervisors() {
        List<Employee> possibleSupervisors = new ArrayList<Employee>(this.employees);
        if (this.employee.getId() != null) {
            for (Employee e : this.employees) {
                if (e.getId().equals(employee.getId())) {
                    possibleSupervisors.remove(e);
                }
            }
        }
        return possibleSupervisors;
    }

    public Long getSupervisorId() {
        return supervisorId;
    }

    public void setSupervisorId(Long supervisorId) {
        this.supervisorId = supervisorId;
    }
}
