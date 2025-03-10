package in.springsecurity.service;

import in.springsecurity.entity.User;
import in.springsecurity.model.Mapping;
import in.springsecurity.payload.userDto.LoginDto;
import in.springsecurity.payload.userDto.UserDto;
import in.springsecurity.payload.userDto.UserResponseDto;
import in.springsecurity.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class UserService {

    private UserRepository userRepository;
    private Mapping mapping;
    private JWTService jwtService;

    public UserService(UserRepository userRepository, Mapping mapping, JWTService jwtService) {
        this.userRepository = userRepository;
        this.mapping = mapping;
        this.jwtService = jwtService;
    }


    public ResponseEntity<Object> create(UserDto userDto, String role) {
        User user = mapping.user(userDto);

        Optional<User> byEmailId = userRepository.findByEmail(user.getEmail());
        if (byEmailId.isPresent()) {
            return ResponseEntity.badRequest().body("Email id already exists");
        }
        Optional<User> byUsername = userRepository.findByUsername(user.getUsername());

        if (byUsername.isPresent()) {
            return ResponseEntity.badRequest().body("Username already exists");
        }

        Optional<User> byMobile = userRepository.findByMobile(user.getMobile());

        if (byMobile.isPresent()) {
            return ResponseEntity.badRequest().body("Mobile Number already Used");
        }

//        String encodedPassword = passwordEncoder.encode(user.getPassword());
        String hashpw = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(10));
        user.setPassword(hashpw);
        user.setRole(role);
        User saved = userRepository.save(user);
        UserResponseDto userResponseDto = mapping.userResp(saved);
        return new ResponseEntity<>(userResponseDto, HttpStatus.CREATED);
    }

    public String login(LoginDto loginDto) {
        User user = userRepository.findByUsername(loginDto.getUsername()).orElseThrow(()-> new RuntimeException("User not Registered"));
            if (BCrypt.checkpw(loginDto.getPassword(), user.getPassword())) {
                return jwtService.generateToken(user.getUsername(),user.getRole());
            }
        return null;
    }
}
