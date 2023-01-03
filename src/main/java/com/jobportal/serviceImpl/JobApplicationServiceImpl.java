package com.jobportal.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jobportal.dao.IFreelancerDao;
import com.jobportal.dao.IJobApplicationDao;
import com.jobportal.dao.IJobDao;
import com.jobportal.dto.JobApplicationDTO;
import com.jobportal.dto.JobApplicationsListDTO;
import com.jobportal.entites.JobApplication;
import com.jobportal.exception.InvalidJobApplicationException;
import com.jobportal.service.IJobApplicationService;

@Service
@Transactional
public class JobApplicationServiceImpl implements IJobApplicationService {

	@Autowired
	IJobApplicationDao jobApplicationDao;

	@Autowired
	IJobDao jobDao;

	@Autowired
	IFreelancerDao freelancerDao;

	@Override
	public JobApplication applyToJob(JobApplicationDTO jobApplicationDto) {
		JobApplication jobApplication = new JobApplication();
		if ((jobApplicationDto.getFreelancerId() != null) || !jobApplicationDto.getCoverLetter().isEmpty()
				|| jobApplicationDto.getJobId() != null) {
			
			jobApplication.setCoverLetter(jobApplicationDto.getCoverLetter());
			jobApplication.setFreelancer(freelancerDao.findById(jobApplicationDto.getFreelancerId()).get());
			jobApplication.setJob(jobDao.findById(jobApplicationDto.getJobId()).get());
			return jobApplicationDao.save(jobApplication);
			
		} else {
			throw new InvalidJobApplicationException();
		}
	}

	@Override
	public List<JobApplicationsListDTO> findAll() {
			return jobApplicationDao.findAllApps();
		
	}

	@Override
	public void remove(Long id) {
		if (jobApplicationDao.existsById(id)) {

			jobApplicationDao.deleteById(id);
		} else {
			throw new InvalidJobApplicationException();
		}

	}

	@Override
	public JobApplication updateJobApplication(Long id, JobApplicationDTO jobApplicationDto) {
		if (jobApplicationDao.existsById(id)) {

			JobApplication jobApplication = jobApplicationDao.findById(id).get();
			jobApplication.setCoverLetter(jobApplicationDto.getCoverLetter());

			jobApplication.setJob(jobDao.findById(jobApplicationDto.getJobId()).get());
			jobApplicationDao.save(jobApplication);

			return jobApplication;
		} else {
			throw new InvalidJobApplicationException();
		}

	}

	@Override
	public List<JobApplicationsListDTO> findAllByJobId(Long jobId) {
		return jobApplicationDao.findAllByJobId(jobId);
	}

	@Override
	public List<JobApplicationsListDTO> findByFreelancerId(Long jobId, Long freelancerId) {
		return jobApplicationDao.findByFreelancerId(jobId, freelancerId);
	}

}
