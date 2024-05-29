package com.management.service;

import java.util.List;

import com.management.bean.DoctorBean;
import com.management.bean.DoctorLoginBean;
import com.management.bean.LoginBean;
import com.management.entity.Doctor;
import com.management.entity.Trainer;

public interface DoctorService {

	void save(Doctor doctor);

	Doctor get(Long id);

	List<Doctor> getAll();

	Doctor deleteById(Long id);

	Doctor update(Doctor doctor);

	Doctor validateLogin(DoctorLoginBean doctorLoginBean);

	Doctor getByDoctorCode(String doctorCode);

}
