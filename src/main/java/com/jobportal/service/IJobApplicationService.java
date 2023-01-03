package com.jobportal.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jobportal.dto.JobApplicationDTO;
import com.jobportal.dto.JobApplicationsListDTO;
import com.jobportal.entites.JobApplication;

@Service
public interface IJobApplicationService {

	JobApplication applyToJob(JobApplicationDTO jobApplicationDto);

	List<JobApplicationsListDTO> findAll();

	void remove(Long id);
	
	JobApplication updateJobApplication(Long id,JobApplicationDTO jobApplicationDto);
	
	List<JobApplicationsListDTO> findAllByJobId(Long jobId);
	
	List<JobApplicationsListDTO> findByFreelancerId(Long jobId, Long freelancerId);
}
