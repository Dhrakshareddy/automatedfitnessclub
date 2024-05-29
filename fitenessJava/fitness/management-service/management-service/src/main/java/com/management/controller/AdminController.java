package com.management.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.management.bean.AdminLoginBean;
import com.management.entity.Admin;
import com.management.service.AdminService;

import lombok.extern.slf4j.Slf4j;



@Slf4j
@RestController
@RequestMapping("/admin_profile")
@CrossOrigin(origins = "http://localhost:4200")
public class AdminController {
	@Autowired
    private AdminService adminService;
	
	@PostMapping("/login")
	public ResponseEntity<Admin> login(@RequestBody AdminLoginBean adminLoginBean) {
		Admin admin = adminService.validateLogin(adminLoginBean);

		if (admin != null) {
			log.info("Login successful for admin: {}", admin);
			return ResponseEntity.ok(admin);
		} else {
			log.warn("Login failed for admin");
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
		}
	}
	@PostMapping(path = "/add")
	public ResponseEntity<Admin> save(@RequestBody Admin admin) {
		log.info("Saving doctor: {}", admin);
		adminService.save(admin);
		ResponseEntity<Admin> responseEntity = new ResponseEntity<>(admin, HttpStatus.CREATED);
		log.info("Doctor saved successfully: {}", admin);
		return responseEntity;
	}


}
