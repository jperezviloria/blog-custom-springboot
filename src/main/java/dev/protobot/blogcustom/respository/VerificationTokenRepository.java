package dev.protobot.blogcustom.respository;

import dev.protobot.blogcustom.model.User;
import dev.protobot.blogcustom.model.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.Optional;

@Repository
public interface VerificationTokenRepository extends JpaRepository<VerificationToken,Long> {

    String queryCreateToken =
            "INSERT INTO token (token, userid) " +
                    "VALUES( :token, :userid) RETURNING NULL;";
    @Query(value = queryCreateToken, nativeQuery = true)
    void createToken(
            @Param("token") String token,
            @Param("userid") Long userid);


    String queryFindByToken =
            "SELECT * FROM token WHERE token = :token ;";
    @Query(value = queryFindByToken, nativeQuery = true)
    Optional<VerificationToken> findByToken(@Param("token") String token);

}
