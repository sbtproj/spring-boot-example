package at.shtrans.springboot;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@EnableJpaRepositories("at.shtrans.repository.*")
@SpringBootApplication(scanBasePackages = {"at.shtrans.service.*", "at.shtrans.repository.*", "at.shtrans.rest.controller.*"})
public class SpringBootWebServiceApplication extends SpringBootServletInitializer {

    private static Logger LOG = LoggerFactory.getLogger(SpringBootWebServiceApplication.class);

    private static Class<SpringBootWebServiceApplication> applicationClass = SpringBootWebServiceApplication.class;

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
