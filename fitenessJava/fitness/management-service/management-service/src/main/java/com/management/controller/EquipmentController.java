package com.management.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.management.entity.Equipment;
import com.management.service.EquipmentService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(path = "/equipment")
public class EquipmentController {

    @Autowired
    private EquipmentService equipmentService;

    
    /**
     * 
     * @param equipment
     * @return
     */
    @PostMapping("/save")
    public ResponseEntity<Equipment> save(@RequestBody Equipment equipment) {
        equipmentService.save(equipment);
        log.info("Equipment saved successfully: {}", equipment);
        ResponseEntity<Equipment> responseEntity = new ResponseEntity<>(equipment, HttpStatus.CREATED);
        return responseEntity;
    }

    /**
     * 
     * @param equipment
     * @return
     */
    @PutMapping("/updateById")
    public Equipment update(@RequestBody Equipment equipment) {
        Equipment updatedEquipment = equipmentService.update(equipment);
        log.info("Equipment updated successfully: {}", updatedEquipment);
        return updatedEquipment;
    }
    /**
     * 
     * @param id
     * @return
     */

    @GetMapping("/getById/{id}")
    public ResponseEntity<Equipment> getById(@PathVariable Long id) {
        Equipment equipment = equipmentService.getById(id);
        log.info("Equipment fetched successfully for ID: {}", id);
        ResponseEntity<Equipment> responseEntity = new ResponseEntity<>(equipment, HttpStatus.OK);
        return responseEntity;
    }
    /**
     * 
     * @return
     */

    @GetMapping("/getAll")
    public ResponseEntity<List<Equipment>> getAll() {
        List<Equipment> equipments = equipmentService.getAll();
        log.info("List of Equipments fetched successfully: {}", equipments);
        ResponseEntity<List<Equipment>> responseEntities = new ResponseEntity<>(equipments, HttpStatus.OK);
        return responseEntities;
    }
/**
 * 
 * @param id
 * @return
 */
    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<Equipment> delete(@PathVariable Long id) {
        Equipment deletedEquipment = equipmentService.delete(id);
        log.info("Equipment deleted successfully for ID: {}", id);
        ResponseEntity<Equipment> responseEntity = new ResponseEntity<>(deletedEquipment, HttpStatus.OK);
        return responseEntity;
    }
}
	