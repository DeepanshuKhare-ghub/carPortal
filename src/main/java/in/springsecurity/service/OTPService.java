package in.springsecurity.service;

import in.springsecurity.entity.User;
import in.springsecurity.payload.OTPDetails;
import in.springsecurity.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Service
public class OTPService {

    private final UserRepository userRepository;
    private final JWTService jwtService;

    public OTPService(UserRepository userRepository, JWTService jwtService) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
    }



    private final Map<String, OTPDetails>  otpStorage = new HashMap<>();
    private static final int OTP_EXPIRY_TIME =5;


    //Method to generate OTP
    public String generateOTP(String mobileNumber){


        // checking whether user exists in database
        Optional<User> opUser = userRepository.findByMobile(mobileNumber);

        if(opUser.isPresent()) {

            //generate a 6-digit random otp
            String otp = String.format("%06d", new Random().nextInt(999999));

            OTPDetails otpDetails = new OTPDetails(otp, System.currentTimeMillis());

            // stored in hashmap - key is mobileNumber and value is optDetails
            OTPDetails otpGet = otpStorage.put(mobileNumber, otpDetails);
            return otp + "  :  " + mobileNumber;
        }
        return "user not found";
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

        else {
            otpDetails.getOtp().equals(otp);
            Optional<User> byMobile = userRepository.findByMobile(mobile);
            User user = byMobile.get();
            String token = jwtService.generateToken(user.getUsername());
            return new ResponseEntity<>(token, HttpStatus.CREATED);
        }
         }
    }

