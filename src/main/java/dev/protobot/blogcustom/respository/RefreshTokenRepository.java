package dev.protobot.blogcustom.respository;


import dev.protobot.blogcustom.model.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.Instant;
import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {



     String querySaveRefreshToken = "INSERT INTO RefreshToken (token, createdDate) " +
            "VALUES( :token , :createdDate  ) RETURNING * ;";
    @Query(value = querySaveRefreshToken, nativeQuery = true)
    RefreshToken saveRefreshToken(
            @Param("token") String token,
            @Param("createdDate") Instant createdDate
    );

    String queryGetRefreshTokenByToken = "SELECT * FROM RefreshToken WHERE token = :token ;";
    @Query(value = queryGetRefreshTokenByToken, nativeQuery = true)
    Optional<RefreshToken> getRefreshTokenByToken(
            @Param("token") String token
    );

    String queryDeleteRefreshTokenByToken = "DELETE FROM RefreshToken WHERE token = :token ;";
    @Query(value = queryDeleteRefreshTokenByToken, nativeQuery = true)
    void deleteRefreshTokenByToken(
            @Param("token") String token
    );


    Optional<RefreshToken> findByToken(String token);



    void deleteByToken(String token);
}
