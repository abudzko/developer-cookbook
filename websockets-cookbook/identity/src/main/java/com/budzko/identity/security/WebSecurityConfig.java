package com.budzko.identity.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class WebSecurityConfig {
    private final UserDetailsService userDetailsService;

    @Bean
    public AuthJwtFilter authJwtFilter() {
        return new AuthJwtFilter();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .cors()
                .and().csrf().disable()
                .exceptionHandling()//.authenticationEntryPoint(accessDeniedHandler())
//                .and().sessionManagement()
//                // .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .requestMatchers("/signin").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/signin")
//                .and().authorizeHttpRequests()
//                .requestMatchers("/login").permitAll()
//                .and()
//                .authorizeHttpRequests()
//                .requestMatchers("/signIn", "/signOut").authenticated()
//                .requestMatchers("/signUp").permitAll()
//                .anyRequest().authenticated()


//                .and().httpBasic();
//                .and().formLogin().loginProcessingUrl("/login").permitAll()
        ;

        http.authenticationProvider(authenticationProvider());

//        http.addFilterBefore(authJwtFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    private AuthenticationEntryPoint accessDeniedHandler() {
        return (request, response, authException) -> {
            log.info("Access denied", authException);
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
        };
    }
}
