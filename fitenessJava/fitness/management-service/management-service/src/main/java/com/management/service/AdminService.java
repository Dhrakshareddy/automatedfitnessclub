package com.management.service;

import com.management.bean.AdminLoginBean;
import com.management.entity.Admin;

public interface AdminService {
	Admin validateLogin(AdminLoginBean adminLoginBean);

	void save(Admin admin);
}
