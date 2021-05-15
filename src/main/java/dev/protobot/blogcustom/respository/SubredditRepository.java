package dev.protobot.blogcustom.respository;

import dev.protobot.blogcustom.dto.SubredditDto;
import dev.protobot.blogcustom.model.Subreddit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubredditRepository extends JpaRepository<Subreddit,Long> {


    String querySaveSubredditDto =
            "INSERT INTO SubredditDto (name, description) " +
                    "VALUES( :name, :description) RETURNING *;";
    @Query(value = querySaveSubredditDto, nativeQuery = true)
    SubredditDto saveSubredditDto(
            @Param("name") String username,
            @Param("description") String password
    );


    String queryGetAllSubredditDto =
            "SELECT * FROM SubredditDto ;";
    @Query(value = queryGetAllSubredditDto, nativeQuery = true)
    List<SubredditDto> getAllSubredditDto();

    //-------------------------------------------------------------------------

    String querySaveSubreddit =
            "INSERT INTO Subreddit2 (name, description) " +
                    "VALUES( :name, :description) RETURNING *;";
    @Query(value = querySaveSubreddit, nativeQuery = true)
    Subreddit saveSubreddit(
            @Param("name") String username,
            @Param("description") String password
    );


//    String queryGetAllSubreddit =
//            "SELECT s.id, s.name, s.description, p.id FROM Subreddit2 as s JOIN Post2 as p ON p.id = s.id  ;";
String queryGetAllSubreddit =
        "SELECT * FROM Subreddit2  ;";
    @Query(value = queryGetAllSubreddit, nativeQuery = true)
    List<Subreddit> getAllSubreddit();
}
