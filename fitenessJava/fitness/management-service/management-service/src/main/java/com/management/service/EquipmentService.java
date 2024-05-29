package com.management.service;

import java.util.List;

import com.management.entity.Equipment;

public interface EquipmentService {

	void save(Equipment equipment);

	Equipment update(Equipment equipment);

	Equipment getById(Long equipmentId);

	List<Equipment> getAll();

	Equipment delete(Long equipmentId);

}
