package com.jobportal.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jobportal.dto.BookmarkedFreelancerDTO;
import com.jobportal.dto.BookmarkedFreelancerListDTO;
import com.jobportal.entites.BookmarkedFreelancer;

@Service
public interface IBookmarkedFreelancerService {

	BookmarkedFreelancer bookmarkFreelancer(BookmarkedFreelancerDTO bookmarkedFreelancerDto);

	void deleteBookmarkedFreelancerById(Long id);

	BookmarkedFreelancer findById(Long id);
	
	List<BookmarkedFreelancerListDTO> getAll();

	Long getCurrentId();

	BookmarkedFreelancer save(BookmarkedFreelancerDTO bookmarkedFreelancerDto);

}

