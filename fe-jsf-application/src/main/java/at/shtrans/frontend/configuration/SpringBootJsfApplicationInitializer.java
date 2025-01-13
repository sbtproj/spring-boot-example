package at.shtrans.frontend.configuration;

import jakarta.faces.webapp.FacesServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@SpringBootApplication
@ComponentScan(basePackages = {"at.shtrans.frontend.beans"})
@Import({SpringBootRestClientConfiguration.class})
@PropertySources({
        @PropertySource("classpath:application.properties"),
        @PropertySource("classpath:client-application.properties")
})
public class SpringBootJsfApplicationInitializer extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootJsfApplicationInitializer.class, args);
    }

    @Bean
    public ServletRegistrationBean servletRegistrationBean() {
        FacesServlet servlet = new FacesServlet();
        ServletRegistrationBean servletRegistrationBean =
                new ServletRegistrationBean(servlet, "*.xhtml");

        return servletRegistrationBean;
    }

}