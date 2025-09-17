package com.swipeprep.appbackend.controller;

import com.swipeprep.appbackend.dto.QuestionDTO;
import com.swipeprep.appbackend.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/questions")
@RequiredArgsConstructor
public class QuestionController {

	private final QuestionService questionService;

	@GetMapping
	public ResponseEntity<Page<QuestionDTO>> getQuestions(
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size
	){
		Pageable pageable = PageRequest.of(page, size);
		Page<QuestionDTO> questionPage = questionService.findPublishedQuestions(pageable);

		return ResponseEntity.ok(questionPage);
	}
}
