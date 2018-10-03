package net.shagie.sb.jpa.dao;

import net.shagie.sb.jpa.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SearchService {
    @Autowired
    CustomerRepository custRepo;

    public List<Customer> findCustomer(Customer cust) {
        Example<Customer> example = Example.of(cust);
        return custRepo.findAll(example);
    }
}
