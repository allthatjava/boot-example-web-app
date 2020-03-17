package brian.example.boot.web.app.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.config.java.AbstractCloudConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;

@Slf4j
@Configuration
@Profile({"development","qa", "production"})
public class DBConfigCloud extends AbstractCloudConfig {

	@Autowired
	private Environment env;

//--- Cloud Database - PostgreSQL --
	/**
     * For PCF cloud database
	 *
	 * @return
     */
	@Bean
	public DataSource dataSource() {
		return connectionFactory().dataSource();
	}

//	@Bean("entityManagerFactory")
//	@Profile({"development","qa", "production"})
//	public LocalContainerEntityManagerFactoryBean entityManagerFactoryCloud() {
//		final LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
//		em.setDataSource(dataSource());
//		em.setPackagesToScan("brian.example.boot.web.app.domain");
//
//		final Properties prop = new Properties();
//		prop.setProperty("hibernate.dialect", env.getProperty("postgres.jpa.properties.hibernate.dialect"));
//		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
//
//		em.setJpaProperties(prop);
//		em.setJpaVendorAdapter(vendorAdapter);
//
//		return em;
//	}
}
