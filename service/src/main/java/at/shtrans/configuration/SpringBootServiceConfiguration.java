package at.shtrans.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;


@Configuration
@ComponentScan("at.shtrans.service")
@Import({ SpringBootRepositoryConfiguration.class})
public class SpringBootServiceConfiguration {

}
