package com.jobportal.service;

import java.util.List;

import com.jobportal.dto.SkillExperienceDTO;
import com.jobportal.dto.SkillExperienceListDTO;
import com.jobportal.entites.SkillExperience;

public interface ISkillExperienceService {

	public SkillExperience addSkill(SkillExperienceDTO skillExperienceDto);

	public List<SkillExperienceListDTO> getSkillByFreelancerId(Long id);
	
	public SkillExperience getSkillById(Long id);
	
	public List<SkillExperience> getByFreelancer(Long id);

	public void updateSkillYears(Long id, Long freelancerId, Integer years);
}
