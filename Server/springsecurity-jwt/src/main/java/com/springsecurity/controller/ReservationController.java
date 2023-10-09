package com.springsecurity.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springsecurity.entity.Reservation;
import com.springsecurity.exception.PassangerNotFoundException;
import com.springsecurity.service.ReservationService;


@RestController
@RequestMapping("/reservations")
public class ReservationController {
   
	   @Autowired
	   ReservationService reservationService;

	   @PostMapping
	    public String createReservation(@RequestBody Reservation reservation) throws PassangerNotFoundException {
	         reservationService.saveReservation(reservation);
	         return "Added Reservation Successfully";
	   }
	    
	    @GetMapping
	    @PreAuthorize("hasAuthority('ADMIN')")
	    public List<Reservation> getAllReservations() {
	        return reservationService.getAllReservations();
	    }

	    @GetMapping("/{id}")
		@PreAuthorize("hasAuthority('USER')")
	    public Reservation getReservationById(@PathVariable int id) {
	        return reservationService.getReservationById(id);
	    }
	    
	    @PutMapping("/{id}")
	    public Reservation updateReservationById(@PathVariable int id) {
	        return reservationService.updateReservationById(id);
	    }
	    

	   /* @GetMapping("/passenger/{passanger}")
//		@PreAuthorize("hasAuthority('ROLE_USER')")
	    public List<Reservation> getReservationsByPassenger(@PathVariable int passanger) {
	        return reservationService.getReservationsByPassengerId(passanger);
	    }*/						//not yet worked
	    
	    @DeleteMapping("/{id}")
	    @PreAuthorize("hasAuthority('USER')")
	    public String deleteReservation(@PathVariable int id) {
	    	 reservationService.deleteReservation(id);
	    	 return "Successfully Deleted Reservation";
	    }
	
}
