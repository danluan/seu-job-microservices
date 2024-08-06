package br.com.danluan.auth.config;

import br.com.danluan.auth.feignClients.UserServiceClient;
import br.com.danluan.auth.security.JwtAuthFilter;
import br.com.danluan.auth.security.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserServiceClient userServiceClient;

    @Bean
    public OncePerRequestFilter jwtFilter(){
        return new JwtAuthFilter(jwtService, userServiceClient);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((authz) ->
                        authz
                                .requestMatchers("/api/admin/**")
                                .hasRole("ADMIN")
                                .requestMatchers(HttpMethod.POST, "/api/user")
                                .permitAll()
                                .requestMatchers("/api/user/**")
                                .authenticated()
                                .requestMatchers("/api/worker/**")
                                .hasAnyRole("ADMIN", "USER", "COMPANY", "FREELANCER", "WORKER")
                                .requestMatchers(HttpMethod.POST, "/api/company")
                                .hasAnyRole("ADMIN")
                                .requestMatchers("/api/company/**")
                                .hasAnyRole("ADMIN", "COMPANY", "WORKER")
                                .requestMatchers("/api/freelancer/**")
                                .hasAnyRole("ADMIN", "FREELANCER", "WORKER")
                                .requestMatchers("/api/resume/**")
                                .hasAnyRole("ADMIN", "COMPANY", "FREELANCER", "WORKER")
                                .requestMatchers("/api/job/**")
                                .hasAnyRole("ADMIN", "COMPANY", "WORKER")
                                .requestMatchers("/api/service/**")
                                .hasAnyRole("ADMIN", "FREELANCER", "WORKER")
                                .requestMatchers("/api/application/**")
                                .hasAnyRole("ADMIN","WORKER", "FREELANCER", "COMPANY")
                                .anyRequest()
                                .permitAll()

                )

                .sessionManagement((session) ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                .addFilterBefore(
                        jwtFilter(),
                        UsernamePasswordAuthenticationFilter.class)


                .csrf(AbstractHttpConfigurer::disable);

        return http.build();

    }
}