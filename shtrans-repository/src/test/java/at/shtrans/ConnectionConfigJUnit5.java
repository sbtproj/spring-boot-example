package at.shtrans;

import at.shtrans.repository.CustomerRepository;
import com.github.database.rider.core.api.configuration.DBUnit;
import com.github.database.rider.junit5.DBUnitExtension;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.suite.api.Suite;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Suite // replacement fuer @RunWith(JUnitPlatform.class)
@ExtendWith(DBUnitExtension.class)
@DBUnit(url = "jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1", driver = "org.h2.Driver", user = "sa")
public class ConnectionConfigJUnit5 {

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    @DisplayName("Single test successful")
    public void shouldSeedFromDeclaredConnection() {

        assertTrue(true);
        assertNotNull(customerRepository);
    }
}
