package at.shtrans.frontend.configuration;

import at.shtrans.frontend.api.service.CustomerApiService;
import at.shtrans.frontend.controller.CustomerController;
import at.shtrans.frontend.controller.JsfController;
import jakarta.faces.webapp.FacesServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@ComponentScan(basePackageClasses = { JsfController.class, CustomerController.class, CustomerApiService.class })
@Import({SpringBootRestClientConfiguration.class})
public class JsfApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(JsfApplication.class, args);
    }

    @Bean
    public ServletRegistrationBean servletRegistrationBean() {
        FacesServlet servlet = new FacesServlet();
        ServletRegistrationBean servletRegistrationBean =
                new ServletRegistrationBean(servlet, "*.xhtml");
        return servletRegistrationBean;
    }

}