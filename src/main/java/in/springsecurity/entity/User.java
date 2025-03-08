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
    private String emailId;

    @Column(name= "role", nullable = false ,length = 25)
    private String role;

    @Column(name="username", nullable = false , length = 100,unique = true)
    private String username;

    @Column(nullable = false, unique = true, length = 2000)
    private String password;

    @Column(name="mobile" ,unique = true, length = 10)
    private String mobile;



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

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
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
}
