package dev.protobot.blogcustom.respository;

import dev.protobot.blogcustom.dto.request.SubRedditRequest;
import dev.protobot.blogcustom.model.Subreddit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

@Repository
public interface SubredditRepository extends JpaRepository<SubRedditRequest,Long> {




    String querySaveSubredditDto =
            "INSERT INTO SubredditDto (name, description) " +
                    "VALUES( :name, :description) RETURNING *;";
    @Query(value = querySaveSubredditDto, nativeQuery = true)
    SubRedditRequest saveSubredditDto(
            @Param("name") String username,
            @Param("description") String password
    );


    String queryGetAllSubredditDto =
            "SELECT * FROM SubredditDto ;";
    @Query(value = queryGetAllSubredditDto, nativeQuery = true)
    List<SubRedditRequest> getAllSubredditDto();
}
