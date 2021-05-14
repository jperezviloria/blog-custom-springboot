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
public class Post {

    @Id
    private Long postId;

    private String postName;

    private String url;

    private String description;

    private Integer voteCount = 0;

    //Join referenced column -> userid
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userid", referencedColumnName = "userid")
    private User user;

    private Instant createdDate;

    //Join referenced column -> id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id" , referencedColumnName = "id")
    private Subreddit subreddit;

}
