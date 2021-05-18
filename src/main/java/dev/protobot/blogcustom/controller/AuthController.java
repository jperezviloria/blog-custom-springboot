package dev.protobot.blogcustom.controller;

import dev.protobot.blogcustom.dto.request.LoginRequest;
import dev.protobot.blogcustom.dto.request.RefreshTokenRequest;
import dev.protobot.blogcustom.dto.request.RegisterRequest;
import dev.protobot.blogcustom.dto.response.AuthenticationResponse;
import dev.protobot.blogcustom.dto.response.RestResponse;
import dev.protobot.blogcustom.service.implementation.AuthServiceImplementation;
import dev.protobot.blogcustom.service.implementation.RefreshTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthServiceImplementation authServiceImplementation;
    private final RefreshTokenService refreshTokenService;

    @Autowired
    public AuthController(AuthServiceImplementation authServiceImplementation,
                          RefreshTokenService refreshTokenService){
        this.authServiceImplementation = authServiceImplementation;
        this.refreshTokenService = refreshTokenService;
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

    @PostMapping("/login")
    public RestResponse<AuthenticationResponse> login (@RequestBody LoginRequest loginRequest){

        AuthenticationResponse authenticationResponse = authServiceImplementation.login(loginRequest);
        System.out.println(authenticationResponse);
        return new RestResponse<>(HttpStatus.OK, authenticationResponse);

    }

    @PostMapping("refresh/token")
    public AuthenticationResponse refreshToken (@RequestBody RefreshTokenRequest refreshTokenRequest){
        return authServiceImplementation.refreshToken(refreshTokenRequest);
    }

    @PostMapping("/logout")
    public RestResponse<String> logout(@RequestBody RefreshTokenRequest refreshTokenRequest){
        refreshTokenService.deleteRefreshToken(refreshTokenRequest.getRefreshToken());
        return new RestResponse<>(HttpStatus.OK, "Refresh token deleted successfully");
    }





}
