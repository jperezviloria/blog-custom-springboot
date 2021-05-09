package dev.protobot.blogcustom.model;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "token")
public class VerificationToken {

    @Id
    private Long id;

    private String token;

    @OneToOne(fetch = FetchType.LAZY)
    private User user;

    private Instant expiryDate;

    public VerificationToken(){}

    public VerificationToken(Long id, String token, User user, Instant expiryDate) {
        this.id = id;
        this.token = token;
        this.user = user;
        this.expiryDate = expiryDate;
    }

    public VerificationToken(String token, User user, Instant expiryDate) {
        this.token = token;
        this.user = user;
        this.expiryDate = expiryDate;
    }
}
