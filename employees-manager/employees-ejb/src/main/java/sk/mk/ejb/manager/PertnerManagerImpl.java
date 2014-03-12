package sk.mk.ejb.manager;

import sk.mk.persistence.dao.EmployeeDAO;
import sk.mk.persistence.dao.PartnerDAO;
import sk.mk.persistence.entity.Partner;

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
 * Time: 18:43
 */
@Dependent
@Named("partnerManager")
@Stateless
@Remote
public class PertnerManagerImpl implements PartnerManager {

    @Inject
    private PartnerDAO partnerDAO;

    @Inject
    private EmployeeDAO employeeDAO;

    @Override
    public List<Partner> find(Long id) {
        return partnerDAO.find(employeeDAO.find(id));
    }

    @Override
    public void saveOrUpdate(Partner partner) {
        if (partner.getId() != null) {
            partnerDAO.update(partner);
        } else {
            partnerDAO.create(partner);
        }
    }

    @Override
    public Partner findPartner(Long id) {
        return partnerDAO.findPartner(id);
    }

    @Override
    public void delete(Long id) {
        Partner p = partnerDAO.findPartner(id);
        partnerDAO.remove(p);
    }
}
