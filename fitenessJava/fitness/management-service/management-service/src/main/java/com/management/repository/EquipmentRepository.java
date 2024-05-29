package com.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.management.entity.Equipment;

public interface EquipmentRepository extends JpaRepository<Equipment, Long> {

}
