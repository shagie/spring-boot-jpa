package net.shagie.sb.jpa.dao;

import java.util.List;

import net.shagie.sb.jpa.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    List<Customer> findByLastName(String lastName);
}
