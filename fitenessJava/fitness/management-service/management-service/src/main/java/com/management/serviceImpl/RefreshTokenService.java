package com.management.serviceImpl;

	import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.management.entity.RefreshToken;
import com.management.repository.AdminRepository;
import com.management.repository.RefreshTokenRepository;
import com.management.repository.UserRepository;

	@Service
	public class RefreshTokenService {

	    @Autowired
	    private RefreshTokenRepository refreshTokenRepository;
	    
	    @Autowired
	    private UserRepository userRepository;
	    
	    @Autowired
	    private AdminRepository adminRepository;

	    public RefreshToken createRefreshToken(String name) {
	        RefreshToken refreshToken = RefreshToken.builder()
	                .user(userRepository.findByName(name).get())
	               .token(UUID.randomUUID().toString())
	                .expiryDate(Instant.now().plusSeconds(604800)) // 7 days (7 * 24 * 60 * 60 seconds)
	                .build();
	        return refreshTokenRepository.save(refreshToken);
	    }


	    public Optional<RefreshToken> findByToken(String token) {
	        return refreshTokenRepository.findByToken(token);
	    }


	    public RefreshToken verifyExpiration(RefreshToken token) {
	        if (token.getExpiryDate().compareTo(Instant.now()) < 0) {
	            refreshTokenRepository.delete(token);
	            throw new RuntimeException(token.getToken() + " Refresh token was expired. Please make a new signin request");
	        }
	        return token;
	    }

	}



