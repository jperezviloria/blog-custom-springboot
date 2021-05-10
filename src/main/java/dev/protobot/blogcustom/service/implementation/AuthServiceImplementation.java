package dev.protobot.blogcustom.service.implementation;

import dev.protobot.blogcustom.dto.request.RegisterRequest;
import dev.protobot.blogcustom.exceptions.SpringRedditException;
import dev.protobot.blogcustom.model.NotificationEmail;
import dev.protobot.blogcustom.model.User;
import dev.protobot.blogcustom.model.VerificationToken;
import dev.protobot.blogcustom.respository.UserRepository;
import dev.protobot.blogcustom.respository.VerificationTokenRepository;
import dev.protobot.blogcustom.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
public class AuthServiceImplementation implements AuthService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final VerificationTokenRepository verificationTokenRepository;
    private final MailServiceImplementation mailServiceImplementation;

    @Autowired
    public AuthServiceImplementation(PasswordEncoder passwordEncoder,
                                     UserRepository userRepository,
                                     VerificationTokenRepository verificationTokenRepository,
                                     MailServiceImplementation mailServiceImplementation){
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.verificationTokenRepository = verificationTokenRepository;
        this.mailServiceImplementation = mailServiceImplementation;
    }

    @Override
    @Transactional
    public void signup(RegisterRequest registerRequest) {
        User user = new User();
        user.setUsername(registerRequest.getUsername());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setCreated(Instant.now());
        user.setEnable(false);
        System.out.println(user);
        userRepository.createUser(
                user.getUsername(),
                user.getPassword(),
                user.getEmail(),
                user.getCreated(),
                user.isEnable());

//        generateVerificationToken(user);
        String token = generateVerificationToken(user);
        mailServiceImplementation.sendMail(new NotificationEmail("Please Activate your Account",
                user.getEmail(), "Thanks you for signin up to Spring Reddit," +
                " please click on the below url to activate your account : " +
                "http://localhost:8080/api/auth/accountVerification/" + token));
    }

    private String generateVerificationToken(User user){
        String token = UUID.randomUUID().toString();
        VerificationToken verificationToken = new VerificationToken();
        System.out.println(token);
        verificationToken.setToken(token);
        verificationToken.setUserId(user.getUserId());
        System.out.println(verificationToken);
        //verificationTokenRepository.createToken(verificationToken.getToken()) ;
        verificationTokenRepository.createToken(verificationToken.getToken(), verificationToken.getUserId());
        //verificationTokenRepository.save(verificationToken);
        return token;

    }

    public void verifyAccount(String token) {
        Optional<VerificationToken> verificationToken = verificationTokenRepository.findByToken(token);
        verificationToken.orElseThrow(() -> new SpringRedditException("Invalid Token"));
        fetchUserAndEnable(verificationToken.get());

    }

    @Transactional
    @Override
    public void fetchUserAndEnable(VerificationToken verificationToken) {
        Long userid = verificationToken.getUserId();
        User user = userRepository.findUserById(userid)
                .orElseThrow(() -> new SpringRedditException(
                                ("User not found with user id  ").concat(userid.toString())));

        user.setEnable(true);
        userRepository.updateEnableUser(user.getUserId(), user.isEnable());
    }
}
