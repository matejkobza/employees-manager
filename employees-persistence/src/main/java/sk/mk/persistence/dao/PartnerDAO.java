package sk.mk.persistence.dao;

import sk.mk.persistence.entity.Employee;
import sk.mk.persistence.entity.Partner;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: matejkobza
 * Date: 10.3.2014
 * Time: 22:52
 */
public interface PartnerDAO {
    public List<Partner> find(Employee employee);

    public void update(Partner partner);

    public void create(Partner partner);

    public Partner findPartner(Long id);

    public void remove(Partner p);
}
