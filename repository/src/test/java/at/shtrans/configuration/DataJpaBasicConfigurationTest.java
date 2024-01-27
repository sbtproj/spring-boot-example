package at.shtrans.configuration;


import com.github.database.rider.junit5.api.DBRider;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;

@SuppressWarnings("squid:S2187")
@DBRider
@SpringBootTest
@TestExecutionListeners(
        value = {DependencyInjectionTestExecutionListener.class, DirtiesContextTestExecutionListener.class})
@ContextConfiguration(classes = {JpaTransactionManagerTestConfiguration.class}, loader = AnnotationConfigContextLoader.class)
public class DataJpaBasicConfigurationTest {

}
