package at.shtrans;


import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@SuppressWarnings("squid:S2187")
@EnableTransactionManagement
@ComponentScan("at.shtrans.repository") // add wegen intellij idea
@EnableJpaRepositories(basePackages = "at.shtrans.repository")
@PropertySource("persistence_test.properties")
public class JpaTransactionManagerTestConfiguration {

    @Autowired
    private Environment env;

    @Bean("testDataSource")
    public DataSource dataSource() {
        final DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty("jdbc.driverClassName"));
        dataSource.setUrl(env.getProperty("jdbc.url"));
        dataSource.setUsername(env.getProperty("jdbc.user"));
        dataSource.setPassword(env.getProperty("jdbc.pass"));

        return dataSource;
    }


    @Bean("entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource testDataSource) {
        final LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(testDataSource);
        em.setPackagesToScan(new String[]{"at.shtrans.domain"});


        // HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
        //     jpaVendorAdapter.setGenerateDdl(true);
        // jpaVendorAdapter.setDatabasePlatform("org.hibernate.dialect.H2Dialect");

        em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        em.setJpaProperties(additionalProperties());

        return em;
    }

    @Bean
    @ConditionalOnMissingBean(type = "JpaTransactionManager")
    JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);

        return transactionManager;
    }

    final Properties additionalProperties() {
        final Properties hibernateProperties = new Properties();
        hibernateProperties.setProperty("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
        hibernateProperties.setProperty("hibernate.dialect", env.getProperty("hibernate.dialect"));
        hibernateProperties.setProperty("hibernate.format_sql", env.getProperty("hibernate.format_sql"));
        hibernateProperties.setProperty("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
        hibernateProperties.setProperty("hibernate.cache.use_second_level_cache", env.getProperty("hibernate.cache.use_second_level_cache"));
        hibernateProperties.setProperty("hibernate.cache.use_query_cache", env.getProperty("hibernate.cache.use_query_cache"));

        return hibernateProperties;
    }

}
