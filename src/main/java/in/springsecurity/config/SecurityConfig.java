package in.springsecurity.config;

import in.springsecurity.repository.UserRepository;
import in.springsecurity.service.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.AuthorizationFilter;

import static in.springsecurity.config.ApiEndpoints.*;

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

//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//
//
//
//        http.csrf(csrf -> csrf.disable()).
//                   cors(cors-> cors.disable());
//
//        http.authorizeHttpRequests().anyRequest().permitAll();
////                .requestMatchers(
////                        "/api/v1/auth/user-signup",
////                                  "/api/v1/auth/userSignIn",
////                                  "/api/v1/auth/content-manager-signup",
////                                  "/api/v1/auth/login-otp").permitAll()
////                                     .requestMatchers("/api/v1/cars/Add-car").hasAuthority("ROLE_CONTENTMANAGER").
////                                           anyRequest().authenticated();
//        http.addFilterBefore(jwtFilter,AuthorizationFilter.class);
//
//        return http.build();
//    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        // 🔹 Publicly accessible endpoints
                        .requestMatchers(
                                AUTH_URL + USER_SIGNUP,
                                AUTH_URL + CONTENT_MANAGER_SIGNUP,
                                AUTH_URL + BLOG_MANAGER_SIGNUP,
                                AUTH_URL + USER_SIGNIN,
                                AUTH_URL + OTP_LOGIN,
                                AUTH_URL + OTP_VALIDATE
                        ).permitAll()

                        // 🔹 Car-related endpoints (Require ADMIN role)
                        .requestMatchers(
                                CAR_URL + ADD_BRAND_URL,
                                CAR_URL + ADD_FUELTYPE_URL,
                                CAR_URL + ADD_MODEL_URL,
                                CAR_URL + ADD_TRANSMISSION_URL,
                                CAR_URL + ADD_YEAR_URL,
                                CAR_URL + ADD_CARSTATUS_URL,
                                CAR_URL + ADD_CAR_URL
                        ).hasRole("ROLE_CONTENTMANAGER")

                        // 🔹 Update endpoints (Require ADMIN role)
                        .requestMatchers(CAR_URL + UPDATE_CAR_STATUS_URL).hasRole("ADMIN")

                        // 🔹 GET endpoints (Public or ROLE_USER)
                        .requestMatchers(CAR_URL + GET_ALL_CARS_URL, CAR_URL + GET_PARAM_URL).permitAll()
                        .requestMatchers(BASE_AREA_URL + ADD_AREA_URL).hasRole("ADMIN")

                        // 🔹 Image Uploading (Restrict to Admins)
                        .requestMatchers(IMAGE_BASE_URL + UPLOAD_FILE_URL).hasRole("ADMIN")
                        .requestMatchers(IMAGE_BASE_URL + GET_ALL_URL).authenticated()

                        // 🔹 Securing all other endpoints
                        .anyRequest().authenticated()
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

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
