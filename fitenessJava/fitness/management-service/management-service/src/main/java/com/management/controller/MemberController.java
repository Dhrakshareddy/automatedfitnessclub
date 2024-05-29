package com.management.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.management.entity.Membership;
import com.management.service.MembershipService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/membership")
@CrossOrigin(origins = "http://localhost:4200")
public class MemberController {

	private static final Logger log = LoggerFactory.getLogger(MemberController.class);

	@Autowired
	private MembershipService membershipService;

	/**
	 * 
	 * @param membership
	 * @return
	 */
	@PostMapping("/add")
	public ResponseEntity<Membership> save(@RequestBody Membership membership) {
		membershipService.save(membership);
		log.info("Membership saved successfully: {}", membership);
		ResponseEntity<Membership> responseEntity = new ResponseEntity<>(membership, HttpStatus.CREATED);
		return responseEntity;
	}
	
/**
 * 
 * @param id
 * @return
 */
	@GetMapping("/getById/{id}")
	public ResponseEntity<Membership> get(@PathVariable Long id) {
		log.info("Fetching membership by ID: {}", id);

		Membership membership = membershipService.get(id);

		ResponseEntity<Membership> responseEntity = new ResponseEntity<>(membership, HttpStatus.OK);
		return responseEntity;
	}

	/**
	 * 
	 * @return
	 */
	@GetMapping("/getAll")
	public ResponseEntity<List<Membership>> getAll() {
		log.info("Fetching all memberships");
		List<Membership> memberships = membershipService.getAll();
		ResponseEntity<List<Membership>> responseEntity = new ResponseEntity<>(memberships, HttpStatus.OK);
		return responseEntity;
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	@DeleteMapping("/deleteById/{id}")
	public String deleteById(@PathVariable Long id) {
		log.info("Deleting membership by ID: {}", id);

		membershipService.deleteById(id);
		log.info("Membership deleted successfully");
		return "Deleted successfully";
	}

	/**
	 * 
	 * @param membership
	 * @return
	 */
	@PutMapping("/updateById")
	public Membership update(@RequestBody Membership membership) {
		log.info("Updating membership by ID: {}", membership.getMembershipId());
		Membership update = membershipService.update(membership);
		log.info("Membership updated successfully: {}", update);
		return update;
	}
}
