package dev.protobot.blogcustom.controller;

import dev.protobot.blogcustom.dto.request.RegisterRequest;
import dev.protobot.blogcustom.dto.response.RestResponse;
import dev.protobot.blogcustom.service.implementation.AuthServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthServiceImplementation authServiceImplementation;

    @Autowired
    public AuthController(AuthServiceImplementation authServiceImplementation){
        this.authServiceImplementation = authServiceImplementation;
    }

    @PostMapping("/signup")
    public RestResponse<?> signup(@RequestBody RegisterRequest registerRequest){
        authServiceImplementation.signup(registerRequest);
        return new RestResponse<>(HttpStatus.OK, "User Register Successfully");
    }

    @GetMapping("/accountVerification/{token}")
    public RestResponse<?> verifyAccount(@PathVariable String token){
        authServiceImplementation.verifyAccount(token);
        return new RestResponse<>(HttpStatus.OK, "Account Activate Successfully");

    }

}
