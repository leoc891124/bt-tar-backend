package com.leoc.springboot.bttar.controller;


import com.leoc.springboot.bttar.model.Companies;
import com.leoc.springboot.bttar.model.ERole;
import com.leoc.springboot.bttar.model.Role;
import com.leoc.springboot.bttar.model.User;
import com.leoc.springboot.bttar.payload.request.LoginRequest;
import com.leoc.springboot.bttar.payload.request.SignupRequest;
import com.leoc.springboot.bttar.payload.response.JwtResponse;
import com.leoc.springboot.bttar.payload.response.MessageResponse;
import com.leoc.springboot.bttar.repository.RoleRepo;
import com.leoc.springboot.bttar.repository.UserRepo;
import com.leoc.springboot.bttar.security.jwt.JwtUtils;
import com.leoc.springboot.bttar.services.CaptchaService;
import com.leoc.springboot.bttar.services.UserDetailsImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.method.P;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/bttar/v1.0/auth")
public class AuthController {
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepo userRepository;

	@Autowired
	RoleRepo roleRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;

	@Autowired
	CaptchaService captchaService;


	@GetMapping("/users")
	@PreAuthorize("hasRole('ADMIN')")
	public List<User> getUser(){
		//role = String.valueOf(ERole.ROLE_USER);
		return  userRepository.findAll();
	}

	@GetMapping("/role/users")
	@PreAuthorize("hasRole('ADMIN')")
	public List<User> getUserByRole(){

		Role userRole = roleRepository.findByName(ERole.ROLE_USER)
				.orElseThrow(() -> new RuntimeException("Error: Role is not found."));

		return userRepository.findByRoles(userRole);
	}

	@GetMapping("/user/{id}")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ResponseEntity<?> getUserById(@PathVariable String id){
		Optional<User> user = userRepository.findById(id);

		if(user.isEmpty()){
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Usuarios con id '"+id+"' no existe"));
		}

		return ResponseEntity.ok(user);
	}

	@PutMapping("/user/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> updateUser(@Valid @RequestBody SignupRequest signupRequest, @PathVariable String id){

		Optional<User> userUpdate = userRepository.findById(id);

		if(userUpdate.isPresent()){
			User user1 = userUpdate.get();

			if (userRepository.existsByUsername(signupRequest.getUsername()) && !user1.getUsername().equals(signupRequest.getUsername())) {
				return ResponseEntity
						.badRequest()
						.body(new MessageResponse("Error: Username is already taken!"));
			}

			if (userRepository.existsByEmail(signupRequest.getEmail()) && !user1.getEmail().equals(signupRequest.getEmail())) {
				return ResponseEntity
						.badRequest()
						.body(new MessageResponse("Error: Email is already in use!"));
			}

			user1.setEmail(signupRequest.getEmail());
			user1.setUsername(signupRequest.getUsername());
			user1.setFirstName(signupRequest.getFirstName());
			user1.setLastName(signupRequest.getLastName());
			user1.setPassword(encoder.encode(signupRequest.getPassword()));
			user1.setCompany(signupRequest.getCompany());
			user1.setEnabled(true);

			Set<String> strRoles = signupRequest.getRoles();
			Set<Role> roles = new HashSet<>();

			if (strRoles == null) {
				Role userRole = roleRepository.findByName(ERole.ROLE_USER)
						.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
				roles.add(userRole);
			} else {
				strRoles.forEach(role -> {
					if (role.equals("admin")) {
						Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
								.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
						roles.add(adminRole);
					} else {
						Role userRole = roleRepository.findByName(ERole.ROLE_USER)
								.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
						roles.add(userRole);
					}
				});
			}

			user1.setRoles(roles);
			user1.setAdmin(false);
			userRepository.save(user1);

			return ResponseEntity.ok(new MessageResponse("usuario actualizada con exito!!"));
		}

		else {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: usuario con id '"+id+"' no existe"));
		}




	}

	@DeleteMapping("/user/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> deleteUserById(@PathVariable String id){

     	userRepository.deleteById(id);
		return ResponseEntity.ok(new MessageResponse("Usuario eliminada exitosamente"));
	}

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

		if (!captchaService.verifyCaptcha(loginRequest.getRecaptchaToken())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Invalid reCAPTCHA. Please try again."));
		}


		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());

		return ResponseEntity.ok(new JwtResponse(jwt,
												 userDetails.getId(),
												 userDetails.getUsername(),
												 userDetails.getEmail(),
												 roles,
				                                 userDetails.getFirstName(),
				                                  userDetails.getLastName(),
				                                  userDetails.getAdmin(),
													userDetails.getCompany()

				));
	}



	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Username is already taken!"));
		}

		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Email is already in use!"));
		}

		// Create new user's account
		User user = new User(
							 signUpRequest.getFirstName(),
							signUpRequest.getLastName(),
							signUpRequest.getUsername(),
							 signUpRequest.getEmail(),
							 encoder.encode(signUpRequest.getPassword()),
				             signUpRequest.getAdmin(),
							 signUpRequest.getCompany()
		);
		user.setEnabled(true);

		Set<String> strRoles = signUpRequest.getRoles();
		Set<Role> roles = new HashSet<>();

		if (strRoles == null) {
			Role userRole = roleRepository.findByName(ERole.ROLE_USER)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
                if (role.equals("admin")) {
                    Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                    roles.add(adminRole);
                } else {
                    Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                    roles.add(userRole);
                }
			});
		}



		user.setRoles(roles);
		userRepository.save(user);

		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}
}
