package sk.mk.ejb.manager;

import sk.mk.persistence.dao.EmployeeDAO;
import sk.mk.persistence.entity.Employee;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: matejkobza
 * Date: 11.3.2014
 * Time: 18:41
 */
@Dependent
@Named("employeeManager")
@Stateless
@Remote
public class EmployeeManagerImpl implements EmployeeManager {

    @Inject
    private EmployeeDAO employeeDAO;

    @Override
    public List<Employee> list() {
        return employeeDAO.list();
    }

    @Override
    public void createOrUpdate(Employee employee) {
        if (employee.getId() == null) {
            this.employeeDAO.create(employee);
        } else {
            this.employeeDAO.update(employee);
        }
    }

    @Override
    public Employee find(Long id) {
        return this.employeeDAO.find(id);
    }

    @Override
    public void delete(Long id) {
        Employee e = this.employeeDAO.find(id);
        this.employeeDAO.delete(e);
    }

    @Override
    public List<Employee> findSubordinates(Long id) {
        return employeeDAO.findSubordinates(id);
    }

//    @Override
//    public Employee load(Long id) {
//
//    }
}
