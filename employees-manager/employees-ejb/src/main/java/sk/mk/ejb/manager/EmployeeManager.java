package sk.mk.ejb.manager;

import sk.mk.persistence.entity.Employee;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: matejkobza
 * Date: 11.3.2014
 * Time: 18:41
 */
public interface EmployeeManager {

    public List<Employee> list();

    /**
     * Create or update employee
     * @param employee
     */
    public void createOrUpdate(Employee employee);

    /**
     * Find one employee
     * @param id
     * @return
     */
    public Employee find(Long id);

    /**
     * Delete one employee
     * @param id
     */
    public void delete(Long id);

    public List<Employee> findSubordinates(Long id);

    /**
     * Load whole hierarchy of employees
     * @param id
     * @return Employee root of tree
     */
//    public Employee load(Long id);
}
