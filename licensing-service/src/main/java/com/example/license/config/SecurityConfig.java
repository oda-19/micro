package com.example.license.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http

                .authorizeHttpRequests(
                        auth -> auth.requestMatchers("/static/style/**", "/static/images/**", "/").permitAll()
                                .requestMatchers("/auth").permitAll()
                                .requestMatchers("/types", "/licenses").hasRole("licensing-service-user")
                                .requestMatchers("/types", "/licenses", "/types/*", "/licenses/*").hasRole("licensing-service-admin")
                                .anyRequest().authenticated()
                ).sessionManagement(
                        session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                ).oauth2ResourceServer(
                        resourceServer -> resourceServer.jwt(
                                jwtConfigurer -> jwtConfigurer.jwtAuthenticationConverter(
                                        keycloakAuthConverter()
                                )
                        )
                )
                .build();
    }

    private Converter<Jwt,? extends AbstractAuthenticationToken> keycloakAuthConverter() {
        var converter = new JwtAuthenticationConverter();
        converter.setJwtGrantedAuthoritiesConverter(
                new AuthoritiesConverter()
        );
        return converter;
    }

}

//import lombok.RequiredArgsConstructor;
//import org.keycloak.adapters.springsecurity.authentication.KeycloakAuthenticationEntryPoint;
//import org.keycloak.adapters.springsecurity.authentication.KeycloakLogoutHandler;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.access.AccessDeniedHandler;
//import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
//
//import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;
//
//@Configuration
//@EnableWebSecurity
//@EnableMethodSecurity(jsr250Enabled = true)
//@RequiredArgsConstructor
//public class SecurityConfig {
////    @Bean
////    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
////        return httpSecurity.authorizeHttpRequests(authorize -> authorize
////                        .anyRequest().authenticated())
////                .oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()))
////                .build();
////    }
//
//    private final JwtAuthConverter jwtAuthConverter;
//    private final KeycloakLogoutHandler keycloakLogoutHandler;
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf()
//                .disable()
//                .authorizeHttpRequests(authorize -> authorize
//                        .requestMatchers("/static/style/**", "/static/images/**", "/")
//                        .permitAll()
//                        .requestMatchers("/types/*", "/licenses/*").hasRole("ADMIN")
//                        .requestMatchers("/types", "/licenses").hasRole("USER")
//                        .anyRequest()
//                        .authenticated())
//                .logout(logout -> logout
//                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//                        .logoutSuccessUrl("/")
//                        .permitAll());
//
//        http
//                .oauth2ResourceServer()
//                .jwt()
//                .jwtAuthenticationConverter(jwtAuthConverter);
//
//        http
//                .sessionManagement()
//                .sessionCreationPolicy(STATELESS);
//
//        return http.build();
//    }
////    @Override
////    protected void configure(HttpSecurity http) throws Exception {
////        super.configure(http);
////        http.authorizeRequests()
////                .anyRequest().authenticated();
////    }
////
////    @Autowired
////    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
////        KeycloakAuthenticationProvider keycloakAuthenticationProvider = keycloakAuthenticationProvider();
////        keycloakAuthenticationProvider.setGrantedAuthoritiesMapper(new SimpleAuthorityMapper());
////        auth.authenticationProvider(keycloakAuthenticationProvider);
////    }
////
////    @Bean
////    @Override
////    protected SessionAuthenticationStrategy sessionAuthenticationStrategy() {
////        return new RegisterSessionAuthenticationStrategy(new SessionRegistryImpl());
////    }
////
////    @Bean
////    public KeycloakConfigResolver KeycloakConfigResolver() {
////        return new KeycloakSpringBootConfigResolver();
////    }
////
////    @Override
////    public void init(WebSecurity builder) throws Exception {
////
////    }
////
////    @Override
////    public void configure(WebSecurity builder) throws Exception {
////
////    }
//}
