package com.jobportal.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jobportal.dto.SkillDTO;
import com.jobportal.entites.Skill;

@Service
public interface ISkillService {

	Skill findById(Long id);

	List<Skill> getAllSkills();

	Long getCurrentId();

	Skill getSkill(Long id);

	String remove(Long id);

	Skill save(Skill skill);

	Skill save(SkillDTO skillDto);

	Skill update(Long id, Skill skill);

}