package com.jobportal.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jobportal.dao.IFeedbackDao;
import com.jobportal.dao.IFreelancerDao;
import com.jobportal.dao.IRecruiterDao;
import com.jobportal.dto.FeedbackDTO;
import com.jobportal.dto.FeedbackListDTO;
import com.jobportal.entites.Feedback;
import com.jobportal.entites.Freelancer;
import com.jobportal.entites.Recruiter;
import com.jobportal.exception.InvalidFeedbackException;
import com.jobportal.service.IFeedbackService;

@Service
public class FeedbackServiceImpl implements IFeedbackService {

	@Autowired
	IFeedbackDao feedbackDao;

	@Autowired
	IRecruiterDao recruiterDao;

	@Autowired
	IFreelancerDao freelancerDao;

	@Override
	public Float averageRating(String id) {
		if (freelancerDao.existsByUserName(id)) {
			return feedbackDao.averageRatings(id);
		}else throw new InvalidFeedbackException();
	}

	@Override
	public Feedback addFeedback(FeedbackDTO feedbackDto) {
		System.out.println(feedbackDto.toString());
		if (recruiterDao.existsByUserName(feedbackDto.getRecruiterUName())
				&& freelancerDao.existsByUserName(feedbackDto.getFreelancerUName())) {
			
			Recruiter recruiter = recruiterDao.findByUserName(feedbackDto.getRecruiterUName());
			Freelancer freelancer = freelancerDao.findByUserName(feedbackDto.getFreelancerUName());
			Feedback feedback = new Feedback();

			feedback.setComment(feedbackDto.getComments());
			feedback.setRanges(feedbackDto.getRanges());
			feedback.setCreatedBy(recruiter);
			feedback.setCreatedFor(freelancer);

			return feedbackDao.save(feedback);
		} else
			throw new InvalidFeedbackException();

	}

	@Override
	public List<FeedbackListDTO> findFeedbacksForFreelancerByRecruiter(String fId, String rId) {

		return feedbackDao.findFeedbacksForFreelancerByRecruiterId(fId, rId);
	}

}

