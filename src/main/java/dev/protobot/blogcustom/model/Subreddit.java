package dev.protobot.blogcustom.model;

import lombok.*;

import javax.persistence.*;
import java.time.Instant;
import java.util.List;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Subreddit {

    @Id
    private Long id;

    private String name;

    private String description;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Post> posts;

    private Instant createdDate;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    /*
    public Subreddit(){}

    public Subreddit(Long id, String name, String description, List<Post> posts, Instant createdDate, User user) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.posts = posts;
        this.createdDate = createdDate;
        this.user = user;
    }

    public Subreddit(String name, String description, List<Post> posts, Instant createdDate, User user) {
        this.name = name;
        this.description = description;
        this.posts = posts;
        this.createdDate = createdDate;
        this.user = user;
    }


     */

}
