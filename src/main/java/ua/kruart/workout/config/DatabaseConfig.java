package ua.kruart.workout.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

/**
 * Contains configuration for working with databases
 *
 * @author  kruart on 24.07.2017.
 */
@ComponentScan(basePackages = "ua.kruart.workout.repository")
@EnableTransactionManagement
@Configuration
public class DatabaseConfig {

    /**Properties that are required to configure the data source*/
    @Value("${jdbc.driverClassName}")
    private String driverClassName;
    @Value("${jdbc.url}")
    private String url;
    @Value("${jdbc.username}")
    private String name;
    @Value("${jdbc.password}")
    private String password;

    /**True sql scripts will be executed, false won't be*/
    @Value("${database.init}")
    private Boolean isInit;

    /**Resources that contain the path to sql scripts*/
    @Value("classpath:sql/${jdbc.initLocation}")
    private Resource initLocation;
    @Value("classpath:sql/populateDB.sql")
    private Resource dbPopulateSqlScript;

    /**Hibernate properties*/
    @Value("${jpa.showSql}")
    private Boolean showSql;
    @Value("${hibernate.format_sql}")
    private Boolean formatSql;
    @Value("${hibernate.use_sql_comments}")
    private Boolean useSqlComments;

    /**Profile which is used in production*/
    @Bean
    @Profile("postgres")
    public static PropertySourcesPlaceholderConfigurer developmentPropertyPlaceholderConfigurer() {
        PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();
        configurer.setLocation(new ClassPathResource("sql/postgres.properties"));
        configurer.setFileEncoding("utf-8");
        return configurer;
    }

    /**Profile which is used for testing*/
    @Bean
    @Profile("hsqldb")
    public static PropertySourcesPlaceholderConfigurer propertyPlaceholderConfigurer() {
        PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();
        configurer.setLocation(new ClassPathResource("sql/hsqldb.properties"));
        configurer.setFileEncoding("utf-8");
        return configurer;
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(url);
        dataSource.setUsername(name);
        dataSource.setPassword(password);
        return dataSource;
    }

    @Bean
    public DataSourceInitializer dataSourceInitializer(DataSource dataSource) {
        final DataSourceInitializer initializer = new DataSourceInitializer();
        initializer.setEnabled(isInit);
        initializer.setDataSource(dataSource);
        initializer.setDatabasePopulator(getDatabasePopulator());
        return initializer;
    }

    private DatabasePopulator getDatabasePopulator() {
        ResourceDatabasePopulator resourceDatabasePopulator = new ResourceDatabasePopulator();
        resourceDatabasePopulator.setSqlScriptEncoding("UTF-8");
        resourceDatabasePopulator.addScripts(initLocation, dbPopulateSqlScript);
        return resourceDatabasePopulator;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
        emf.setDataSource(dataSource);
        emf.setPackagesToScan("ua.kruart.workout.model");

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setShowSql(showSql);
        emf.setJpaVendorAdapter(vendorAdapter);

        Properties jpaProperties = new Properties();
        jpaProperties.put("hibernate.format_sql", formatSql);
        jpaProperties.put("hibernate.use_sql_comments", useSqlComments);

        emf.setJpaProperties(jpaProperties);

        return emf;
    }

    @Bean
    public JpaTransactionManager transactionManager(EntityManagerFactory emf) {
        return new JpaTransactionManager(emf);
    }
}
