package dev.protobot.blogcustom.service.implementation;

import dev.protobot.blogcustom.exceptions.SpringRedditException;
import dev.protobot.blogcustom.model.RefreshToken;
import dev.protobot.blogcustom.respository.RefreshTokenRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.UUID;

@Service
@AllArgsConstructor
@Transactional
public class RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;

    public RefreshToken generateRefreshToken() {
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setToken(UUID.randomUUID().toString());
        refreshToken.setCreatedDate(Instant.now());

        return refreshTokenRepository.saveRefreshToken(refreshToken.getToken(), refreshToken.getCreatedDate());
    }

    void validateRefreshToken(String token) {
        refreshTokenRepository.getRefreshTokenByToken(token)
                .orElseThrow(() -> new SpringRedditException("Invalid refresh Token"));
    }

    public void deleteRefreshToken(String token) {
        refreshTokenRepository.deleteRefreshTokenByToken(token);
    }
}
