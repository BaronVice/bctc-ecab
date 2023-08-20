package bctc.cabinet.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SecurityConfiguration {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            // cross-site request forgery protection
            .csrf(
                    csrf -> csrf.csrfTokenRepository(new HttpSessionCsrfTokenRepository())
                            // .disable()               !! Kinda not recommended to disable
            )
            // model to customize authorization
            .authorizeHttpRequests(
                    authorize -> authorize
                            // Whitelist (no need to authenticate)
                            .requestMatchers("/api/v1/auth/**")
                            .permitAll()
                            // All others should be authenticated
                            .anyRequest()
                            .authenticated()
            )
            // Authentication state should not be stored -> set policy to stateless
            .sessionManagement(
                    management -> management
                            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )
            // Which authentication provider to use
            .authenticationProvider(
                    authenticationProvider
            )
            // Execute this filter before UsernamePasswordAuthenticationFilter
                // (check things, update SecurityContext and only then get credentials)
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
        ;

        return http.build();
    }
}
