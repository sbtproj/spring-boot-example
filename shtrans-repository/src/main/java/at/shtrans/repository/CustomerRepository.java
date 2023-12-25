package at.shtrans.repository;

import at.shtrans.domain.Customer;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository
        extends PagingAndSortingRepository<Customer, Long>, JpaSpecificationExecutor<Customer> {

    
}
