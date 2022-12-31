package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.CarRent;

public interface CarRentalRepo extends JpaRepository<CarRent,Integer>{

}
