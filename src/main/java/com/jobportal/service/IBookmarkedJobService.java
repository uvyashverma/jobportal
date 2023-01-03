package com.jobportal.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jobportal.dto.BookmarkedJobDTO;
import com.jobportal.dto.BookmarkedJobListDTO;
import com.jobportal.entites.BookmarkedJob;

@Service
public interface IBookmarkedJobService {

	BookmarkedJob bookmarkJob(BookmarkedJobDTO bjd);

	List<BookmarkedJob> findBookmarkedJobsBySkillName(String name);

	BookmarkedJob findById(Long id);
	
	List<BookmarkedJobListDTO> findAllBookmarks(Long frId);

	Long getCurrentId();
	
	void remove(Long BId);

}
