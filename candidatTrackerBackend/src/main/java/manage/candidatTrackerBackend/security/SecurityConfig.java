package manage.candidatTrackerBackend.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.HttpMethod.GET;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    
    @Autowired private BCryptPasswordEncoder bCryptPassword;
    @Autowired private UserDetailsService userDetailsService;
    @Autowired private JwtFilterService jwtFilterService;
     
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return
            http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(
                        authorize ->
                                authorize
                                    .requestMatchers(POST,"/api/auth/login").permitAll()
                                    .requestMatchers(POST,"/api/auth/register").permitAll()
                                    .requestMatchers(POST,"/api/auth/logout").authenticated()
                                    .requestMatchers(GET,"/api/getall").permitAll()
                                    .requestMatchers(GET,"/api/auth/test").permitAll()
                                    .requestMatchers(POST,"/api/auth/candidate/add").hasAnyRole("ADMIN","USER") 
                                    .anyRequest().authenticated())
                .sessionManagement(httpSecurityManagementConfigurer ->
                    httpSecurityManagementConfigurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtFilterService, UsernamePasswordAuthenticationFilter.class)
                .build();

    }

    @Bean
    public AuthenticationManager authenticationManager (AuthenticationConfiguration authenticationConfiguration) 
        throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public AuthenticationProvider authenticationProvider () {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(bCryptPassword);
        return daoAuthenticationProvider;
    }
}
