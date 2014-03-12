package sk.mk.persistence.dao;

import sk.mk.persistence.entity.Employee;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: matejkobza
 * Date: 10.3.2014
 * Time: 22:52
 */
public interface EmployeeDAO {

    public List<Employee> list();

    public void create(Employee employee);

    public void update(Employee employee);

    public Employee find(Long id);

    public void delete(Long id);

    public List<Employee> findSubordinates(Long id);

//    public Employee load(Long id);
}
