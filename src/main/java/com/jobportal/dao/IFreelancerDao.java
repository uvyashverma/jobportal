package com.jobportal.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.jobportal.dto.FreelancerListDTO;
import com.jobportal.entites.Freelancer;

@Repository
public interface IFreelancerDao extends JpaRepository<Freelancer, Long> {

	@Query(value = "select freelancer_seq.currval from dual", nativeQuery = true)
	Long getCurrentSeriesId();

	public Freelancer findByUserName(String userName);

	public boolean existsByUserName(String userName);
	
	@Query(value = "select new com.jobportal.dto.FreelancerListDTO(f.id, f.userName, f.firstName, f.lastName, f.password) from Freelancer f where not f.firstName like 'dummy%' order by f.id")
	public List<FreelancerListDTO> findAllFreelancers(); 
}
