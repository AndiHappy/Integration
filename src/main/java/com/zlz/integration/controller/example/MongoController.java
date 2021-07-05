package com.zlz.integration.controller.example;

import com.zlz.integration.db.mongodb.dao.CustomerRepository;
import com.zlz.integration.db.mongodb.domain.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class MongoController {

	@Autowired
	CustomerRepository customerRepository;

	@GetMapping("/custom")
	public String greeting(
			@RequestParam(name="firstname", required=false, defaultValue="hello") String firstname,
			@RequestParam(name="lastname", required=false, defaultValue="world") String lastname
			) {
		Customer s = new Customer();
		s.firstName=firstname;
		s.lastName=lastname;
		Customer result = customerRepository.insert(s);
		return result.toString();
	}

	@GetMapping("/custom/firstname")
	public String getCustom(@RequestParam(name="firstName", required=true) String firstName) {
		List<Customer> result = customerRepository.findByFirstName(firstName);
		return result.toString();
	}

	@GetMapping("/custom/id")
	public String getCustomById(@RequestParam(name="id", required=true) String id) {
		Optional<Customer> result = customerRepository.findById(id);
		if(result.isPresent()){
			return result.get().toString();
		}
		return "null";
	}


}