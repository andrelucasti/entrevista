package br.com.entrevista;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import br.com.entrevista.daos.DaoUsuario;

@Configuration
@EnableJpaRepositories(basePackageClasses  = {DaoUsuario.class})
public class JPAConfig {
	
	
		@Bean
		public DataSource dataSource(){
			
			DriverManagerDataSource dataSource = new DriverManagerDataSource();
			
			dataSource.setDriverClassName("com.mysql.jdbc.Driver");
			dataSource.setUsername("root");
			dataSource.setPassword("");
			dataSource.setUrl("jdbc:mysql://127.0.0.1:3306/entrevista");
			
			return dataSource;
		}
		
		@Bean
		public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
		    LocalContainerEntityManagerFactoryBean emfb = new LocalContainerEntityManagerFactoryBean();
		    emfb.setDataSource(dataSource);
		    emfb.setPackagesToScan("br.com.entrevista.models"); 
		    emfb.setJpaVendorAdapter(jpaVendorAdapter());
		    emfb.setJpaProperties(prop());
		    return emfb;
		}
		
		
		@Bean 
		public JpaVendorAdapter jpaVendorAdapter() {
		    return new HibernateJpaVendorAdapter();
		}
		
		@Bean
		public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
		    JpaTransactionManager transactionManager = new JpaTransactionManager();
		    transactionManager.setEntityManagerFactory(emf);
		    return transactionManager;
		}
	
		
		public Properties prop(){
			Properties properties = new Properties();
			
			properties.setProperty("hibernate.hbm2ddl.auto", "update");
			properties.setProperty("hibernate.show_sql", "true");
			
			return properties;
		}

}
