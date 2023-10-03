package com.airline.ticketbooking.service;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.airline.ticketbooking.exceptions.PassangerNotFoundException;
import com.airline.ticketbooking.model.Passanger;
import com.airline.ticketbooking.repository.PassangerRepository;


@Service
public class PassangerService {
	

	@Autowired
	private PassangerRepository passangerRepository;
		
    
	public Passanger getPassanger(long id)
	{
		Optional<Passanger> passangers=passangerRepository.findById(id);
		if(passangers.isPresent()) {
			Passanger passanger=passangers.get();
			return passanger;
		}
		else {
			throw new PassangerNotFoundException("Passanger not exist"+id);
		}
	}
	public List<Passanger> getAllPassangers(){
		List<Passanger> passanger=passangerRepository.findAll();
		if(passanger!=null) {
			return passanger;
		}
		else
		{ 
			throw new PassangerNotFoundException("no passanger records found");
		}
	}
	
	public void updatePassanger(Passanger passanger,long id) {
		Optional<Passanger> passangr=passangerRepository.findById(id);
		if(passangr.isPresent()) {
			Passanger passangrs=passangr.get();
			
			passangrs.setFirstName(passanger.getFirstName());
			passangrs.setLastName(passanger.getLastName());
			passangrs.setAddress(passanger.getAddress());
			passangrs.setPassword(passanger.getPassword());
			passangrs.setGender(passanger.getGender());
			passangrs.setPhoneNumber(passanger.getPhoneNumber());
			passangrs.setEmail(passanger.getEmail());
			passangrs.setAge(passanger.getAge());
			passangrs.setPassportNumber(passanger.getPassportNumber());
			passangrs.setNationality(passanger.getNationality());
			
			passangerRepository.save(passangrs);
		}
		else
		{
			throw new PassangerNotFoundException("not found passanger id:"+id);
		}
	}
	public void deletePassanger(long id) {
		Optional<Passanger> passanger=passangerRepository.findById(id);
		if(passanger.isPresent()) {
			Passanger pass=passanger.get();
			passangerRepository.delete(pass);
		}
		else
		{
			throw new PassangerNotFoundException("no records found for this id"+id);
		}
	}
	
	
	/*public PassangerDto addPassanger(PassangerDto pasaangerDto) {
		pasaangerDto.setPassword(passwordencoder.encode(pasaangerDto.getPassword()));
		Passanger passanger = new Passanger();
		mapDtoToEntity(pasaangerDto, passanger);
		Passanger u = passangerRepository.save(passanger);
		return mapEntityToDto(u);
	}

	private void mapDtoToEntity(PassangerDto passangerDto, Passanger passanger) {
		passanger.setName(passangerDto.getName());
		passanger.setGender(passangerDto.getGender());
		passanger.setPhoneNumber(passangerDto.getPhoneNumber());
		passanger.setPassword(passangerDto.getPassword());
		passanger.setEmail(passangerDto.getEmail());
		passanger.setAge(passangerDto.getAge());
		passanger.setPassportNumber(passangerDto.getPassportNumber());
		passanger.setNationality(passangerDto.getNationality());
		if (null == passanger.getRoles()) {
			passanger.setRoles(new HashSet<>());
		}
		passangerDto.getRoles().stream().forEach(role -> {
			Role roles = roleRepository.findByRole(role);
			if (null == roles) {
				roles = new Role();
				roles.setPassanger(new HashSet<>());
			}
			roles.setRole(role);
			passanger.addRoles(roles);
		});
	}

	private PassangerDto mapEntityToDto(Passanger passanger) {
		PassangerDto responseDto = new PassangerDto();
		responseDto.setId(passanger.getId());
		responseDto.setName(passanger.getName());
		responseDto.setGender(passanger.getGender());
		responseDto.setPhoneNumber(passanger.getPhoneNumber());
		responseDto.setPassword(passanger.getPassword());
		responseDto.setEmail(passanger.getEmail());
		responseDto.setAge(passanger.getAge());
		responseDto.setPassportNumber(passanger.getPassportNumber());
		responseDto.setNationality(passanger.getNationality());
		responseDto.setRoles(passanger.getRoles().stream().map(Role::getRole).collect(Collectors.toSet()));
		return responseDto;
	}*/
	 
}
