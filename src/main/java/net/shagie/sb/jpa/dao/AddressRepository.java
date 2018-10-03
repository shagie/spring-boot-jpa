package net.shagie.sb.jpa.dao;

import net.shagie.sb.jpa.model.Address;
import org.springframework.data.repository.CrudRepository;

public interface AddressRepository extends CrudRepository<Address, Long> {
}
