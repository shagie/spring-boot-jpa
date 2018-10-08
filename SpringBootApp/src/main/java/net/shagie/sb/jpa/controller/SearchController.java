package net.shagie.sb.jpa.controller;

import net.shagie.sb.jpa.dao.AddressRepository;
import net.shagie.sb.jpa.dao.CustomerRepository;
import net.shagie.sb.jpa.dao.SearchService;
import net.shagie.sb.jpa.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SearchController {

    private final CustomerRepository custRepo;
    private final AddressRepository addrRepo;
    private final SearchService search;

    @Autowired
    public SearchController(CustomerRepository custRepo, AddressRepository addrRepo, SearchService search) {
        this.custRepo = custRepo;
        this.addrRepo = addrRepo;
        this.search = search;
    }

    @RequestMapping("/cust")
    public List<Customer> custLookup(
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String lastName) {
        return search.findCustomer(new Customer(firstName, lastName, null));
    }
}
