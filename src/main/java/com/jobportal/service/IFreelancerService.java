package com.jobportal.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Service;

import com.jobportal.dto.FreelancerDTO;
import com.jobportal.dto.FreelancerListDTO;
import com.jobportal.entites.Freelancer;

@Service
public interface IFreelancerService {

	Freelancer findById(Long id);

	Long getCurrentId();

	Freelancer save(FreelancerDTO freelancerDto);

	Freelancer update(@Valid Long id, FreelancerDTO freelancerDto);

	Freelancer findByUserName(String userName);
	
	List<FreelancerListDTO> listFreelancers();
}
