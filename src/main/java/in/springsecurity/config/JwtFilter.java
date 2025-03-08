package in.springsecurity.config;

import in.springsecurity.repository.UserRepository;
import in.springsecurity.service.CustomUserDetailsService;
import in.springsecurity.service.JWTService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {

    private JWTService jwtService;

    private UserRepository userRepository;

    private CustomUserDetailsService customUserDetailsService;

    public JwtFilter(JWTService jwtService, UserRepository userRepository, CustomUserDetailsService customUserDetailsService) {
        this.jwtService = jwtService;
        this.userRepository = userRepository;
        this.customUserDetailsService = customUserDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader("Authorization");

        if(token!=null && token.startsWith("Bearer ")){
            String jwt = token.substring(8, token.length());
            String username = jwtService.getUsername(jwt);


            UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);

            if (userDetails != null) {
                UsernamePasswordAuthenticationToken authenticationToken =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                authenticationToken.setDetails(new WebAuthenticationDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authenticationToken);

//                System.out.println("Authenticated User: " + username);
//                System.out.println("Authorities: " + userDetails.getAuthorities());

            }
        }
        filterChain.doFilter(request, response);
        }

    }


//finding the actual user from repository
//            Optional<User> opUser = userRepository.findByUsername(username);
//            if(opUser.isPresent()){
//                User user = opUser.get();
//                UsernamePasswordAuthenticationToken authenticationToken
//                        = new UsernamePasswordAuthenticationToken(user,
//                        null,
//                        Collections.singleton(new SimpleGrantedAuthority(user.getRole())));   //used in security roles concept
//
//                //formula - australia sets new world Records
//                authenticationToken.setDetails(new WebAuthenticationDetails(request));
//
//                //Here we are finally setting up the details for login in authentication token
//                // and security context now have details to allow the access to the url's
//                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
//            }