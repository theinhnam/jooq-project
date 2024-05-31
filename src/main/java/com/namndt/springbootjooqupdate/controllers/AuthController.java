package com.namndt.springbootjooqupdate.controllers;


import com.namndt.springbootjooqupdate.data.requests.AuthRequest;
import com.namndt.springbootjooqupdate.data.responses.DFResponse;
import com.namndt.springbootjooqupdate.services.auth_service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<DFResponse<String>> login(@RequestBody AuthRequest authRequest){
        String token = authService.authenticate(authRequest);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new DFResponse<>("OK", token != null?"Login success":"login fail", token));
    }

}
