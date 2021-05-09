package dev.protobot.blogcustom.model;

import javax.persistence.*;

@Entity
public class Vote {

    @Id
    private Long voteId;

    private VoteType voteType;

    //Join referenced column -> postid
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "postid", referencedColumnName = "id")
    private Post post;

    //Join referenced column -> userid
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userid", referencedColumnName = "userid")
    private User user;


    public Vote(){}

    public Vote(Long voteId, VoteType voteType, Post post, User user) {
        this.voteId = voteId;
        this.voteType = voteType;
        this.post = post;
        this.user = user;
    }

    public Vote(VoteType voteType, Post post, User user) {
        this.voteType = voteType;
        this.post = post;
        this.user = user;
    }
}
