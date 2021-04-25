package com.ps.controller;

import com.ps.model.AccessToken;
import com.ps.model.JwtUser;
import com.ps.util.JwtUtil;
import io.micrometer.core.annotation.Timed;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/token")
@CrossOrigin
public class TokenController {

    private JwtUtil jwtGenerator;

    public TokenController(JwtUtil jwtGenerator) {
        this.jwtGenerator = jwtGenerator;
    }

    @PostMapping("/getNormalUserToken")
    public AccessToken generate(String yourName, int yearOfBirth) {
        return new AccessToken(jwtGenerator.generate(new JwtUser(yearOfBirth, yourName, "USER")));

    }

    @Timed(value = "getAdminToken.request")
    @GetMapping("/getAdminToken")
    public AccessToken getSampleTokenRequest() {
        return new AccessToken(jwtGenerator.generate(new JwtUser(1990, "Dhananjaya", "ADMIN")));
    }
}
