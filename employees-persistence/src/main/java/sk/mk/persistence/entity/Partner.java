package sk.mk.persistence.entity;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: matejkobza
 * Date: 10.3.2014
 * Time: 22:34
 *
 * This class represents business partner
 */
@Entity
@Table(name = "PARTNER")
public class Partner implements Serializable {

    private static final long serialVersionUID = 887724291717758632L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID_PARTNER", nullable = false)
    private Long id;

    @Column(name = "NAME")
    @Size(min = 2, max = 80)
    private String name;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "PHONE")
    private String phone;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_EMPLOYEE", nullable = false)
    private Employee contact;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Employee getContact() {
        return contact;
    }

    public void setContact(Employee contact) {
        this.contact = contact;
    }
}
