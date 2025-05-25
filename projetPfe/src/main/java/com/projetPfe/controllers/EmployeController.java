package com.projetPfe.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projetPfe.JWT.JwtUtils;
import com.projetPfe.entities.ERole;
import com.projetPfe.entities.Employe;
import com.projetPfe.entities.JwtResponse;
import com.projetPfe.entities.LoginRequest;
import com.projetPfe.entities.MessageResponse;
import com.projetPfe.entities.SignupRequest;
import com.projetPfe.entities.UserDetailsImpl;

import com.projetPfe.repositories.UserRepository;

import jakarta.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/auth")
public class EmployeController {
	@Autowired
	AuthenticationManager authenticationManager;
	@Autowired
	UserRepository userRepository;
	@Autowired
	PasswordEncoder encoder;
	@Autowired
	JwtUtils jwtUtils;

	@PostMapping("/login")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		System.out.println("authentication -------- "+authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);

		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
				.collect(Collectors.toList());
		System.out.println("userDetails ------- "+ userDetails.toString());
		return ResponseEntity.ok(
				new JwtResponse(jwt, userDetails.getId(), userDetails.getFirstName(), userDetails.getEmail(), roles));
	}

	
	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
	    if (userRepository.existsByEmail(signUpRequest.getEmail())) {
	        return ResponseEntity
	                .badRequest()
	                .body(new MessageResponse("Error: Email is already taken!"));
	    }

	    // Créer un nouvel utilisateur
	    Employe user = new Employe();
	    user.setPrenom(signUpRequest.getFirstName());
	    user.setNom(signUpRequest.getLastName());
	    user.setEmail(signUpRequest.getEmail());
	    user.setMotDePasse(encoder.encode(signUpRequest.getPassword()));

	    // Récupérer le rôle
	    ERole roleToAssign = ERole.ROLE_ChargéClientele; // par défaut
	    Set<String> strRoles = signUpRequest.getRole();

	    if (strRoles != null && strRoles.contains("back_Office")) {
	        roleToAssign = ERole.ROLE_BackOffice;
	    }

	    user.setRole(roleToAssign);
	    userRepository.save(user);

	    return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}

	
	
	
	
	
}


