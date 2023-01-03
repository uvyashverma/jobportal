package com.jobportal.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jobportal.dto.SkillDTO;
import com.jobportal.entites.Skill;
import com.jobportal.exception.DuplicateSkillException;
import com.jobportal.exception.InvalidSkillException;
import com.jobportal.exception.JobPortalValidationException;
import com.jobportal.service.ISkillService;

@RestController
@RequestMapping("/skills")
@CrossOrigin(origins = "*")
public class SkillController {
	@Autowired
	ISkillService skillservice;

	@DeleteMapping(value = "/remove/{id}")
	public ResponseEntity<Object> deleteSkill(@PathVariable Long id) {
		try {
			skillservice.remove(id);
			return new ResponseEntity<>("Deleted Skill Successfully.", HttpStatus.OK);
		} catch (InvalidSkillException e) {
			throw new InvalidSkillException("Cannot find skill with given id.");
		}
	}

	@GetMapping(value = "/getAll")
	public List<Skill> getAllSkills() {
		return skillservice.getAllSkills();
	}

	@PostMapping("/add")
	public ResponseEntity<Object> saveSkill(@Valid @RequestBody SkillDTO skillDto, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			System.out.println("Some errors exist!");
			List<FieldError> fieldErrors = bindingResult.getFieldErrors();

			List<String> errMessages = new ArrayList<>();
			for (FieldError fe : fieldErrors) {
				errMessages.add(fe.getDefaultMessage());
			}
			throw new JobPortalValidationException(errMessages);
		}
		try {
			skillservice.save(skillDto);
		} catch (DuplicateSkillException exception) {
			throw new DuplicateSkillException("Skill already exists.");
		} catch (InvalidSkillException exception) {
			throw new InvalidSkillException("One or more entered fields is invalid.");
		}
		return new ResponseEntity<>("Skill Saved.", HttpStatus.OK);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<Object> updateSkill(@PathVariable Long id, @RequestBody Skill skill) {
		try {
			skillservice.update(id, skill);
			return new ResponseEntity<>("Updated records successfully", HttpStatus.OK);
		} catch (InvalidSkillException e) {
			throw new InvalidSkillException("Cannot find skill with given id");
		}
	}
}
