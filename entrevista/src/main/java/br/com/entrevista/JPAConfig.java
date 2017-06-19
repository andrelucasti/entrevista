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
import org.springframework.transaction.annotation.EnableTransactionManagement;

import br.com.entrevista.daos.DaoUsuario;

/**
 * 
 * @author Andre
 * 
 *	Classe de configuração do JPA
 */
@Configuration
@EnableJpaRepositories(basePackageClasses = {DaoUsuario.class}) 
@EnableTransactionManagement //Habilita poder usar a anotação @Transactional
public class JPAConfig {
	
	/**
	 * @author Andre
	 * @return
	 * 
	 * Responsável pela configuração de conexão e dialeto do banco que vai ser usado .
	 */
		@Bean
		public DataSource dataSource(){
			
			DriverManagerDataSource dataSource = new DriverManagerDataSource();
			
			dataSource.setDriverClassName("com.mysql.jdbc.Driver");
			dataSource.setUsername("root");
			dataSource.setPassword("");
			dataSource.setUrl("jdbc:mysql://127.0.0.1:3306/entrevista");
			
			return dataSource;
		}
		
		/**
		 * @author Andre
		 * @param dataSource
		 * @return
		 * 
		 * Este bean é representa o  {@link EntityManagerFactory } ou seja, como o spring é um IOC 
		 * ele vai ficar pelo "Begin, e commit" no gerenciamento da minha persistencia de dados e carregamento das minhas entidades JPA.
		 */
		
		@Bean
		public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
		    LocalContainerEntityManagerFactoryBean emfb = new LocalContainerEntityManagerFactoryBean();
		    emfb.setDataSource(dataSource);
		    emfb.setPackagesToScan("br.com.entrevista.models"); 
		    emfb.setJpaVendorAdapter(jpaVendorAdapter());
		    emfb.setJpaProperties(prop());
		    return emfb;
		}
		
		/**
		 * @author Andre
		 * @return
		 * 
		 * Informo que estou usandoa implementação do Hibernate
		 */
		@Bean 
		public JpaVendorAdapter jpaVendorAdapter() {
		    return new HibernateJpaVendorAdapter();
		}
		
		/**
		 * 
		 * @param emf
		 * @return
		 * 
		 * habilitando o gerenciador de transação para o spring
		 */
		@Bean
		public JpaTransactionManager transactionManager(EntityManagerFactory emf) {
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
