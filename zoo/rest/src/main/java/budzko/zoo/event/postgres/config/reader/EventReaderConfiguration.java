package budzko.zoo.event.postgres.config.reader;

import budzko.zoo.event.postgres.repo.reader.EventReaderRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Objects;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackageClasses = {EventReaderRepo.class},
        entityManagerFactoryRef = "eventReaderEntityManagerFactory",
        transactionManagerRef = "eventReaderTransactionManager"
)
@RequiredArgsConstructor
public class EventReaderConfiguration {

    @Bean
    public LocalContainerEntityManagerFactoryBean eventReaderEntityManagerFactory(
            @Qualifier("eventReaderDataSource") DataSource dataSource,
            EntityManagerFactoryBuilder builder
    ) {
        return builder
                .dataSource(eventReaderDataSource())
                .packages(EventReaderRepo.class)
                .build();
    }

    @Bean
    @ConfigurationProperties("spring.datasource.event-reader.hikari")
    public DataSource eventReaderDataSource() {
        return eventReaderDataSourceProperties()
                .initializeDataSourceBuilder()
                .build();
    }

    @Bean
    public PlatformTransactionManager eventReaderTransactionManager(
            @Qualifier("eventReaderEntityManagerFactory") LocalContainerEntityManagerFactoryBean eventReaderEntityManagerFactory) {
        return new JpaTransactionManager(Objects.requireNonNull(eventReaderEntityManagerFactory.getObject()));
    }

    @Bean
    @ConfigurationProperties("spring.datasource.event-reader")
    public DataSourceProperties eventReaderDataSourceProperties() {
        return new DataSourceProperties();
    }
}
