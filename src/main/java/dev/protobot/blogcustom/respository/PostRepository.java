package dev.protobot.blogcustom.respository;

import dev.protobot.blogcustom.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post,Long> {


    String queryGetPostByIdSubreddit = "SELECT * FROM Post WHERE  idSubreddit = :idSubreddit ";
    @Query(value = queryGetPostByIdSubreddit, nativeQuery = true)
    List<Post> getPostByIdSubreddit(
            @Param("idSubreddit") Long idSubreddit
    );

    String queryGetPostByUsername = "SELECT * FROM Post WHERE  username = :username ";
    @Query(value = queryGetPostByUsername, nativeQuery = true)
    List<Post> getPostByUsername(
            @Param("username") String username
    );


    String queryGetPostByName = "SELECT * FROM Post WHERE  postname = :postname ";
    @Query(value = queryGetPostByName, nativeQuery = true)
    Optional<Post> getPostByName(
            @Param("postname") String postname
    );

    String querySavePost = "INSERT INTO Post2 (postName, description, url, userId, createdDate, idSubreddit, voteCount) " +
            "VALUES( :postName , :description , :url , :userId , :createdDate , :idsubreddit , :voteCount ) ;";
    @Query(value = querySavePost, nativeQuery = true)
    Post savePost(
            @Param("postName") String postName,
            @Param("description") String description,
            @Param("url") String url,
            @Param("userId") Long userId,
            @Param("createdDate") Instant createdDate,
            @Param("idsubreddit") Long idSubreddit,
            @Param("voteCount") int voteCount
    );



}
