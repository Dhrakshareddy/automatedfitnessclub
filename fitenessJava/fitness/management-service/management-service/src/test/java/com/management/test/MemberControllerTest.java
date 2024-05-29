package com.management.test;

import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.management.controller.MemberController;
import com.management.entity.Membership;
import com.management.service.MembershipService;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class MemberControllerTest {

	@InjectMocks
	private MemberController memberController;

	@Mock
	private MembershipService membershipService;

	@Test
	public void testSave() {
		Membership membershipToSave = new Membership();

		ResponseEntity<Membership> response = memberController.save(membershipToSave);

		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		assertEquals(membershipToSave, response.getBody());

		verify(membershipService).save(eq(membershipToSave));
	}

	@Test
	public void testGetById() {
		Long membershipId = 1L;
		Membership sampleMembership = new Membership();

		when(membershipService.get(eq(membershipId))).thenReturn(sampleMembership);

		ResponseEntity<Membership> response = memberController.get(membershipId);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(sampleMembership, response.getBody());

		verify(membershipService).get(eq(membershipId));
	}

	@Test
	public void testGetAll() {
		List<Membership> sampleMembershipList = Arrays.asList(/* add sample memberships */);

		when(membershipService.getAll()).thenReturn(sampleMembershipList);

		ResponseEntity<List<Membership>> response = memberController.getAll();

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(sampleMembershipList, response.getBody());

		verify(membershipService).getAll();
	}

	@Test
	public void testDeleteById() {
		Long membershipId = 1L;

		String result = memberController.deleteById(membershipId);

		assertEquals("Deleted successfully", result);

		verify(membershipService).deleteById(eq(membershipId));
	}

	@Test
	public void testUpdate() {
		Membership membershipToUpdate = new Membership();

		Membership updatedMembership = memberController.update(membershipToUpdate);

		verify(membershipService).update(eq(membershipToUpdate));
	}
}
