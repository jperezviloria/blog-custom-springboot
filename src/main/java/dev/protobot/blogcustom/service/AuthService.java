package dev.protobot.blogcustom.service;

import dev.protobot.blogcustom.dto.request.RegisterRequest;
import dev.protobot.blogcustom.model.User;
import dev.protobot.blogcustom.model.VerificationToken;
import org.springframework.transaction.annotation.Transactional;

public interface AuthService {

    void signup(RegisterRequest registerRequest);

    //String generateVerificationToken(User user);

    void verifyAccount(String token);


    @Transactional
    void fetchUserAndEnable(VerificationToken verificationToken);

}
