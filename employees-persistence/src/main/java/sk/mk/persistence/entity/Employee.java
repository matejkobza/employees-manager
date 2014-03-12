package sk.mk.persistence.entity;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: matejkobza
 * Date: 10.3.2014
 * Time: 22:33
 */
@Entity
@Table(name = "EMPLOYEE")
public class Employee implements Serializable {

    private static final long serialVersionUID = 7753254282557142061L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID_EMPLOYEE", nullable = false)
    private Long id;

    @Column(name = "NAME")
    @Size(min = 2, max = 20)
//    @Pattern(regexp = "[A-Za-z]*")
    private String name;

    @Column(name = "POSITION")
    @Size(min = 2, max = 80)
    private String position;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_EMPLOYEE") // without this it will create another table
    // http://docs.jboss.org/hibernate/core/3.6/reference/en-US/html_single/#collections-mapping
    private Set<Partner> partners;

    @ManyToOne(targetEntity = Employee.class)
    @JoinColumn(name = "SUPERVISOR", referencedColumnName = "ID_EMPLOYEE", nullable = true)
    private Employee supervisor;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Partner> getPartners() {
        return partners;
    }

    public void setPartners(Set<Partner> partners) {
        this.partners = partners;
    }

    public Employee getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(Employee supervisor) {
        this.supervisor = supervisor;
    }
}
