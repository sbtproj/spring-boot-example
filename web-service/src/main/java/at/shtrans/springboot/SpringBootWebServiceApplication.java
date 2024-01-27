package at.shtrans.springboot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class SpringBootWebServiceApplication extends SpringBootServletInitializer {

    private static Logger LOG = LoggerFactory.getLogger(SpringBootWebServiceApplication.class);
    private static Class<SpringBootWebServiceApplication> applicationClass = SpringBootWebServiceApplication.class;

    public static void main(String[] args) {
        LOG.info("STARTING THE APPLICATION");
        SpringApplication.run(applicationClass, args);
        LOG.info("APPLICATION FINISHED");
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(applicationClass);
    }

}
