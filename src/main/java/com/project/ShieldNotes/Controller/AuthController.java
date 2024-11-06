package com.project.ShieldNotes.Controller;

import com.project.ShieldNotes.Entity.AppRole;
import com.project.ShieldNotes.Entity.Role;
import com.project.ShieldNotes.Entity.UserClass;
import com.project.ShieldNotes.Repository.RoleRepository;
import com.project.ShieldNotes.Repository.UserRepository;
import com.project.ShieldNotes.security.UserDetailsImpl;
import com.project.ShieldNotes.security.UserDetailsImplService;
import com.project.ShieldNotes.security.jwt.JwtUtils;
import com.project.ShieldNotes.security.request.LoginRequest;
import com.project.ShieldNotes.security.request.SignupRequest;
import com.project.ShieldNotes.security.response.LoginResponse;
import com.project.ShieldNotes.security.response.MessageResponse;
import com.project.ShieldNotes.security.response.UserInfoResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    PasswordEncoder encoder;


    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    UserDetailsImplService userService;

    @PostMapping("/public/signin")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {


        Authentication authentication;
        try {
            authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        } catch (AuthenticationException exception) {
            Map<String, Object> map = new HashMap<>();
            map.put("message", "Bad credentials");
            map.put("status", false);
            return new ResponseEntity<Object>(map, HttpStatus.NOT_FOUND);
        }

//      set the authentication
        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        String jwtToken = jwtUtils.generateTokenFromUsername(userDetails);

        // Collect roles from the UserDetails
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        // Prepare the response body, now including the JWT token directly in the body
        LoginResponse response = new LoginResponse(userDetails.getUsername(), roles, jwtToken);

        // Return the response entity with the JWT token included in the response body
        return ResponseEntity.ok(response);
    }

    @PostMapping("/public/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signupRequest) {
        if (userRepository.existsByUserName(signupRequest.getUsername())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Username already exists"));
        }
        if (userRepository.existsByEmail(signupRequest.getEmail())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Email already exists"));
        }

        UserClass userClass = new UserClass(signupRequest.getUsername(), signupRequest.getEmail(), encoder.encode(signupRequest.getPassword()));
        Set<String> strRoles = signupRequest.getRole();
        Role role;
        if(strRoles == null || strRoles.isEmpty()) {
            role = roleRepository.findByRoleName(AppRole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role not found"));
        }
        else{
            String roleStr = strRoles.iterator().next();
            if(roleStr.equals("admin")){
                role = roleRepository.findByRoleName(AppRole.ROLE_ADMIN)
                        .orElseThrow(() -> new RuntimeException("Error: Role not found"));
            }
            else{
                role = roleRepository.findByRoleName(AppRole.ROLE_USER)
                        .orElseThrow(() -> new RuntimeException("Error: Role not found"));
            }
        }
        userClass.setAccountNonLocked(true);
        userClass.setAccountNonExpired(true);
        userClass.setCredentialsNonExpired(true);
        userClass.setEnabled(true);
        userClass.setCredentialsExpiryDate(LocalDate.now().plusYears(1));
        userClass.setAccountExpiryDate(LocalDate.now().plusYears(1));
        userClass.setTwoFactorEnabled(false);
        userClass.setSignUpMethod("email");
        userClass.setRole(role);
        userRepository.save(userClass);

        return ResponseEntity.ok(new MessageResponse("User registered successfully"));


    }
    @GetMapping("/users")
    public ResponseEntity<?> getUserDetails(@AuthenticationPrincipal UserDetails userDetails) {
        UserClass userClass = userService.findByUserName(userDetails.getUsername());
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        UserInfoResponse response = new UserInfoResponse(
                userClass.getUserId(),
                userClass.getUserName(),
                userClass.getEmail(),
                userClass.isAccountNonLocked(),
                userClass.isAccountNonExpired(),
                userClass.isCredentialsNonExpired(),
                userClass.isEnabled(),
                userClass.getCredentialsExpiryDate(),
                userClass.getAccountExpiryDate(),
                userClass.isTwoFactorEnabled(),
                roles
        );

        return ResponseEntity.ok().body(response);

    }
    @GetMapping("/username")
    public String currentUserName(@AuthenticationPrincipal UserDetails userDetails) {
        return (userDetails != null) ? userDetails.getUsername() : "";
    }
}








