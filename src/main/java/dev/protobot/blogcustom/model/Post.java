package dev.protobot.blogcustom.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "post2")
public class Post {

    @Id
    @Column(name = "id")
    private Long postId;

    @Column(name = "postname")
    private String postName;

    private String url;

    private String description;

    @Column(name = "votecount")
    private Integer voteCount = 0;

    //Join referenced column -> userid
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userid", referencedColumnName = "userid")
    private User user;

    @Column(name = "createddate")
    private Instant createdDate;

    //Join referenced column -> id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idsubreddit" , referencedColumnName = "id")
    private Subreddit subreddit;

}
