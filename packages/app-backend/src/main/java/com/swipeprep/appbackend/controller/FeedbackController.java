package com.swipeprep.appbackend.controller;

import com.swipeprep.appbackend.dto.FeedbackRequest;
import com.swipeprep.appbackend.model.User;
import com.swipeprep.appbackend.service.FeedbackService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/questions/{questionId}/feedback")
public class FeedbackController {

	private final FeedbackService feedbackService;

	@PostMapping
	public ResponseEntity<Void> submitFeedback(
			@PathVariable UUID questionId,
			@RequestBody FeedbackRequest request,
			@AuthenticationPrincipal User currentUser
	){
		feedbackService.processFeedback(questionId, currentUser, request);
		return ResponseEntity.noContent().build();
	}
}
