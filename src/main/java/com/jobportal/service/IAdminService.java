package com.jobportal.service;

import org.springframework.stereotype.Service;

import com.jobportal.dto.AdminDTO;
import com.jobportal.entites.Admin;

@Service
public interface IAdminService {
	Admin findById(Long id);

	Admin save(AdminDTO adminDto);

	Admin update(Long id, AdminDTO adminDto);

	Admin findByUserName(String userName);

}
