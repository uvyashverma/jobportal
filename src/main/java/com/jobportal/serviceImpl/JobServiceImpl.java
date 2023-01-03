package com.jobportal.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jobportal.dao.IFreelancerDao;
import com.jobportal.dao.IJobDao;
import com.jobportal.dao.IRecruiterDao;
import com.jobportal.dao.ISkillDao;
import com.jobportal.dto.JobDTO;
import com.jobportal.entites.Freelancer;
import com.jobportal.entites.Job;
import com.jobportal.exception.InvalidJobException;
import com.jobportal.service.IJobService;

@Service
public class JobServiceImpl implements IJobService {

	@Autowired
	IJobDao jobdao;

	@Autowired
	ISkillDao skillDao;

	@Autowired
	IFreelancerDao freelancerDao;

	@Autowired
	IRecruiterDao recruiterDao;

	public void close(Long id) {
		if (jobdao.existsById(id)) {
			Job job = jobdao.findById(id).get();
			job.setActive(false);
			jobdao.save(job);
		} else {
			throw new InvalidJobException();
		}
	}

	@Override
	public Job findById(Long id) {

		if (jobdao.existsById(id)) {
			return jobdao.findById(id).get();
		} else
			throw new InvalidJobException();

	}

	@Override

	public List<Object> findJobsBySkillName(String name) {
		if (skillDao.existsByName(name)) {
			return jobdao.findBySkill(name);
		} else {
			throw new InvalidJobException();
		}

	}

	@Override
	public Job postJob(JobDTO jobdto) {
		Job job = new Job();
		if (recruiterDao.existsById(jobdto.getRecruiterId()) && freelancerDao.existsById(jobdto.getFreelancerid())
				&& skillDao.existsById(jobdto.getSkillId())) {
			job.setPostedBy(recruiterDao.findById(jobdto.getRecruiterId()).get());
			job.setAwardedTo(freelancerDao.findById(2L).get());
			job.setSkill(skillDao.findById(jobdto.getSkillId()).get());
			job.setActive(true);
			job.setJobTitle(jobdto.getJobTitle());
			job.setJobDescription(jobdto.getJobDescription());
			return jobdao.save(job);
		} else
			throw new InvalidJobException();
	}

	@Override
	public List<Object> findAll() {
		return jobdao.findAllDTO();
	}

	@Override
	public void awardJob(Long jobId, Long freelancerId) {
		Job job = jobdao.findById(jobId).get();
		Freelancer freelancer = freelancerDao.findById(freelancerId).get();
		job.setAwardedTo(freelancer);
		jobdao.saveAndFlush(job);
		
	}

	@Override
	public List<Object> findAllActiveJobs() {
		return jobdao.findAllActiveDTO();
	}

}
