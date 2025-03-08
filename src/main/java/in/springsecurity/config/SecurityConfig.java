package in.springsecurity.config;

import in.springsecurity.repository.UserRepository;
import in.springsecurity.service.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.AuthorizationFilter;

@Configuration
public class SecurityConfig {

    private JwtFilter jwtFilter;
    private UserRepository userRepository;// changed not in SIR wala

    public SecurityConfig(JwtFilter jwtFilter, UserRepository userRepository) {
        this.jwtFilter = jwtFilter;
        this.userRepository = userRepository;
    }


    @Bean                                                           // changed not in sir wala
    public CustomUserDetailsService userDetailsService(){
        return new CustomUserDetailsService(userRepository);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {



        http.csrf(csrf -> csrf.disable()).
                   cors(cors-> cors.disable());

        http.authorizeHttpRequests().anyRequest().permitAll();
//                .requestMatchers(
//                        "/api/v1/auth/user-signup",
//                                  "/api/v1/auth/userSignIn",
//                                  "/api/v1/auth/content-manager-signup",
//                                  "/api/v1/auth/login-otp").permitAll()
//                                     .requestMatchers("/api/v1/cars/Add-car").hasAuthority("ROLE_CONTENTMANAGER").
//                                           anyRequest().authenticated();
        http.addFilterBefore(jwtFilter,AuthorizationFilter.class);

        return http.build();
    }

}


//h(cd)2
//        http.csrf().disable()
//                  .cors().disable();
//
//        http.addFilterBefore(jwtFilter, AuthorizationFilter.class);     // this  added after creation of jwt to handle the flow
////
////        http.authorizeHttpRequests()
////                .requestMatchers("/api/v1/auth/user-signup","/api/v1/auth/userSignIn","/api/v1/auth/content-manager-signup","/api/v1/auth/login-otp","api/v1/auth/validate-otp")
////                .permitAll()
////                .anyRequest().authenticated();
//
//
//        http.authorizeHttpRequests()
//                .requestMatchers("/api/v1/auth/user-signup","/api/v1/auth/userSignIn","/api/v1/auth/content-manager-signup","/api/v1/auth/login-otp","/api/v1/auth/validate-otp")
//                .permitAll().requestMatchers("/api/v1/cars/Add-car").hasRole("CONTENTMANAGER")
//                .anyRequest().authenticated();

//        http.addFilterBefore(jwtFilter, AuthorizationFilter.class);
//
