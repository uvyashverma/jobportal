package com.jobportal.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jobportal.dao.IBookmarkedFreelancerDao;
import com.jobportal.dao.IFreelancerDao;
import com.jobportal.dao.IRecruiterDao;
import com.jobportal.dao.ISkillDao;
import com.jobportal.dto.BookmarkedFreelancerDTO;
import com.jobportal.dto.BookmarkedFreelancerListDTO;
import com.jobportal.entites.BookmarkedFreelancer;
import com.jobportal.exception.InvalidBookmarkedFreelancerException;
import com.jobportal.service.IBookmarkedFreelancerService;

@Service
@Transactional
public class BookmarkedFreelancerServiceImpl implements IBookmarkedFreelancerService {

	@Autowired
	IBookmarkedFreelancerDao bookmarkedFreelancerDao;

	@Autowired
	ISkillDao skillDao;

	@Autowired
	IFreelancerDao freelancerDao;

	@Autowired
	IRecruiterDao recruiterDao;

	
	@Override
	public BookmarkedFreelancer bookmarkFreelancer(BookmarkedFreelancerDTO bookmarkedFreelancerDto) {

		BookmarkedFreelancer bookmarkedFreelancer = new BookmarkedFreelancer();

		if (recruiterDao.existsById(bookmarkedFreelancerDto.getRecruiterId())
				&& freelancerDao.existsById(bookmarkedFreelancerDto.getFreelancerId())) {

			bookmarkedFreelancer.setBookmarkedBy(recruiterDao.findById(bookmarkedFreelancerDto.getRecruiterId()).get());
			bookmarkedFreelancer.setFreelancer(freelancerDao.findById(bookmarkedFreelancerDto.getFreelancerId()).get());

			return bookmarkedFreelancerDao.save(bookmarkedFreelancer);
		} else
			throw new InvalidBookmarkedFreelancerException();

	}

	
	@Override
	public void deleteBookmarkedFreelancerById(Long id) {
		if (bookmarkedFreelancerDao.existsById(id)) {
			bookmarkedFreelancerDao.deleteById(id);
		} else {
			throw new InvalidBookmarkedFreelancerException();
		}

	}

	
	
	@Override
	public BookmarkedFreelancer findById(Long id) {
		if (bookmarkedFreelancerDao.existsById(id)) {
			return bookmarkedFreelancerDao.findById(id).get();
		} else
			throw new InvalidBookmarkedFreelancerException();

	}

	
	@Override
	public Long getCurrentId() {
		return bookmarkedFreelancerDao.getCurrentSeriesId();
	}

	@Override
	public BookmarkedFreelancer save(BookmarkedFreelancerDTO bookmarkedFreelancerDto) {
		BookmarkedFreelancer bookmarkedFreelancer = new BookmarkedFreelancer();
		bookmarkedFreelancer.setBookmarkedBy(recruiterDao.findById(bookmarkedFreelancerDto.getRecruiterId()).get());
		bookmarkedFreelancer.setFreelancer(freelancerDao.findById(bookmarkedFreelancerDto.getFreelancerId()).get());
		return bookmarkedFreelancerDao.save(bookmarkedFreelancer);
	}

	@Override
	public List<BookmarkedFreelancerListDTO> getAll() {
		return bookmarkedFreelancerDao.findAllDTO();
	}

}
