package at.shtrans.configuration;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan("at.shtrans.domain")
@EnableJpaRepositories("at.shtrans.repository")
@PropertySource("classpath:application-h2.properties")
public class SpringBootRepositoryConfiguration {

}

