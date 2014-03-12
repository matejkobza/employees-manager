package sk.mk.persistence.dao;

import sk.mk.persistence.entity.Employee;
import sk.mk.persistence.entity.Partner;

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
public class PartnerDAOImpl implements PartnerDAO {

    @PersistenceContext(unitName = "PU")
    private EntityManager em;

    @Inject
    private EmployeeDAO employeeDAO;

    public EntityManager getEm() {
        return em;
    }

    @Override
    public List<Partner> find(Employee employee) {
        return em.createQuery("SELECT p FROM Partner p WHERE p.contact=:contact", Partner.class)
                .setParameter("contact", employee).getResultList();
    }

    @Override
    public void update(Partner partner) {
        em.merge(partner);
    }

    @Override
    public void create(Partner partner) {
        em.persist(partner);
    }

    @Override
    public Partner findPartner(Long id) {
        return em.find(Partner.class, id);
    }

    @Override
    public void remove(Partner p) {
        em.remove(p);
    }
}
