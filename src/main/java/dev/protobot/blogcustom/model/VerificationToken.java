package dev.protobot.blogcustom.model;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "token")
public class VerificationToken {

    @Id
    private Long id;

    private String token;

//    @OneToOne(fetch = FetchType.LAZY)
//    private User user;

    @Column(name = "userid")
    private Long userId;

    @Column(name = "expirydate")
    private Instant expiryDate;

    public VerificationToken(){}

    public VerificationToken(Long id, String token, Long userId, Instant expiryDate) {
        this.id = id;
        this.token = token;
        this.userId = userId;
        this.expiryDate = expiryDate;
    }

    public VerificationToken(String token, Long userId, Instant expiryDate) {
        this.token = token;
        this.userId = userId;
        this.expiryDate = expiryDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Instant getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Instant expiryDate) {
        this.expiryDate = expiryDate;
    }
}
