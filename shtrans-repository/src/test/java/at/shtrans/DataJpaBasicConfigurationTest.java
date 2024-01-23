package at.shtrans;


import com.github.database.rider.junit5.api.DBRider;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;

@SuppressWarnings("squid:S2187")
@SpringJUnitConfig
@SpringBootTest
@DBRider(dataSourceBeanName = "testDataSource")
@TestExecutionListeners(
        value = {
                DependencyInjectionTestExecutionListener.class,
                DirtiesContextTestExecutionListener.class
        }
)
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { JpaTransactionManagerTestConfiguration.class} , loader = AnnotationConfigContextLoader.class)
@DirtiesContext
public class DataJpaBasicConfigurationTest {

}
