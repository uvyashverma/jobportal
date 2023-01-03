package com.jobportal.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jobportal.dto.FeedbackDTO;
import com.jobportal.dto.FeedbackListDTO;
import com.jobportal.entites.Feedback;

@Service
public interface IFeedbackService {

	Float averageRating(String id);

	Feedback addFeedback(FeedbackDTO feedbackDto);

	List<FeedbackListDTO> findFeedbacksForFreelancerByRecruiter(String fId, String rId);
}
