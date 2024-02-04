package at.shtrans.rest.configuration;

import at.shtrans.configuration.SpringBootServiceConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@SpringBootApplication(scanBasePackages = {"at.shtrans.rest.controller"})
@Import({SpringBootServiceConfiguration.class, SpringBootSwaggerConfiguration.class})
public class SpringBootApplicationInitializer extends SpringBootServletInitializer {

    private static final Logger LOG = LoggerFactory.getLogger(SpringBootApplicationInitializer.class);

    private static final Class<SpringBootApplicationInitializer> applicationClass = SpringBootApplicationInitializer.class;

    public static void main(String[] args) {
        LOG.info("STARTING THE APPLICATION");
        SpringApplication.run(applicationClass, args);
        LOG.info("APPLICATION FINISHED");
    }

    //If you need a traditional war deployment we need to extend the SpringBootServletInitializer
    //This helps us deploy war files to Jboss containers
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder springApplicationBuilder) {
        return springApplicationBuilder.sources(applicationClass);
    }
}
