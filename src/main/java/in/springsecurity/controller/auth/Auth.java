package in.springsecurity.controller.auth;

import in.springsecurity.config.ApiEndpoints;
import in.springsecurity.payload.jwtDto.JWTTokenDto;
import in.springsecurity.payload.userDto.LoginDto;
import in.springsecurity.payload.userDto.UserDto;
import in.springsecurity.service.JWTService;
import in.springsecurity.service.OTPService;
import in.springsecurity.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ApiEndpoints.AUTH_URL)
public class Auth {

    private UserService userService;
    private OTPService otpService;
    private JWTService jwtService;

    public Auth(UserService userService, OTPService otpService, JWTService jwtService) {
        this.userService = userService;
        this.otpService = otpService;
        this.jwtService = jwtService;
    }

    @PostMapping(ApiEndpoints.USER_SIGNUP)
    public ResponseEntity<Object> createUser(@RequestBody UserDto userDto){
        return userService.create(userDto,"ROLE_USER");
    }

    @PostMapping(ApiEndpoints.CONTENT_MANAGER_SIGNUP)
    public ResponseEntity<Object> createContentManager(@RequestBody UserDto userDto){
        return userService.create(userDto,"ROLE_CONTENTMANAGER");
    }

    @PostMapping(ApiEndpoints.BLOG_MANAGER_SIGNUP)
    public ResponseEntity<Object> blogManager(@RequestBody UserDto userDto){
        return userService.create(userDto,"ROLE_BLOGMANAGER");
    }

    @PostMapping(ApiEndpoints.USER_SIGNIN)
    public ResponseEntity<?> userSignIn(@RequestBody LoginDto loginDto){
        String jwtToken = userService.login(loginDto);
        if(jwtToken!=null) {
            JWTTokenDto jwtTokenDto = new JWTTokenDto();
            jwtTokenDto.setToken(jwtToken);
            jwtTokenDto.setTokenType("JWT");
            return new ResponseEntity<>(jwtTokenDto, HttpStatus.CREATED);
        }
        return new ResponseEntity<>("Invalid Username or Password", HttpStatus.UNAUTHORIZED);
    }


    @PostMapping(ApiEndpoints.OTP_LOGIN)
    public String generateOtp(@RequestParam String mobile){
       return otpService.generateOTP(mobile);
    }

    @PostMapping(ApiEndpoints.OTP_VALIDATE)
    public ResponseEntity<?> validateOtp(@RequestParam String mobile,
                              @RequestParam String otp){
        return otpService.validateOtp(mobile, otp);

    }
}
