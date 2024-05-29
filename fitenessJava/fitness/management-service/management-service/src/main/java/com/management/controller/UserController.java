package com.management.controller;

import java.util.List;
import java.util.Optional;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.management.bean.MedicalHistoryBean;
import com.management.dto.AuthRequest;
import com.management.dto.JwtResponse;
import com.management.entity.RefreshToken;
import com.management.entity.User;
import com.management.repository.UserRepository;
import com.management.service.UserService;
import com.management.serviceImpl.JwtService;
import com.management.serviceImpl.RefreshTokenService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/user")
@CrossOrigin(origins ="*")
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    
	@Autowired
	private JwtService jwtService;

	@Autowired
	private RefreshTokenService refreshTokenService;

	@Autowired
	private AuthenticationManager authenticationManager;


    @Autowired
    private UserService userService;
    
    @Autowired
    private UserRepository userRepository;


    @GetMapping("/check")
    public String demo() {
        log.info("Hello endpoint accessed");
        return "hello";
    }
/**
 * 
 * @param user
 * @return
 */
    @PostMapping("/save")
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        userService.saveUser(user);
        log.info("User saved successfully: {}", user);
        ResponseEntity<User> responseEntity = new ResponseEntity<>(user, HttpStatus.CREATED);
        return responseEntity;
    }
    /**
     * 
     * @param loginBean
     * @return
     */

//    @PostMapping("/login")
//    public ResponseEntity<User> login(@RequestBody LoginBean loginBean) {
//        User user = userService.validateLogin(loginBean);
//
//        if (user != null) {
//            log.info("User login successful: {}", user);
//            return ResponseEntity.ok(user);
//        } else {
//            log.error("User login failed");
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
//        }
//    }
    
	@PostMapping("/login")
	public JwtResponse authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
		
		System.out.println("hii");
		org.springframework.security.core.Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(authRequest.getName(), authRequest.getPassword()));
		if (authentication.isAuthenticated()) {
			System.out.println("hii");
//			RegistrationForm user = registrationService.validateLogin(authRequest);
			User user = userRepository.findByName(authRequest.getName()).get();
			System.out.println(user);
			System.out.println("hii");
        
			RefreshToken refreshToken = refreshTokenService.createRefreshToken(authRequest.getName());
			System.out.println(refreshToken);
			return JwtResponse.builder().accessToken(jwtService.generateToken(authRequest.getName()))
					.token(refreshToken.getToken()).user(user).build();
		} else {
			throw new UsernameNotFoundException("invalid user request !");
		}
	}

	@PostMapping("/refreshToken")
	public JwtResponse refreshToken(@RequestBody RefreshToken refreshTokenRequest) {
		return refreshTokenService.findByToken(refreshTokenRequest.getToken())
				.map(refreshTokenService::verifyExpiration).map(RefreshToken::getUser).map(userInfo -> {
					String accessToken = jwtService.generateToken(userInfo.getName());
					return JwtResponse.builder().accessToken(accessToken).token(refreshTokenRequest.getToken()).build();
				}).orElseThrow(() -> new RuntimeException("Refresh token is not in database!"));
	}
    
    
   
	
	

    /**
     * 
     * @param name
     * @return
     */
    @GetMapping("/getByName/{name}")
    public ResponseEntity<Optional<User>> getUserByName(@PathVariable String name) {
        Optional<User> user = userService.getUserByName(name);
        log.info("User fetched by name: {}", name);
        return ResponseEntity.ok(user);
    }

    /**
     * 
     * @param id
     * @return
     */
    @GetMapping("/getById/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        log.info("Fetching user by ID: {}", id);
        User user = userService.getUserById(id);
        log.info("User details fetched successfully");
        ResponseEntity<User> responseEntity = new ResponseEntity<>(user, HttpStatus.OK);
        return responseEntity;
    }
    /**
     * 
     * @return
     */

    @GetMapping("/getAll")
    public ResponseEntity<List<User>> getAll() {
        log.info("Fetching all users");
        List<User> users = userService.getAll();
        ResponseEntity<List<User>> responseEntity = new ResponseEntity<>(users, HttpStatus.OK);
        return responseEntity;
    }
    /**
     * 
     * @param id
     * @return
     */

    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id) {
        log.info("Deleting user by ID: {}", id);
        userService.deleteById(id);
        log.info("User deleted successfully");
        return ResponseEntity.status(HttpStatus.OK).body("Deleted successfully");
    }
   /**
    * 
    * @param user
    * @return
    */
    @PutMapping("/updateById")
    public User update(@RequestBody User user) {
        User update = userService.update(user);
        log.info("User updated successfully: {}", update);
        return update;
    }
   /**
    * 
    * @param username
    * @return
    */
    @GetMapping("/byName/{username}")
    public ResponseEntity<List<MedicalHistoryBean>> getMedicalHistoryByName(@PathVariable String username) {
        List<MedicalHistoryBean> historyList = userService.getMedicalHistoryBean(username);
        if (historyList.isEmpty()) {
            log.info("No medical history found for username: {}", username);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            log.info("Medical history fetched successfully for username: {}", username);
            return new ResponseEntity<>(historyList, HttpStatus.OK);
        }
    }
    /**
     * 
     * @param trainerCode
     * @return
     */

    @GetMapping("/getByTrainerCode/{trainerCode}")
    public ResponseEntity<List<User>> getUsersByTrainerCode(@PathVariable String trainerCode) {
        log.info("Fetching users by trainer code: {}", trainerCode);
        List<User> users;
        try {
            users = userService.getUsersByTrainerCode(trainerCode);
            log.info("Users fetched successfully by trainer code: {}", trainerCode);
            return new ResponseEntity<>(users, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error fetching users by trainer code: {}", trainerCode);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
 /**
  * 
  * @param email
  * @param password
  * @return
  */
    @PutMapping("/updatepassword")
    public User updatePassword(@RequestParam String email, @RequestParam String password) {
        log.info("Updating password for user with email: {}", email);
        return userService.updatePassword(email, password);
    }
    
    
//    @PostMapping("/send-reset-email")
//	public ResponseEntity<EmailRequest> sendResetEmail(@RequestBody EmailRequest emailRequest) {
//		System.out.println(emailRequest.getEmail());
//		userService.sendPasswordResetEmail(emailRequest.getEmail());
//		return  new ResponseEntity<EmailRequest>(emailRequest, HttpStatus.OK);
//	}


}
