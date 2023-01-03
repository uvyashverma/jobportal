package com.jobportal.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jobportal.dao.IAdminDao;
import com.jobportal.dto.AdminDTO;
import com.jobportal.entites.Admin;
import com.jobportal.exception.InvalidAdminException;
import com.jobportal.service.IAdminService;

@Service
@Transactional
public class AdminServiceImpl implements IAdminService {

	@Autowired
	IAdminDao adminDao;

	@Override
	public Admin findById(Long id) {
		if (adminDao.existsById(id)) {
			return adminDao.findById(id).get();
		} else {
			throw new InvalidAdminException();
		}
	}

	@Override
	public Admin save(AdminDTO adminDto) {
		Admin admin = new Admin();
		String userName = adminDto.getUserName();
		String firstName = adminDto.getFirstName();
		String lastName = adminDto.getLastName();
		String password = adminDto.getPassword();
		if (!(firstName == null || lastName == null || password == null || userName == null)) {
			admin.setUserName(userName);
			admin.setFirstName(firstName);
			admin.setLastName(lastName);
			admin.setPassword(password);
			return adminDao.save(admin);
		} else
			throw new InvalidAdminException();

	}

	@Override
	public Admin update(Long id, AdminDTO adminDto) {
		if (adminDao.existsById(id)) {
			Admin admin = adminDao.findById(id).get();
			admin.setUserName(adminDto.getUserName());
			admin.setPassword(adminDto.getPassword());
			admin.setFirstName(adminDto.getFirstName());
			admin.setLastName(adminDto.getLastName());
			return adminDao.save(admin);
		} else {
			throw new InvalidAdminException();
		}
	}

	@Override
	public Admin findByUserName(String userName) {
		if (adminDao.existsByUserName(userName)) {
			return adminDao.findByUserName(userName);
		} else {
			throw new InvalidAdminException();
		}
	}

}
