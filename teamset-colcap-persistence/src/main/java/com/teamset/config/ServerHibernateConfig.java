package com.teamset.config;

import java.util.List;
import java.util.Properties;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@PropertySource(value = { "classpath:persistence.hibernate.properties" })
@EnableTransactionManagement
@Profile("server")
public class ServerHibernateConfig {

	@Inject
	protected Environment env;

	@Value("#{'com.teamset.domain.colcap.entity'.split(',')}")
	protected List<String> packageList;

	@Bean
	public static PropertySourcesPlaceholderConfigurer placeHolderConfigurer() {
		PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer = new PropertySourcesPlaceholderConfigurer();
		propertySourcesPlaceholderConfigurer.setIgnoreUnresolvablePlaceholders(true);
		return propertySourcesPlaceholderConfigurer;
	}

	@Bean
	public LocalSessionFactoryBean sessionFactory() {
		final LocalSessionFactoryBean ret = new LocalSessionFactoryBean();
		ret.setHibernateProperties(hiberateProperties());
		ret.setPackagesToScan(packageList.toArray(new String[0]));
		ret.setDataSource(dataSource());
		return ret;
	}

	@Bean
	public DataSource dataSource() {
		final JndiDataSourceLookup dsLookup = new JndiDataSourceLookup();
		dsLookup.setResourceRef(true);
		DataSource dataSource = dsLookup.getDataSource(env.getProperty("com.rb.weblogic.dataSource"));
		return dataSource;
	}

	@Bean
	@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
	public HibernateTransactionManager transactionManager(final LocalSessionFactoryBean localSessionFactoryBean) {
		final HibernateTransactionManager ret = new HibernateTransactionManager();
		ret.setSessionFactory(localSessionFactoryBean.getObject());
		return ret;
	}

	protected Properties hiberateProperties() {

		return new Properties() {
			private static final long serialVersionUID = -7897589834575173212L;

			{
				setProperty("hibernate.c3p0.acquire_increment", env.getProperty("com.rb.persistence.hibernate.connection.hibernate.cp30.acquire_increment"));
				setProperty("hibernate.c3p0.idle_test_period", env.getProperty("com.rb.persistence.hibernate.connection.hibernate.cp30.idle_test_period"));
				setProperty("hibernate.c3p0.max_size", env.getProperty("com.rb.persistence.hibernate.connection.hibernate.cp30.max_size"));
				setProperty("hibernate.c3p0.max_statements", env.getProperty("com.rb.persistence.hibernate.connection.hibernate.cp30.max_statements"));
				setProperty("hibernate.c3p0.min_size", env.getProperty("com.rb.persistence.hibernate.connection.hibernate.cp30.min_size"));
				setProperty("hibernate.c3p0.timeout", env.getProperty("com.rb.persistence.hibernate.connection.hibernate.cp30.timeout"));
				setProperty("hibernate.dialect", env.getProperty("com.rb.persistence.hibernate.dialect"));
				setProperty("hibernate.hbm2ddl.auto", env.getProperty("com.rb.persistence.hibernate.hbm2ddl.auto"));
				setProperty("hibernate.show_sql", env.getProperty("com.rb.persistence.hibernate.show_sql"));
				setProperty("hibernate.format_sql", env.getProperty("com.rb.persistence.hibernate.format_sql"));
			}
		};
	}
}
