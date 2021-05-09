package dev.protobot.blogcustom.model;


import javax.persistence.*;
import java.time.Instant;

@Entity
public class Post {

    @Id
    private Long postId;

    private String postName;

    private String url;

    private String description;

    private Integer voteCount;

    //Join referenced column -> userid
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userid", referencedColumnName = "userid")
    private User user;

    private Instant createdDate;

    //Join referenced column -> id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id" , referencedColumnName = "id")
    private Subreddit subreddit;

    public Post(){}

    public Post(Long postId, String postName, String url, String description, Integer voteCount, User user, Instant createdDate, Subreddit subreddit) {
        this.postId = postId;
        this.postName = postName;
        this.url = url;
        this.description = description;
        this.voteCount = voteCount;
        this.user = user;
        this.createdDate = createdDate;
        this.subreddit = subreddit;
    }

    public Post(String postName, String url, String description, Integer voteCount, User user, Instant createdDate, Subreddit subreddit) {
        this.postName = postName;
        this.url = url;
        this.description = description;
        this.voteCount = voteCount;
        this.user = user;
        this.createdDate = createdDate;
        this.subreddit = subreddit;
    }
}
