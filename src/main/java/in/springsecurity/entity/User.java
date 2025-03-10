package in.springsecurity.entity;

import jakarta.persistence.*;

@Entity
@Table(name ="user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id ;

    @Column(nullable = false)
    private String name;

    @Column(name="email_id",nullable = false, length = 100)
    private String email;

    @Column(name= "role", nullable = false ,length = 25)
    private String role;

    @Column(name="username", nullable = false , length = 100,unique = true)
    private String username;

    @Column(nullable = false, unique = true, length = 2000)
    private String password;

    @Column(name="mobile" ,unique = true, length = 10)
    private String mobile;


    @Column(name = "provider")
    private String provider;  // Stores "GOOGLE" if user logs in via Google

    @Column(name = "provider_id", unique = true)
    private String providerId; // Stores Google "sub" ID


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }



    public void setRole(String role) {
        this.role = role;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }
}
