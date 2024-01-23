package at.shtrans;

import at.shtrans.repository.CustomerRepository;
import com.github.database.rider.core.api.dataset.DataSet;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ConnectionConfigJUnit5 extends DataJpaBasicConfigurationTest {

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    @DisplayName("Single test successful")
    @DataSet(value = "datasets/customer/empty.yml")
    public void shouldSeedFromDeclaredConnection() {

        assertNotNull(customerRepository.count());
        assertEquals(0, customerRepository.count());
    }
}
