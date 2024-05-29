package com.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.management.entity.Membership;

public interface MembershipRepository extends JpaRepository<Membership, Long>{

}
