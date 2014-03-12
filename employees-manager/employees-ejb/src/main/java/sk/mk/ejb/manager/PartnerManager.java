package sk.mk.ejb.manager;

import sk.mk.persistence.entity.Partner;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: matejkobza
 * Date: 11.3.2014
 * Time: 18:43
 */
public interface PartnerManager {
    public List<Partner> find(Long id);

    public void saveOrUpdate(Partner partner);

    public Partner findPartner(Long id);

    public void delete(Long id);
}
