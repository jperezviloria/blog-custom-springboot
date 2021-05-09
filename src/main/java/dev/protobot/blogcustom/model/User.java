package dev.protobot.blogcustom.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.Instant;

@Entity
@Table(name = "users")
public class User {

    @Id
    private Long userId;

    private String username;

    private String password;

    private String email;

    private Instant created;

    private boolean enable;

    public User(){}

    public User(Long userId, String username, String password, String email, Instant created, boolean enable) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.email = email;
        this.created = created;
        this.enable = enable;
    }

    public User(String username, String password, String email, Instant created, boolean enable) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.created = created;
        this.enable = enable;
    }
}
