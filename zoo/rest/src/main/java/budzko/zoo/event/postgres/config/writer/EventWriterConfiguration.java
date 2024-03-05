package budzko.zoo.event.postgres.config.writer;

import budzko.zoo.action.repo.UserActionRepo;
import budzko.zoo.event.postgres.repo.writer.EventWriterRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
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
        basePackageClasses = {EventWriterRepo.class, UserActionRepo.class},
        entityManagerFactoryRef = "eventWriterEntityManagerFactory",
        transactionManagerRef = "eventWriterTransactionManager"
)
@RequiredArgsConstructor
/**
 * https://www.baeldung.com/spring-boot-configure-multiple-datasources
 */
public class EventWriterConfiguration {

    @Bean
    @Primary
    public LocalContainerEntityManagerFactoryBean eventWriterEntityManagerFactory(
            @Qualifier("eventWriterDataSource") DataSource dataSource,
            EntityManagerFactoryBuilder builder
    ) {
        return builder
                .dataSource(eventWriterDataSource())
                .packages(EventWriterRepo.class, UserActionRepo.class)
                .build();
    }

    @Bean
    @ConfigurationProperties("spring.datasource.event-writer.hikari")
    @Primary
    public DataSource eventWriterDataSource() {
        return eventWriterDataSourceProperties()
                .initializeDataSourceBuilder()
                .build();
    }

    @Bean
    @Primary
    public PlatformTransactionManager eventWriterTransactionManager(
            @Qualifier("eventWriterEntityManagerFactory") LocalContainerEntityManagerFactoryBean eventWriterEntityManagerFactory) {
        return new JpaTransactionManager(Objects.requireNonNull(eventWriterEntityManagerFactory.getObject()));
    }

    @Bean
    @ConfigurationProperties("spring.datasource.event-writer")
    public DataSourceProperties eventWriterDataSourceProperties() {
        return new DataSourceProperties();
    }
}
