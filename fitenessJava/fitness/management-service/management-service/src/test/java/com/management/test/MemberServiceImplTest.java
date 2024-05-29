package com.management.test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.management.entity.Membership;
import com.management.exception.ResourceNotFoundException;
import com.management.repository.MembershipRepository;
import com.management.serviceImpl.MembershipServiceImpl;

class MemberServiceImplTest {

    @Mock
    private MembershipRepository membershipRepository;

    @InjectMocks
    private MembershipServiceImpl membershipService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSave() {
        Membership membership = new Membership();
        membershipService.save(membership);
        verify(membershipRepository, times(1)).save(membership);
    }

    @Test
    void testGetAll() {
        List<Membership> membershipList = new ArrayList<>();
        when(membershipRepository.findAll()).thenReturn(membershipList);

        assertEquals(membershipList, membershipService.getAll());

        verify(membershipRepository, times(1)).findAll();
    }

    @Test
    void testDeleteById() {
        Long id = 1L;
        Membership membership = new Membership();
        membership.setMembershipId(id);

        when(membershipRepository.findById(id)).thenReturn(Optional.of(membership));

        assertEquals(membership, membershipService.deleteById(id));

        verify(membershipRepository, times(1)).findById(id);
        verify(membershipRepository, times(1)).deleteById(id);
    }

    @Test
    void testDeleteByIdNotFound() {
        Long id = 1L;

        when(membershipRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> membershipService.deleteById(id));

        verify(membershipRepository, times(1)).findById(id);
        verify(membershipRepository, never()).deleteById(id);
    }

    @Test
    void testUpdate() {
        Membership membership = new Membership();
        membership.setMembershipId(1L);

        when(membershipRepository.findById(membership.getMembershipId())).thenReturn(Optional.of(membership));

        assertEquals(membership, membershipService.update(membership));

        verify(membershipRepository, times(1)).findById(membership.getMembershipId());
        verify(membershipRepository, times(1)).save(membership);
    }

    @Test
    void testUpdateNotFound() {
        Membership membership = new Membership();
        membership.setMembershipId(1L);

        when(membershipRepository.findById(membership.getMembershipId())).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> membershipService.update(membership));

        verify(membershipRepository, times(1)).findById(membership.getMembershipId());
        verify(membershipRepository, never()).save(membership);
    }

    @Test
    void testGet() {
        Long id = 1L;
        Membership membership = new Membership();
        membership.setMembershipId(id);

        when(membershipRepository.findById(id)).thenReturn(Optional.of(membership));

        assertEquals(membership, membershipService.get(id));

        verify(membershipRepository, times(1)).findById(id);
    }

    @Test
    void testGetNotFound() {
        Long id = 1L;

        when(membershipRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> membershipService.get(id));

        verify(membershipRepository, times(1)).findById(id);
    }
}