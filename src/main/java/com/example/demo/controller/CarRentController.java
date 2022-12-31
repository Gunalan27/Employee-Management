package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.exception.ResourceNotFound;
import com.example.demo.model.CarRent;
import com.example.demo.repository.CarRentalRepo;

@Controller
@RequestMapping("red/v1")
public class CarRentController {
	
	@Autowired
	private CarRentalRepo repo;
	
	@PostMapping("/saveCar")
	public CarRent addRent(@RequestBody CarRent car){
		return repo.save(car);
	}
	
	@PutMapping("/editCar/{id}")
	public ResponseEntity<CarRent> editCarByID(@PathVariable int id,@RequestBody CarRent car){
		CarRent newCar=repo.findById(id).orElseThrow(
				()->new ResourceNotFound("Not found from record "+id));
		
		newCar.setCarModel(car.getCarModel());
		newCar.setCarNo(car.getCarNo());
		newCar.setStatus(car.getStatus());
		
		return ResponseEntity.ok(newCar);
	}
	
	@GetMapping("/getCars")
	public List<CarRent> getAll(){
		return repo.findAll();
	}
	
	@DeleteMapping("/deleteCar/{id}")
	public ResponseEntity<String> deleteById(@PathVariable int id){
		CarRent car=repo.findById(id).orElseThrow(
				()->new ResourceNotFound("Not found from record "+id));
		repo.delete(car);
		return ResponseEntity.ok("Successfully deleted car of id "+car.getCardId());
	}
	
	@GetMapping("getCar/{id}")
	public ResponseEntity<CarRent> getCarByID(@PathVariable int id){
		
		CarRent car=repo.findById(id).orElseThrow(
				()->new ResourceNotFound("Not found from record "+id));
		return ResponseEntity.ok(car);
	}
}
