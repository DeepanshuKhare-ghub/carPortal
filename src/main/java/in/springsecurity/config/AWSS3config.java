package in.springsecurity.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AWSS3config {
    @Value("${aws.accessKey}")
    private String accessKey;

    @Value("${aws.secretKey}")
    private String secretKey;

    @Value("${aws.region}")
    private String region;

    public AWSCredentials credentials(){
        AWSCredentials credentials = new BasicAWSCredentials(accessKey,secretKey);
        return credentials;
    }

    @Bean
    public AmazonS3 amazonS3(){
        AmazonS3 s3client = AmazonS3ClientBuilder.standard().
                withCredentials(new AWSStaticCredentialsProvider(credentials())).
                withRegion(region).
                build();
        return s3client;
    }


}
