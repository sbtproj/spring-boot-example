package at.shtrans.rest.configuration;

import at.shtrans.rest.configuration.matcher.RoleRequestMatcher;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SpringBootBasicAuthSecurityConfiguration {

    @Value("${auth.user}")
    private String authUser;

    @Value("${auth.password}")
    private String authPassword;

    @Bean
    public SecurityFilterChain securedFilterChain(HttpSecurity http) throws Exception {

        // Using a Custom AuthorizationManager
        // https://www.baeldung.com/spring-security-authorizationmanager
        // https://salahuddin-s.medium.com/custom-header-based-authentication-using-spring-security-17f4163d0986

        http.csrf((csrf) -> csrf.disable()); //No CSRF token
        http.formLogin((FormLoginConfigurer<HttpSecurity> formLoginCustomizer) -> formLoginCustomizer.disable()); //No Form Login
        http.logout((LogoutConfigurer<HttpSecurity> logoutCustomizer) -> logoutCustomizer.disable()); //No Logout

        http.securityMatcher("/customer/**")
                .authorizeHttpRequests(authorize
                        -> authorize
                        .requestMatchers(new RoleRequestMatcher("/restful-ws/customer", "ADMIN", "ADVISOR")).authenticated()
                        .requestMatchers(new RoleRequestMatcher("/restful-ws/customer/firstName", "ADMIN", "ADVISOR")).authenticated()
                        .requestMatchers(new RoleRequestMatcher("/restful-ws/customer/lastName", "ADMIN", "ADVISOR")).authenticated()
                        .requestMatchers(new RoleRequestMatcher("/restful-ws/customer/version", "ADMIN", "ADVISOR")).authenticated()
                        .requestMatchers(new RoleRequestMatcher("/restful-ws/customer/create", "ADMIN")).authenticated()
                        .requestMatchers(new RoleRequestMatcher("/restful-ws/customer/update", "ADMIN")).authenticated()
                        .requestMatchers(new RoleRequestMatcher("/restful-ws/customer/delete", "ADMIN")).authenticated()
                )
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        UserDetails user = User
                .withUsername(authUser)
                .password(passwordEncoder().encode(authPassword))
                .build();
        return new InMemoryUserDetailsManager(user);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}



