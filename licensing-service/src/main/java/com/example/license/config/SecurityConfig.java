package com.example.license.config;

//import lombok.RequiredArgsConstructor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.web.SecurityFilterChain;
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
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf()
//                .disable()
//                .authorizeHttpRequests(authorize -> authorize
//                        .requestMatchers("/static/style/**", "/static/images/**", "/")
//                        .permitAll()
//                        .anyRequest()
//                        .authenticated());
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
