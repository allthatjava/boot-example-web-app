package brian.example.boot.web.app.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;
import java.util.Properties;

@Slf4j
@Configuration
@Profile({"local","default"})
public class DBConfig {

	@Autowired
	private Environment env;

//--- Local Database - H2 --
	@Bean
	public DataSource devDataSource() {
		final DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(env.getProperty("h2.datasource.driver-class-name"));
		dataSource.setUrl(env.getProperty("h2.datasource.url"));
		dataSource.setUsername(env.getProperty("h2.datasource.username"));
		dataSource.setPassword(env.getProperty("h2.datasource.password"));

		return dataSource;
	}

	@Bean("entityManagerFactory")
	public LocalContainerEntityManagerFactoryBean entityManagerFactoryDev() {
		final LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(devDataSource());
		em.setPackagesToScan("brian.example.boot.web.app.domain");

		final Properties prop = new Properties();
		prop.setProperty("hibernate.dialect", env.getProperty("h2.jpa.properties.hibernate.dialect"));
		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();

		em.setJpaProperties(prop);
		em.setJpaVendorAdapter(vendorAdapter);

		return em;
	}
}
