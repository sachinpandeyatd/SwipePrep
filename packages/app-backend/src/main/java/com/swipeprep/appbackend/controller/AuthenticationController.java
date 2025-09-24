package com.swipeprep.appbackend.controller;

import com.swipeprep.appbackend.dto.AuthenticationResponse;
import com.swipeprep.appbackend.dto.LoginRequest;
import com.swipeprep.appbackend.dto.RegisterRequest;
import com.swipeprep.appbackend.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

	private final AuthenticationService authenticationService;

	@PostMapping("/register")
	public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request){
		return ResponseEntity.ok(authenticationService.register(request));
	}

	@PostMapping("/login")
	public ResponseEntity<AuthenticationResponse> login(@RequestBody LoginRequest request){
		AuthenticationResponse response = authenticationService.login(request);

		return ResponseEntity.ok(response);
	}
}
