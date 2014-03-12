package sk.mk.persistence.dao;

import sk.mk.persistence.entity.Employee;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: matejkobza
 * Date: 10.3.2014
 * Time: 22:52
 */
public class EmployeeDAOImpl implements EmployeeDAO {

    @PersistenceContext(unitName = "PU")
    private EntityManager em;

    public EntityManager getEm() {
        return em;
    }

    @Override
    public List<Employee> list() {
        List<Employee> es = em.createQuery("SELECT e FROM Employee e", Employee.class).getResultList();
        em.detach(es);
        return es;
    }

    @Override
    public void create(Employee employee) {
        em.persist(employee);
    }

    @Override
    public void update(Employee employee) {
        em.merge(employee);
    }

    @Override
    public Employee find(Long id) {
        return em.find(Employee.class, id);
    }

    @Override
    public void delete(Employee e) {
        em.createQuery("DELETE Partner p WHERE p.contact=:contact").setParameter("contact", e).executeUpdate();
        em.remove(e);
    }

    @Override
    public List<Employee> findSubordinates(Long id) {
        Employee supervisor = this.find(id);
        return em.createQuery("SELECT e FROM Employee e WHERE e.supervisor=:supervisor", Employee.class)
                .setParameter("supervisor", supervisor).getResultList();
    }

//    @Override
//    public Employee load(Long id) {
//        em.createQuery("SELECT e FROM Employee e LEFT JOIN FETCH Employee.supervisor");
//    }
}
