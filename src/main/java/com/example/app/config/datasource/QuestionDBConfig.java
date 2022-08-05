package com.example.app.config.datasource;

import com.example.app.repositories.QuestionRepository;
import com.zaxxer.hikari.HikariDataSource;
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
        entityManagerFactoryRef = "questionEntityManagerFactory",
        transactionManagerRef = "questionTransactionManager",
        basePackages = "com.example.app.repositories",
        excludeFilters = {
                @ComponentScan.Filter(type = FilterType.ASPECTJ, pattern = "com.example.app.repositories.AnswerRepository"),
                @ComponentScan.Filter(type = FilterType.ASPECTJ, pattern = "com.example.app.repositories.SolutionRepository"),
                @ComponentScan.Filter(type = FilterType.ASPECTJ, pattern = "com.example.app.repositories.ClientRequestRepository")
        }
)
public class QuestionDBConfig {

    @Bean
    @Primary
    @ConfigurationProperties(prefix="spring.question.datasource")
    public DataSourceProperties questionDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean(name="questionDataSource")
    @ConfigurationProperties(prefix="spring.question.datasource")
    public DataSource dataSource() {
        return questionDataSourceProperties()
                .initializeDataSourceBuilder()
                .type(HikariDataSource.class)
                .build();
    }

    @Bean(name = "questionEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean questionEntityManagerFactory(EntityManagerFactoryBuilder builder,
                                                                           @Qualifier("questionDataSource") DataSource dataSource) {
        Map<String, Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto", "create-drop");
        properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        return builder.dataSource(dataSource)
                .properties(properties)
                .packages("com.example.app.models.question")
                .persistenceUnit("Question")
                .build();
    }

    @Bean(name = "questionTransactionManager")
    public PlatformTransactionManager bookTransactionManager(
            @Qualifier("questionEntityManagerFactory") EntityManagerFactory questionEntityManagerFactory) {
        return new JpaTransactionManager(questionEntityManagerFactory);
    }
}
