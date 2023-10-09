package com.springsecurity.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springsecurity.dto.AuthRequest;
import com.springsecurity.entity.Passanger;
import com.springsecurity.exception.PassangerNotFoundException;
import com.springsecurity.repository.PassangersRepository;
import com.springsecurity.service.JwtService;
import com.springsecurity.service.PassangerService;

@RestController
@RequestMapping("/passangers")
public class PassangerController {

	@Autowired
	PassangerService passangerService;

	@Autowired
	PassangersRepository passangerRepository;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

//    @Autowired
//    private CustomUserDetailsService service;
//  
	@Autowired
	private JwtService jwtService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@PostMapping("/signup")
	public String addPassanger(@RequestBody Passanger passanger) {
		String encodedPassword = passwordEncoder.encode(passanger.getPassword());
		passanger.setPassword(encodedPassword);
		passangerRepository.save(passanger);
		return "Signup successfully";
	}

	@GetMapping("/get")
	@PreAuthorize("hasAuthority('ADMIN')")
	public List<Passanger> getAllPassangers() throws PassangerNotFoundException {
		return passangerService.getAllPassengers();
	}

	@PostMapping("/login")
	public String authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));
		if (authentication.isAuthenticated()) {
			return jwtService.generateToken(authRequest.getEmail());
		} else {
			throw new UsernameNotFoundException("invalid user request !");
		}
	}

	@GetMapping("/{id}")
	@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
	public Passanger getPassanger(@PathVariable long id) throws PassangerNotFoundException {
		return passangerService.getPassanger(id);
	}

	@PutMapping("/{id}")
	@PreAuthorize("hasAuthority('USER')")
	public String updatePassanger(@RequestBody Passanger passanger, @PathVariable long id)
			throws PassangerNotFoundException {
		passangerService.updatePassanger(passanger, id);
		return "updated successfully";
	}

	@DeleteMapping("/{id}")
	@PreAuthorize("hasAuthority('USER')")
	public String deletePassanger(@PathVariable long id) throws PassangerNotFoundException {
		passangerService.deletePassanger(id);
		return "Successfully Deleted Passanger record";
	}

//	@GetMapping("/")
//    public List<Details> searchDetails(
//            @RequestParam(required = false) String destination,  // Change variable name from 'name' to 'destination'
//            @RequestParam(required = false) Integer id,          // Change variable name from 'id' to 'source'
//            @RequestParam(required = false) String source,      // Change variable name from 'age' to 'source'
//            @RequestParam(required = false) String date) {      // Change variable name from 'city' to 'date'
//        return dservice.searchDetails(destination, id, source, date);
//    }
	
	
}
