package net.shagie.sb.jpa;

import net.shagie.sb.jpa.dao.AddressRepository;
import net.shagie.sb.jpa.dao.CustomerRepository;
import net.shagie.sb.jpa.model.Address;
import net.shagie.sb.jpa.model.AddressType;
import net.shagie.sb.jpa.model.Customer;
import net.shagie.sb.jpa.model.State;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.EnumMap;
import java.util.Map;

@SpringBootApplication
public class Application {

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }

    @Bean
    public CommandLineRunner demo(CustomerRepository custRepo, AddressRepository addrRepo) {
        return (args) -> {
            // save a couple of customers
            Address work = new Address("100 Main", "Therevill", State.WASHINGTON, "12345");
            Address res = new Address("205 Oak", "Therevill", State.WASHINGTON, "123456");

            addrRepo.save(work);
            addrRepo.save(res);
            Map<AddressType, Address> map = new EnumMap<>(AddressType.class);
            map.put(AddressType.COMMERICAL, work);
            map.put(AddressType.RESIDENTIAL, res);

            custRepo.save(new Customer("Jack", "Bauer", map));
            custRepo.save(new Customer("Chloe", "O'Brian"));
            custRepo.save(new Customer("Kim", "Bauer"));
            custRepo.save(new Customer("David", "Palmer"));
            custRepo.save(new Customer("Michelle", "Dessler"));
            custRepo.save(new Customer("Kim", "Smith"));
            custRepo.save(new Customer("David", "Smith"));

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
            custRepo.findByLastName("Bauer").forEach(bauer -> {
                log.info(bauer.toString());
            });
            // for (Customer bauer : repository.findByLastName("Bauer")) {
            // 	log.info(bauer.toString());
            // }
            log.info("");
        };
    }

}
