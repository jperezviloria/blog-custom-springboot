package dev.protobot.blogcustom.model;

import javax.persistence.*;
import java.time.Instant;

@Entity
public class Comment {

    @Id
    private Long id;

    private String text;

    //Join referenced column -> postid
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "postid", referencedColumnName = "id")
    private Post post;

    private Instant createdDate;

    //Join referenced column -> userid
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userid", referencedColumnName = "userid")
    private User user;

    public Comment(){}

    public Comment(Long id, String text, Post post, Instant createdDate, User user) {
        this.id = id;
        this.text = text;
        this.post = post;
        this.createdDate = createdDate;
        this.user = user;
    }

    public Comment(String text, Post post, Instant createdDate, User user) {
        this.text = text;
        this.post = post;
        this.createdDate = createdDate;
        this.user = user;
    }
}
