package in.springsecurity.config;

import in.springsecurity.repository.UserRepository;
import in.springsecurity.service.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.AuthorizationFilter;

import static in.springsecurity.config.ApiEndpoints.*;

@Configuration
public class SecurityConfig {

    private final JwtFilter jwtFilter;
    private final UserRepository userRepository;
    private final OAuth2LoginSuccessHandler successHandler;

    public SecurityConfig(JwtFilter jwtFilter, UserRepository userRepository, OAuth2LoginSuccessHandler successHandler) {
        this.jwtFilter = jwtFilter;
        this.userRepository = userRepository;
        this.successHandler = successHandler;
    }

    @Bean
    public CustomUserDetailsService userDetailsService() {
        return new CustomUserDetailsService(userRepository);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        // Publicly accessible endpoints
                        .requestMatchers(
                                AUTH_URL + USER_SIGNUP,
                                AUTH_URL + CONTENT_MANAGER_SIGNUP,
                                AUTH_URL + ADMIN_SIGNUP,
                                AUTH_URL + USER_SIGNIN,
                                AUTH_URL + OTP_LOGIN,
                                AUTH_URL + OTP_VALIDATE
                        ).permitAll()

                        // Car-related endpoints (Require CONTENTMANAGER role)
                        .requestMatchers(
                                CAR_URL + ADD_BRAND_URL,
                                CAR_URL + ADD_FUELTYPE_URL,
                                CAR_URL + ADD_MODEL_URL,
                                CAR_URL + ADD_TRANSMISSION_URL,
                                CAR_URL + ADD_YEAR_URL,
                                CAR_URL + ADD_CARSTATUS_URL,
                                CAR_URL + ADD_CAR_URL
                        ).hasAuthority("CONTENTMANAGER")

                        // Update endpoints (Require ADMIN role)
                        .requestMatchers(CAR_URL + UPDATE_CAR_STATUS_URL).hasAuthority("ADMIN")

                        // GET endpoints (Public or ROLE_USER)
                        .requestMatchers(CAR_URL + GET_ALL_CARS_URL, CAR_URL + GET_PARAM_URL).permitAll()
                        .requestMatchers(BASE_AREA_URL + ADD_AREA_URL).hasAuthority("ADMIN")

                        .requestMatchers("/", "/login", "/error").permitAll()
                        // Image Uploading (Restrict to Admins)
                        .requestMatchers(IMAGE_BASE_URL + UPLOAD_FILE_URL).hasAuthority("ADMIN")
                        .requestMatchers(IMAGE_BASE_URL + GET_ALL_URL).authenticated()

                        // Secure all other endpoints
                        .anyRequest().authenticated()
                )
                .oauth2Login(oauth -> oauth.successHandler(successHandler))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        http.addFilterBefore(jwtFilter, AuthorizationFilter.class);

        return http.build();
    }
}