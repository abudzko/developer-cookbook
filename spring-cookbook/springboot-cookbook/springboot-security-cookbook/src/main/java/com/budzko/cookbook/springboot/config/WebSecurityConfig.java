package com.budzko.cookbook.springboot.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;
import java.util.List;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class WebSecurityConfig {
    @Value("${db.schema:}")
    private String dbSchema;

    @Autowired
    private DataSource dataSource;

//    @Bean
//    public UserDetailsService userDetailsService() {
//        var userDetailsService = new InMemoryUserDetailsManager();
//        var user = User.withUsername("user")
//                .password("password")
//                .authorities("read")
//                .build();
//        userDetailsService.createUser(user);
//
//        return userDetailsService;
//    }

    @Bean
    public UserDetailsService userDetailsService() {
        String schema = StringUtils.hasText(dbSchema) ? dbSchema + "." : "";
        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
        jdbcUserDetailsManager.setUsersByUsernameQuery(
                "select username, password, enabled from %susers where username = ?".formatted(schema));
        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery(
                "select username,authority from %sauthorities where username = ?".formatted(schema));
        return jdbcUserDetailsManager;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(
                        (auth) -> auth
                                .requestMatchers(HttpMethod.GET, "/command/info").authenticated()
                                .requestMatchers(HttpMethod.GET, "/command/noop").anonymous()
                                .requestMatchers(HttpMethod.POST, "/command/start").hasAnyAuthority("read")
                                .requestMatchers(HttpMethod.POST, "/command/stop").hasAnyAuthority("read")
                                .requestMatchers("/actuator/**").permitAll()
//                                .anyRequest().permitAll()
                                .anyRequest().authenticated()

                )
                .csrf(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable)
                .authenticationManager(
                        new ProviderManager(List.of(daoAuthenticationProvider()))
                )
                .httpBasic(withDefaults());
//                .oauth2ResourceServer(oauth -> {
//
//                });
        return http.build();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService());
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }
}
