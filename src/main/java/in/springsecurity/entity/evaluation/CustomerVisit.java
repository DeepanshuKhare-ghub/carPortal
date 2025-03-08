package in.springsecurity.entity.evaluation;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "customer_visit")
public class CustomerVisit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotBlank(message = "Customer name cannot be empty")
    @Size(max = 100, message = "Customer name must not exceed 100 characters")
    @Column(name = "customer_name", nullable = false, length = 100)
    private String name;

    @NotBlank(message = "Mobile number is required")
    @Pattern(regexp = "\\d{10}", message = "Mobile number must be 10 digits")
    @Column(name = "cus_mobile", nullable = false, length = 10)
    private String mobile;

    @NotBlank(message = "PinCode is required")
    @Size(min = 6, max = 6, message = "PinCode must be 6 digits")
    @Column(name ="pinCode", nullable = false)
    private String pinCode;

    @NotNull(message = "Date of visit is required")
    @Column(name = "date_of_visit", nullable = false)
    private LocalDate dateOfVisit;

    @NotNull(message = "Time of visit is required")
    @Column(name = "time_of_visit", nullable = false)
    private LocalTime timeOfVisit;

    @ManyToOne
    @JoinColumn(name = "agent_id")
    private Agent agent;

    public Agent getAgent() {
        return agent;
    }

    public void setAgent(Agent agent) {
        this.agent = agent;
    }

    public LocalTime getTimeOfVisit() {
        return timeOfVisit;
    }

    public void setTimeOfVisit(LocalTime timeOfVisit) {
        this.timeOfVisit = timeOfVisit;
    }

    public LocalDate getDateOfVisit() {
        return dateOfVisit;
    }

    public void setDateOfVisit(LocalDate dateOfVisit) {
        this.dateOfVisit = dateOfVisit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPinCode() {
        return pinCode;
    }

    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}