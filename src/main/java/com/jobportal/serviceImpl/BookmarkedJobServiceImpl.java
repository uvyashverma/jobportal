package com.jobportal.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jobportal.dao.IBookmarkedJobDao;
import com.jobportal.dao.IFreelancerDao;
import com.jobportal.dao.IJobDao;
import com.jobportal.dao.ISkillDao;
import com.jobportal.dto.BookmarkedJobDTO;
import com.jobportal.dto.BookmarkedJobListDTO;
import com.jobportal.entites.BookmarkedJob;
import com.jobportal.entites.Skill;
import com.jobportal.exception.InvalidBookmarkedJobException;
import com.jobportal.service.IBookmarkedJobService;

@Service
public class BookmarkedJobServiceImpl implements IBookmarkedJobService {
	@Autowired
	IBookmarkedJobDao bookmarkedjobdao;
	@Autowired
	ISkillDao skilldao;
	@Autowired
	IFreelancerDao freelancerdao;
	@Autowired
	IJobDao jobdao;
	
	
	
	@Transactional
	public BookmarkedJob bookmarkJob(BookmarkedJobDTO bookmarkedjobdto) 
	{
		BookmarkedJob bookmarkedJob=new BookmarkedJob();
	
		if (jobdao.existsById(bookmarkedjobdto.getJobId()) && 
				freelancerdao.existsById(bookmarkedjobdto.getFreelancerId())&&
				skilldao.existsById(bookmarkedjobdto.getSkillId())) {
			bookmarkedJob.setSkill(skilldao.findById(bookmarkedjobdto.getSkillId()).get());
			bookmarkedJob.setFreelancer(freelancerdao.findById(bookmarkedjobdto.getFreelancerId()).get());
			bookmarkedJob.setJob(jobdao.findById(bookmarkedjobdto.getJobId()).get());
		
		return bookmarkedjobdao.save(bookmarkedJob);
		}
		
		else
		{
			throw new InvalidBookmarkedJobException();
		}
	}
	
	@Override
	@Transactional
	public List<BookmarkedJob> findBookmarkedJobsBySkillName(String name) {
		if(skilldao.existsByName(name)) {
			Skill skill = skilldao.findByName(name);
			return bookmarkedjobdao.findBookmarkedJobBySkill(skill);
		}else throw new InvalidBookmarkedJobException();
	}
	
	

	@Transactional
	@Override
	public BookmarkedJob findById(Long id) {
		if(bookmarkedjobdao.existsById(id))
		{
			
		return bookmarkedjobdao.findById(id).get();
		}
		else
		{
			throw new InvalidBookmarkedJobException();
		}
	}

	
	@Override
	public Long getCurrentId() {
		return bookmarkedjobdao.getCurrentSeriesId();
	}
	
	
	@Transactional
	public void remove(Long BId)
	{
		if(bookmarkedjobdao.existsById(BId))
		{
			
		BookmarkedJob bj=bookmarkedjobdao.findById(BId).get();
		bookmarkedjobdao.delete(bj);
		}
		else
		{
			throw new InvalidBookmarkedJobException();
		}
	}
	
	
	@Override
	public List<BookmarkedJobListDTO> findAllBookmarks(Long frId) {
		return bookmarkedjobdao.findAllBookmarks(frId);
	}
	
	
	
	
	
	
	

}
