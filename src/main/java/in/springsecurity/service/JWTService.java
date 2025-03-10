package in.springsecurity.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.Date;

@Service
public class JWTService {

    @Value("${jwt.key}")         //goes to property file and read value
    private String algorithmKey;

    @Value("${jwt.issuer}")
    private String issuer;

    @Value("${jwt.expiry}")
    private long expiry;

    private Algorithm algorithm;

    @PostConstruct   // bean lifecycle
    public void postConstruct() throws UnsupportedEncodingException {
        algorithm = Algorithm.HMAC256(algorithmKey);
    }


    //Generating token

    //formula - Computer Engineer is Always Unemployed
//    public String generateToken(String username) {
//         return JWT.create()
//                .withClaim("username",username)
//                      .withExpiresAt(new Date(System.currentTimeMillis()+expiry))
//                            .withIssuer(issuer)
//                                 .sign(algorithm);
//    }
//
//
//    // method for getUsername from jwt token for validation
//
//    //formula - Junior sachin with bodyguard vikram
//    public String getUsername(String token){
//        DecodedJWT verify = JWT.require(algorithm)
//                .withIssuer(issuer)
//                .build()
//                .verify(token);
//       return  verify.getClaim("username").asString();
//    }


    public String generateToken(String username, String role) {
        return JWT.create()
                .withClaim("username", username)
                .withClaim("role", role)
                .withExpiresAt(new Date(System.currentTimeMillis() + expiry))
                .withIssuer(issuer)
                .sign(algorithm);
    }

    // Extract username from JWT token
    public String getUsername(String token) {
        DecodedJWT verify = JWT.require(algorithm)
                .withIssuer(issuer)
                .build()
                .verify(token);
        return verify.getClaim("username").asString();
    }

    // Extract user role from JWT token
    public String getRole(String token) {
        DecodedJWT verify = JWT.require(algorithm)
                .withIssuer(issuer)
                .build()
                .verify(token);
        return verify.getClaim("role").asString();
    }
}
