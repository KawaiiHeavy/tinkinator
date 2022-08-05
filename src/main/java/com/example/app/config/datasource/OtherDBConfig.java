package com.example.app.config.datasource;

import com.example.app.repositories.AnswerRepository;
import com.example.app.repositories.ClientRequestRepository;
import com.example.app.repositories.SolutionRepository;
import com.zaxxer.hikari.HikariDataSource;
import org.hibernate.cfg.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.*;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "otherEntityManagerFactory",
        transactionManagerRef = "otherTransactionManager",
        basePackages = "com.example.app.repositories",
        excludeFilters = {
                @ComponentScan.Filter(type = FilterType.ASPECTJ, pattern = "com.example.app.repositories.QuestionRepository")
        }
)
public class OtherDBConfig {

    @Bean
    @Primary
    @ConfigurationProperties(prefix="spring.other.datasource")
    public DataSourceProperties otherDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Primary
    @Bean(name="otherDataSource")
    public DataSource dataSource() {
        return otherDataSourceProperties()
                .initializeDataSourceBuilder()
                .type(HikariDataSource.class)
                .build();
    }

    @Primary
    @Bean(name = "otherEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean otherEntityManagerFactory(EntityManagerFactoryBuilder builder,
                                                                           @Qualifier("otherDataSource") DataSource dataSource) {
        Map<String, Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto", "create-drop");
        properties.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        return builder.dataSource(dataSource)
                .properties(properties)
                .packages("com.example.app.models.other")
                .persistenceUnit("Question")
                .build();
    }

    @Primary
    @Bean(name = "otherTransactionManager")
    public PlatformTransactionManager otherTransactionManager(
            @Qualifier("otherEntityManagerFactory") EntityManagerFactory questionEntityManagerFactory) {
        return new JpaTransactionManager(questionEntityManagerFactory);
    }
}