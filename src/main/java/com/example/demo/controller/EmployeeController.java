package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.exception.ResourceNotFound;
import com.example.demo.model.Employee;

@CrossOrigin(origins="http://localhost:3000")
@RestController
@RequestMapping("/api/v1")
public class EmployeeController {
	
	@Autowired
	private EmployeeRepository emprepo;
	
	//to get the list of all the employees....
	
	@GetMapping("/employees")
	public List<Employee> getAllEmployees(){
		return emprepo.findAll();
	}
	
	//to create the new employee....
	
	@PostMapping("/employees")
	public Employee addEmployee(@RequestBody Employee employee) {
		return emprepo.save(employee);
	}
	
	//to fetch the employee by ID....
	
	@GetMapping("/employees/{id}")
	public ResponseEntity<Employee> getByID(@PathVariable int id) {
		Employee e=emprepo.findById(id)
				.orElseThrow(()-> new ResourceNotFound("The id is invalid "+id));
		return ResponseEntity.ok(e);
	}
	
	//update the record of employee by ID....
	@PutMapping("/employees/{id}")
	public ResponseEntity<Employee> updateByID(@PathVariable int id,@RequestBody Employee emp){
		
		Employee e=emprepo.findById(id)
				.orElseThrow(()-> new ResourceNotFound("The id is invalid "+id));
		e.setFirstname(emp.getFirstname());
		e.setLastname(emp.getLastname());
		e.setEmail(emp.getEmail());
		
		Employee updatedEmp=emprepo.save(e);
		return ResponseEntity.ok(updatedEmp);
		
	}
	
	//Deleting the employee record based on the ID...
	@DeleteMapping("/employees/{id}")
	public ResponseEntity<Map<String,Boolean>> deleteByID(@PathVariable int id){
		Employee e=emprepo.findById(id)
				.orElseThrow(()-> new ResourceNotFound("The id is invalid "+id));
		emprepo.delete(e);
		Map<String,Boolean> response=new HashMap<>();
		response.put("Success", true);
		return ResponseEntity.ok(response);
		
	}
	
}
