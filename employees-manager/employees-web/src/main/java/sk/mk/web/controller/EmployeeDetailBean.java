package sk.mk.web.controller;

import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import sk.mk.ejb.manager.EmployeeManager;
import sk.mk.ejb.manager.PartnerManager;
import sk.mk.persistence.entity.Employee;
import sk.mk.persistence.entity.Partner;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: matejkobza
 * Date: 11.3.2014
 * Time: 22:11
 */
@ManagedBean
@ViewScoped
public class EmployeeDetailBean implements Serializable {

    private static final long serialVersionUID = 7054870447841963826L;

    private Long id;
    private TreeNode root;
    private Employee employee;
    private List<Partner> partners;
    private Partner partner;

    @EJB
    private EmployeeManager employeeManager;

    @EJB
    private PartnerManager partnerManager;

    @PostConstruct
    public void init() {
        this.id = Long.valueOf(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id"));
        this.partner = new Partner();
        employee = employeeManager.find(id);
        root = new DefaultTreeNode("root", null);
        constructTree(employee, root);
        reset();
    }

    public void reset() {
        this.partners = partnerManager.find(id);
    }

    public void resetForm() {
        this.partner = new Partner();
    }

    public TreeNode getRoot() {
        return root;
    }

    public void constructTree(Employee emp, TreeNode parent) {
        List<Employee> subordinates = employeeManager.findSubordinates(emp.getId());
        TreeNode node = new DefaultTreeNode(emp.getName() + " (" + emp.getPosition() + ")", parent);
        for (Employee subordinate : subordinates) {
            constructTree(subordinate, node);
        }
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public List<Partner> getPartners() {
        return partners;
    }

    public void setPartners(List<Partner> partners) {
        this.partners = partners;
    }

    public Partner getPartner() {
        return partner;
    }

    public void setPartner(Partner partner) {
        this.partner = partner;
    }

    public void saveOrUpdatePartner() {
        partner.setContact(this.employee);
        partnerManager.saveOrUpdate(partner);
        reset();
    }

    public void deletePartner(Long id) {
        partnerManager.delete(id);
        reset();
    }

    public void editPartner(Long id) {
        this.partner = partnerManager.findPartner(id);
    }
}
