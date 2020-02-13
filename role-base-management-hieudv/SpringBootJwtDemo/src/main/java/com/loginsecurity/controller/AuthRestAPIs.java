package com.loginsecurity.controller;

import com.loginsecurity.message.request.LoginForm;
import com.loginsecurity.message.request.SignUpForm;
import com.loginsecurity.message.resqonse.JwtResponse;
import com.loginsecurity.model.Role;
import com.loginsecurity.model.RoleName;
import com.loginsecurity.model.User;
import com.loginsecurity.repository.RoleRepository;
import com.loginsecurity.repository.UserRepository;
import com.loginsecurity.security.jwt.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@CrossOrigin(origins = "*",maxAge = 3600)
@RestController("/api/auth")
public class AuthRestAPIs {
@Autowired
    AuthenticationManager authenticationManager;
@Autowired
    UserRepository userRepository;
@Autowired
    RoleRepository  roleRepository;
@Autowired
    PasswordEncoder encoder;
@Autowired
    JwtProvider jwtProvider;
@PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginForm loginRequest){
    Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                    loginRequest.getUsername(),
                    loginRequest.getPassword()
            )
    );
    SecurityContextHolder.getContext().setAuthentication(authentication);
    String jwt = jwtProvider.generateJwtToken(authentication);
    return  ResponseEntity.ok(new JwtResponse(jwt));
}
@PostMapping("/signup")
    public ResponseEntity<String> registerUser(@Valid @RequestBody SignUpForm signUpRequest){
    if(userRepository.existsByUsername(signUpRequest.getUsername())) {
        return new ResponseEntity<String>("Fail -> Username is already taken!",
                HttpStatus.BAD_REQUEST);
    }

    if(userRepository.existsByEmail(signUpRequest.getEmail())) {
        return new ResponseEntity<String>("Fail -> Email is already in use!",
                HttpStatus.BAD_REQUEST);
    }
    //
    User user = new User(signUpRequest.getName(), signUpRequest.getUsername(),
            signUpRequest.getEmail(), encoder.encode(signUpRequest.getPassword()));

    Set<String> strRoles = signUpRequest.getRole();
    Set<Role> roles = new HashSet<>();

    strRoles.forEach(role -> {
        switch(role) {
            case "admin":
                Role adminRole = roleRepository.findByName(RoleName.ROLE_ADMIN)
                        .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
                roles.add(adminRole);

                break;
            case "pm":
                Role pmRole = roleRepository.findByName(RoleName.ROLE_PM)
                        .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
                roles.add(pmRole);

                break;
            default:
                Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
                        .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
                roles.add(userRole);
        }
    });

    user.setRoles(roles);
    userRepository.save(user);

    return ResponseEntity.ok().body("User registered successfully!");

}

}
