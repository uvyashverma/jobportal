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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jobportal.dto.BookmarkedFreelancerDTO;
import com.jobportal.entites.BookmarkedFreelancer;
import com.jobportal.exception.InvalidBookmarkedFreelancerException;
import com.jobportal.exception.JobPortalValidationException;
import com.jobportal.service.IBookmarkedFreelancerService;

@RestController
@RequestMapping("/bmark/freelancer")
@CrossOrigin(origins = "*")
public class BookmarkedFreelancerController {
	@Autowired
	IBookmarkedFreelancerService bookmarkedFreelancerService;

	
	@PostMapping("/add")
	public ResponseEntity<Object> createBookmark(@Valid @RequestBody BookmarkedFreelancerDTO bookmarkedFreelancerDto,
			BindingResult bindingResult) {

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
			bookmarkedFreelancerService.bookmarkFreelancer(bookmarkedFreelancerDto);
		} catch (InvalidBookmarkedFreelancerException exception) {
			throw new InvalidBookmarkedFreelancerException("One or more entered fields contain invalid objects.");
		}
		return new ResponseEntity<>("Added successfully", HttpStatus.OK);
	}


	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Object> deleteById(@PathVariable Long id) {
		try {
			bookmarkedFreelancerService.deleteBookmarkedFreelancerById(id);
		} catch (InvalidBookmarkedFreelancerException exception) {
			throw new InvalidBookmarkedFreelancerException("No bookmark with specified id exists.");
		}
		return new ResponseEntity<>("Deleted Successfully", HttpStatus.OK);
	}

	
	@GetMapping("/get/id/{id}")
	public ResponseEntity<Object> getById(@Valid @PathVariable Long id) {
		try {
			BookmarkedFreelancer bookmark = bookmarkedFreelancerService.findById(id);
			return new ResponseEntity<>(bookmark, HttpStatus.OK);
		} catch (InvalidBookmarkedFreelancerException exception) {
			throw new InvalidBookmarkedFreelancerException("No Bookmark with specified id.");
		}
	}

	@GetMapping("/getAll")
	public ResponseEntity<Object> getAll() {
		return new ResponseEntity<>(bookmarkedFreelancerService.getAll(), HttpStatus.OK);
	}
}
