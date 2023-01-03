package com.jobportal.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.jobportal.dto.BookmarkedFreelancerListDTO;
import com.jobportal.entites.BookmarkedFreelancer;

@Repository
public interface IBookmarkedFreelancerDao extends JpaRepository<BookmarkedFreelancer, Long> {

	
	@Query(value = "select bkd_fr_seq.currval from dual", nativeQuery = true)
	Long getCurrentSeriesId();

	@Query(value="select new com.jobportal.dto.BookmarkedFreelancerListDTO(bf.id, CONCAT(bf.freelancer.firstName, ' ', bf.freelancer.lastName) as freelancerName, bf.freelancer.userName, CONCAT(bf.bookmarkedBy.firstName, ' ', bf.bookmarkedBy.lastName) as recruiterName, bf.bookmarkedBy.userName) from BookmarkedFreelancer bf order by bf.id")
	List<BookmarkedFreelancerListDTO> findAllDTO(); 
}
