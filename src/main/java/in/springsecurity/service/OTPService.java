package in.springsecurity.service;

import in.springsecurity.entity.User;
import in.springsecurity.payload.OTPDetails;
import in.springsecurity.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

import static java.util.concurrent.TimeUnit.SECONDS;

@Service
public class OTPService {

    private final UserRepository userRepository;
    private final JWTService jwtService;

    public OTPService(UserRepository userRepository, JWTService jwtService) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
    }



    private final ConcurrentHashMap<String, OTPDetails> otpStorage = new ConcurrentHashMap<>();
     private static final long OTP_EXPIRY_TIME  = TimeUnit.SECONDS.toMillis(300);


    //Method to generate OTP
    public String generateOTP(String mobileNumber){


        // checking whether user exists in database
        User user = userRepository.findByMobile(mobileNumber).orElseThrow(() -> new RuntimeException("Unregistered mob+ile Number"));

            //generate a 6-digit random otp
            String otp = String.format("%06d", new Random().nextInt(999999));

            OTPDetails otpDetails = new OTPDetails(otp, System.currentTimeMillis());

            // stored in hashmap - key is mobileNumber and value is optDetails
            OTPDetails otpGet = otpStorage.put(mobileNumber, otpDetails);
            return otp + "  :  " + mobileNumber;
        }



    public ResponseEntity<?> validateOtp(String mobile, String otp) {

        // hashmap se otp nikala
        OTPDetails otpDetails = otpStorage.get(mobile);

        //check kiya ki kya yha otp null toh nhi hai
        if(otpDetails == null){
           return ResponseEntity.badRequest().body("otp is incorrect");
        }


        //check if otp is expired
        long currentTime = System.currentTimeMillis();
        long otpTime = otpDetails.getTimeStamp();
        long timeDifference = TimeUnit.MILLISECONDS.toMinutes(currentTime-otpTime);

        if(timeDifference>OTP_EXPIRY_TIME){
            otpStorage.remove(mobile);
            return  ResponseEntity.badRequest().body("Otp Expired");
        }
            if(otpDetails.getOtp().equals(otp)) {
                User user = userRepository.findByMobile(mobile).orElseThrow(() -> new RuntimeException("Invalid Mobile Number"));
                String token = jwtService.generateToken(user.getUsername(),user.getRole());
                return new ResponseEntity<>(token, HttpStatus.CREATED);

        }
            return ResponseEntity.badRequest().body("Technical error plz re-enter otp");
         }
    }

