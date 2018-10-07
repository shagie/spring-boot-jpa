package net.shagie.sb.jpa.config;

import net.shagie.sb.jpa.dao.AddressRepository;
import net.shagie.sb.jpa.dao.CustomerRepository;
import net.shagie.sb.jpa.dao.SearchService;
import net.shagie.sb.jpa.model.Address;
import net.shagie.sb.jpa.model.AddressType;
import net.shagie.sb.jpa.model.Customer;
import net.shagie.sb.jpa.model.State;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.Date;
import java.util.EnumMap;
import java.util.Map;

@Configuration
@Profile("cmdline")
public class CmdlineConfig {
    private static final Logger log = LoggerFactory.getLogger(CmdlineConfig.class);

    @Bean
    public CommandLineRunner cmdline(CustomerRepository custRepo, AddressRepository addrRepo, SearchService search) {
        return (args) -> {
            // save a couple of customers
            Address work = new Address("100 Main", "Therevill", State.WASHINGTON, "12345");
            Address res = new Address("205 Oak", "Therevill", State.WASHINGTON, "123456");

            addrRepo.save(work);
            addrRepo.save(res);
            Map<AddressType, Address> map = new EnumMap<>(AddressType.class);
            map.put(AddressType.COMMERICAL, work);
            map.put(AddressType.RESIDENTIAL, res);

            custRepo.save(new Customer("Jack", "Bauer", new Date(0), map));
            custRepo.save(new Customer("Chloe", "O'Brian", new Date(946684800000L)));
            custRepo.save(new Customer("Kim", "Bauer", new Date(315532800000L)));
            custRepo.save(new Customer("David", "Palmer", new Date(339984000000L)));
            custRepo.save(new Customer("Michelle", "Dessler", new Date(339984000000L)));
            custRepo.save(new Customer("Kim", "Smith", new Date(315532800000L)));
            custRepo.save(new Customer("David", "Smith", new Date(0)));

            // fetch all customers
            log.info("Customers found with findAll():");
            log.info("-------------------------------");
            for (Customer customer : custRepo.findAll()) {
                log.info(customer.toString());
            }
            log.info("");

            // fetch an individual customer by ID
            custRepo.findById(1L)
                    .ifPresent(customer -> {
                        log.info("Customer found with findById(1L):");
                        log.info("--------------------------------");
                        log.info(customer.toString());
                        log.info("");
                    });

            // fetch customers by last name
            log.info("Customer found with findByLastName('Bauer'):");
            log.info("--------------------------------------------");
            custRepo.findByLastName("Bauer").forEach(bauer -> log.info(bauer.toString()));
            log.info("");

            log.info("Customer found with query by example - last name ('smith'):");
            Customer example = new Customer();
            example.setLastName("Smith");
            search.findCustomer(example).forEach(smith -> log.info(smith.toString()));
            log.info("");
        };
    }

}
