package bctc.cabinet.config;

import bctc.cabinet.services.StudentService;
import bctc.cabinet.services.TeacherService;
import bctc.cabinet.util.ServiceError;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {
    private final StudentService studentService;
    private final TeacherService teacherService;

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    // TODO: handle repo
    @Bean
    public UserDetailsService userDetailsService(){
        return username -> studentService.findByEmail(username, ServiceError.USER_NOT_FOUND);
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        // DAO to fetch UserDetails, encode password, etc.
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        // Which UserDetailsService to use
        authenticationProvider.setUserDetailsService(userDetailsService());
        // Which password encoder should be used
        authenticationProvider.setPasswordEncoder(passwordEncoder());

        return authenticationProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
