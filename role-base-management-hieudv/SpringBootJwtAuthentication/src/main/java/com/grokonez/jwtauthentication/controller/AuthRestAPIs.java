package com.grokonez.jwtauthentication.controller;

import com.grokonez.jwtauthentication.message.request.Credential;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthRestAPIs {

    @Autowired
    AuthenticationManager authenticationManager;

//    @Autowired
//    PasswordEncoder encoder;



    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody Credential credential) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        credential.getUserName(),
                        credential.getUserPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

//        String jwt = jwtProvider.generateJwtToken(authentication);
//        return ResponseEntity.ok(new JwtResponse(jwt));
        return ResponseEntity.ok("aaaa");
    }

//    @PostMapping("/signup")
//    public ResponseEntity<String> registerUser(@Valid @RequestBody SignUpForm signUpRequest) {
//        if(userRepository.existsByUsername(signUpRequest.getUsername())) {
//            return new ResponseEntity<String>("Fail -> Username is already taken!",
//                    HttpStatus.BAD_REQUEST);
//        }
//
//        if(userRepository.existsByEmail(signUpRequest.getEmail())) {
//            return new ResponseEntity<String>("Fail -> Email is already in use!",
//                    HttpStatus.BAD_REQUEST);
//        }
//
//        // Creating user's account
//        User user = new User(signUpRequest.getName(), signUpRequest.getUsername(),
//                signUpRequest.getEmail(), encoder.encode(signUpRequest.getPassword()));
//
//        Set<String> strRoles = signUpRequest.getRole();
//        Set<Role> roles = new HashSet<>();
//
//        strRoles.forEach(role -> {
//        	switch(role) {
//	    		case "admin":
//	    			Role adminRole = roleRepository.findByName(RoleName.ROLE_ADMIN)
//	                .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
//	    			roles.add(adminRole);
//
//	    			break;
//	    		case "pm":
//	            	Role pmRole = roleRepository.findByName(RoleName.ROLE_PM)
//	                .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
//	            	roles.add(pmRole);
//
//	    			break;
//	    		default:
//	        		Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
//	                .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
//	        		roles.add(userRole);
//        	}
//        });
//
//        user.setRoles(roles);
//        userRepository.save(user);
//
//        return ResponseEntity.ok().body("User registered successfully!");
//    }
}