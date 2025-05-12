package org.lessons.java.games.gamer_hub.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // gestione permessi singole pagine
    @Bean
    @SuppressWarnings("removal")
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .requestMatchers("/games/create", "/games/edit/**").hasAuthority("ADMIN")
                .requestMatchers(HttpMethod.POST, "/games/**").hasAuthority("ADMIN")

                // TODO: aggiungi le altre pagine in seguito, o scleri come l'ultima volta (vuoi aggiungere anche piattaforme?)
                .requestMatchers("/tags", "/tags/**").hasAuthority("ADMIN")
                .requestMatchers("/sales", "/sales/**").hasAuthority("ADMIN")
                .requestMatchers("/games", "/games/**").hasAnyAuthority("USER", "ADMIN")
                .requestMatchers("/**").permitAll()
                .and().formLogin()
                .and().logout()
                .and().exceptionHandling()
                .and().csrf().disable();

        return http.build();
    }

    // gestione user details (provider)
    @Bean
    @SuppressWarnings("deprecation")
    DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        // gestione servizio recupero users via username
        authProvider.setUserDetailsService(userDetailService());

        // gestione password encoder
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

    @Bean
    DatabaseUserDetailService userDetailService() {
        return new DatabaseUserDetailService();
    }

    // gestione password encoder
    @Bean
    PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
