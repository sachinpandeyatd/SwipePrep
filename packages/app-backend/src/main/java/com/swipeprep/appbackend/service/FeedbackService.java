package com.swipeprep.appbackend.service;

import com.swipeprep.appbackend.dto.FeedbackRequest;
import com.swipeprep.appbackend.model.Question;
import com.swipeprep.appbackend.model.User;
import com.swipeprep.appbackend.model.UserFeedback;
import com.swipeprep.appbackend.repository.QuestionRepository;
import com.swipeprep.appbackend.repository.UserFeedbackRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FeedbackService {
	private final UserFeedbackRepository feedbackRepository;
	private final QuestionRepository questionRepository;

	@Transactional
	public void processFeedback(UUID questionId, User currentUser, FeedbackRequest request){
		UserFeedback feedback = feedbackRepository.findByUserIdAndQuestionId(currentUser.getId(), questionId)
				.orElseGet(() -> {
					Question question = questionRepository.findById(questionId)
							.orElseThrow(() -> new EntityNotFoundException("Question not found with id: " + questionId));
					return UserFeedback.builder()
							.user(currentUser)
							.question(question)
							.build();
				});

		feedback.setVote(request.getVote());
		feedback.setReport(request.getReport());

		if (request.getReport() == null){
			feedback.setReportDetails(null);
		}else {
			feedback.setReportDetails(request.getReportDetails());
		}

		feedbackRepository.save(feedback);
	}
}
