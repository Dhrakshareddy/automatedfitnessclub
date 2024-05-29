package com.management.serviceImpl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.management.bean.AdminLoginBean;
import com.management.constants.ManagementConstants;
import com.management.entity.Admin;
import com.management.exception.PasswordMismatchException;
import com.management.repository.AdminRepository;
import com.management.service.AdminService;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
public class AdminServiceImpl  implements AdminService{
   

	@Autowired
	   private AdminRepository adminRepository;
	@Override
	public Admin validateLogin(AdminLoginBean adminLoginBean) {
		Admin admin = adminRepository.findByName(adminLoginBean.getName());
		log.info("Doctor found: {}", admin);

		if (admin != null) {
			Admin registrationBean = new Admin();
			if (admin.getPassword().equals(adminLoginBean.getPassword())) {
				log.info("Login successful");
				return admin;
			} else {
				try {
					throw new PasswordMismatchException("Incorrect password");
				} catch (Exception e) {
					log.error(e.getMessage());
				}
				return admin;
			}
		} else {
			try {
				throw new UserNameNotFoundException("Incorrect emailId");
			} catch (Exception e) {
				log.error(e.getMessage());
			}
		}
		return admin;
	}
	
	
	@Override
	public void save(Admin admin) {
		admin.setRole(ManagementConstants.ADMINROLE);
		adminRepository.save(admin);
		log.info("Saved successfully");
	}

}
