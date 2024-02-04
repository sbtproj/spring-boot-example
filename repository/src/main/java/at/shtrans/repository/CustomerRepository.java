package at.shtrans.repository;

import at.shtrans.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository
        extends JpaRepository<Customer, Long>, JpaSpecificationExecutor<Customer> {

    List<Customer> findByFirstName(String firstName);

    List<Customer> findByLastName(String lastName);

    List<Customer> findByVersion(Integer version);
}
