package net.shagie.sb.jpa.dao;

import net.shagie.sb.jpa.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
