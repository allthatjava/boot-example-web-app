package brian.boot.template.web.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
public class DBConfig {

    @Autowired
    private Environment env;

//    @ConfigurationProperties(prefix="spring.datasource")
    private DataSource dataSource() {
        final DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName( env.getProperty("spring.datasource.driver-class-name") );
        dataSource.setUrl( env.getProperty("spring.datasource.url") );
        dataSource.setUsername( env.getProperty("spring.datasource.username") );
        dataSource.setPassword( env.getProperty("spring.datasource.password") );

        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory()
    {
        final LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource());
        em.setPackagesToScan("brian.boot.template.web.app.domain");

        final Properties prop = new Properties();
        prop.setProperty("hibernate.dialect", env.getProperty("spring.jpa.properties.hibernate.dialect"));
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();

        em.setJpaProperties(prop);
        em.setJpaVendorAdapter(vendorAdapter);

        return em;
    }

}
