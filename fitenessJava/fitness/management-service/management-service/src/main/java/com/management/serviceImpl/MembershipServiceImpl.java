package com.management.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.management.entity.Membership;
import com.management.exception.ResourceNotFoundException;
import com.management.repository.MembershipRepository;
import com.management.service.MembershipService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MembershipServiceImpl implements MembershipService {

	@Autowired
	private MembershipRepository membershipRepository;

	/**
	 * Saves a membership entity.
	 * 
	 * @param membership The membership entity to save.
	 */
	@Override
	public void save(Membership membership) {
		membershipRepository.save(membership);
		log.info("Saved successfully");
	}

	/**
	 * Retrieves all membership entities.
	 * 
	 * @return A list of all membership entities.
	 */

	@Override
	public List<Membership> getAll() {
		log.info("Fetching all memberships");
		return membershipRepository.findAll();
	}

	/**
	 * Deletes a membership entity by ID.
	 * 
	 * @param id The ID of the membership entity to delete.
	 * @return The deleted membership entity.
	 * @throws ResourceNotFoundException If no membership is found with the given
	 *                                   ID.
	 */

	@Override
	public Membership deleteById(Long id) {
		Optional<Membership> optional = membershipRepository.findById(id);
		if (optional.isEmpty()) {
			try {
				optional.orElseThrow(() -> new ResourceNotFoundException(
						"No membership was found to delete for the given ID:" + id));
			} catch (ResourceNotFoundException e) {
				log.error(e.getMessage());
				throw e;
			}
		}
		membershipRepository.deleteById(id);
		log.info("Deleted successfully");
		return optional.get();
	}

	/**
	 * Updates a membership entity.
	 * 
	 * @param membership The membership entity to update.
	 * @return The updated membership entity.
	 * @throws ResourceNotFoundException If no membership is found with the given
	 *                                   ID.
	 */

	@Override
	public Membership update(Membership membership) {
		Optional<Membership> optional = membershipRepository.findById(membership.getMembershipId());
		if (optional.isEmpty()) {
			try {
				optional.orElseThrow(() -> new ResourceNotFoundException(
						"No membership was found to update for the given ID:" + membership.getMembershipType()));
			} catch (ResourceNotFoundException e) {
				log.error(e.getMessage());
				throw e;
			}
		}
		membershipRepository.save(membership);
		log.info("Updated successfully");
		return membership;
	}

	/**
	 * Retrieves a membership entity by ID.
	 * 
	 * @param id The ID of the membership entity to retrieve.
	 * @return The retrieved membership entity.
	 * @throws ResourceNotFoundException If no membership is found with the given
	 *                                   ID.
	 */

	@Override
	public Membership get(Long id) {
		Optional<Membership> optional = membershipRepository.findById(id);
		if (optional.isEmpty()) {
			try {
				optional.orElseThrow(
						() -> new ResourceNotFoundException("No membership was found to fetch for the given ID:" + id));
			} catch (ResourceNotFoundException e) {
				log.error(e.getMessage());
				throw e;
			}
		}
		log.info("Fetched successfully");
		return optional.get();
	}
}
