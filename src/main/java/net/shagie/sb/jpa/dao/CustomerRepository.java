package net.shagie.sb.jpa.dao;

import java.util.List;

import net.shagie.sb.jpa.model.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

    List<Customer> findByLastName(String lastName);
}
