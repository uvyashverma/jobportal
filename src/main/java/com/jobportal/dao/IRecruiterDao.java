package com.jobportal.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.jobportal.dto.RecruiterListDTO;
import com.jobportal.entites.Recruiter;

@Repository
public interface IRecruiterDao extends JpaRepository<Recruiter, Long> {

	
	@Query(value = "select recruiter_seq.currval from dual", nativeQuery = true)
	Long getCurrentSeriesId();

	public Recruiter findByUserName(String userName);

	public boolean existsByUserName(String userName);

	@Query("select new com.jobportal.dto.RecruiterListDTO(r.id, r.userName, r.firstName, r.lastName, r.password) from Recruiter r")
	public List<RecruiterListDTO> findAllRecruiters();
}
