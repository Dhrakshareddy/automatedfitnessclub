package com.management.service;

import java.util.List;

import com.management.entity.Doctor;
import com.management.entity.Membership;
import com.management.entity.Trainer;

public interface MembershipService {
	
	void save(Membership membership);
	
	List<Membership> getAll();

	Membership deleteById(Long id);

	Membership update(Membership membership);

	Membership get(Long id);;

}
