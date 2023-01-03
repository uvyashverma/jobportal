package com.jobportal.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jobportal.dto.JobDTO;
import com.jobportal.entites.Job;

@Service
public interface IJobService {

	void close(Long id);

	Job findById(Long id);
	
	List<Object> findJobsBySkillName(String name);
	
	List<Object> findAll();
	
	List<Object> findAllActiveJobs();
	
	Job postJob(JobDTO jobDto);
	
	void awardJob(Long jobId, Long freelancerId);
}