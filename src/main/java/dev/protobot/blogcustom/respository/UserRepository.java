package dev.protobot.blogcustom.respository;

import dev.protobot.blogcustom.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    /*
    private Long userId;

    private String username;

    private String password;

    private String email;

    private Instant created;

    private boolean enable;
     */



    String queryCreateUser =
            "INSERT INTO Users (username, password, email, created, enable) " +
                    "VALUES( :username, :password, :email, :created, :enable) RETURNING *;";
    @Query(value = queryCreateUser, nativeQuery = true)
    User createUser(
            @Param("username") String username,
            @Param("password") String password,
            @Param("email") String email,
            @Param("created") Instant created,
            @Param("enable") boolean enable);



    String queryUpdateEnableUser =
            "UPDATE Users SET " +
                    "enable = :enable " +
                    "WHERE userid = :userid " +
                    "RETURNING NULL ;";
    @Query(value = queryUpdateEnableUser, nativeQuery = true)
    User updateEnableUser(
            @Param("userid") Long userid,
            @Param("enable") boolean enable);


    String queryFindByUsername =
            "SELECT * FROM users" +
                    "WHERE username = :username ;";
    @Query(value = queryFindByUsername, nativeQuery = true)
    Optional<User> findByUsername(
            @Param("username") String username);

    String queryFindById =
            "SELECT * FROM users" +
                    "WHERE userid = :userid ;";
    @Query(value = queryFindById, nativeQuery = true)
    Optional<User> findUserById(
            @Param("userid") Long userid);


}
