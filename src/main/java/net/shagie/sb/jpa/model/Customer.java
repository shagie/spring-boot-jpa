package net.shagie.sb.jpa.model;

import javax.persistence.*;
import java.util.Date;
import java.util.EnumMap;
import java.util.Map;

@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private Date birthday;

    @ElementCollection
    @ManyToMany(fetch = FetchType.EAGER)
    @MapKeyEnumerated(EnumType.STRING)
    private Map<AddressType, Address> addresses;

    public Customer() {
        addresses = new EnumMap<>(AddressType.class);
    }

    public Customer(String firstName, String lastName, Date birthday) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        addresses = new EnumMap<>(AddressType.class);
    }

    public Customer(String firstName, String lastName, Date birthday, Map<AddressType,Address> addresses) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.addresses = addresses;
    }

    public Map<AddressType, Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(Map<AddressType, Address> addresses) {
        this.addresses = addresses;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthday=" + birthday +
                ", addresses=" + addresses +
                '}';
    }
}
