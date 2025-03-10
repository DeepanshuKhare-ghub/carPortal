package in.springsecurity.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import in.springsecurity.entity.User;
import in.springsecurity.repository.UserRepository;
import in.springsecurity.service.JWTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component
public class OAuth2LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JWTService jwtService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        OAuth2User oauthUser = (OAuth2User) authentication.getPrincipal();
        String email = oauthUser.getAttribute("email");
        String googleId = oauthUser.getAttribute("sub");  // Google unique ID

        // ✅ Check if user exists in DB
        Optional<User> existingUser = userRepository.findByEmail(email);
        User user;
        if (existingUser.isEmpty()) {
            user = new User();
            user.setEmail(email);
            user.setUsername(email);  // ✅ Google users ke liye username = email
            user.setName(oauthUser.getAttribute("name"));
            user.setRole("USER");
            user.setPassword("OAUTH_USER"); // Default role
            user.setProvider("GOOGLE");  // ✅ Identify login provider
            user.setProviderId(googleId);
            userRepository.save(user);
        } else {
            user = existingUser.get();
        }

        // ✅ JWT Generate with username & role
        String token = jwtService.generateToken(user.getUsername(), user.getRole());


        // Send JSON response instead of redirect
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        Map<String, String> responseData = new HashMap<>();
        responseData.put("token", token);

        ObjectMapper objectMapper = new ObjectMapper();
        response.getWriter().write(objectMapper.writeValueAsString(responseData));
    }
}
