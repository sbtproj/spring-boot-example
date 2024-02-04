package at.shtrans.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;


@Configuration
@ComponentScan(basePackages = {"at.shtrans.service", "at.shtrans.repository"})
@Import({ SpringBootRepositoryConfiguration.class})
public class SpringBootServiceConfiguration {

}
