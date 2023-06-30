package com.example.graduationprojectbe.config;


import com.example.graduationprojectbe.exception.CustomAccessDeniedHandler;
import com.example.graduationprojectbe.exception.CustomAuthenticationEntryPoint;
import com.example.graduationprojectbe.sercurity.CustomFilter;
import com.example.graduationprojectbe.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(
        securedEnabled = true,
        jsr250Enabled = true
)
public class SecurityConfig {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private CustomFilter customFilter;

    @Autowired
    private CustomAuthenticationEntryPoint customAuthenticationEntryPoint;

    @Autowired
    private CustomAccessDeniedHandler customAccessDeniedHandler;


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(customUserDetailsService);
        return provider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain (HttpSecurity http) throws Exception {

        String [] PUBLIC = {
               "/visitor/**",
                "public/api/**",
                "/employee/api/v1/**",
                "/excel/api/v1/**"
        };

        String [] NHANVIENLETAN = {
                "/receptionist/api/v1/**",
                "/receptionist/api/v2/**"
        };
        String [] NHANVIENSUACHUA = {
                "/engineer/api/v1/**",
                "/engineer/api/v2/**"
        };
        String [] NHANVIENKHO = {
                "/warehouse-employee/api/v1/**",
                "/warehouse-employee/api/v2/**"
        };
        String [] NHANVIENBAOHANH = {
                "/warranty-employee/api/v1/**",

        };
        String [] ADMIN = {
                "/admin/api/v1/**",
                "/admin/api/v2/**"
        };




        http
                .cors()
                .and()
                .csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers(PUBLIC).permitAll()
                .requestMatchers(NHANVIENLETAN).hasRole("NHANVIENLETAN")
                .requestMatchers(NHANVIENSUACHUA).hasRole("NHANVIENSUACHUA")
                .requestMatchers(NHANVIENBAOHANH).hasRole("NHANVIENBAOHANH")
                .requestMatchers(NHANVIENKHO).hasRole("NHANVIENKHO")
                .requestMatchers(ADMIN).hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(customAuthenticationEntryPoint)
                .accessDeniedHandler(customAccessDeniedHandler)
                .and()
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(customFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

}
