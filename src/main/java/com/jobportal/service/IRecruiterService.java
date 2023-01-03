package com.jobportal.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jobportal.dto.RecruiterDTO;
import com.jobportal.dto.RecruiterListDTO;
import com.jobportal.entites.Recruiter;

@Service
public interface IRecruiterService {

	Recruiter findById(Long id);

	Long getCurrentId();

	Recruiter save(RecruiterDTO recruiterdto);

	Recruiter update(Long id, RecruiterDTO recruiterDto);
	
	Recruiter findByUserName(String userName);
	
	List<RecruiterListDTO> findAll();
}
