package com.swipeprep.appbackend.service;

import com.swipeprep.appbackend.dto.QuestionDTO;
import com.swipeprep.appbackend.dto.TagDTO;
import com.swipeprep.appbackend.model.Question;
import com.swipeprep.appbackend.model.QuestionStatus;
import com.swipeprep.appbackend.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuestionService {
	
	private final QuestionRepository questionRepository;
	
	@Transactional(readOnly = true)
	public Page<QuestionDTO> findPublishedQuestions(Pageable pageable){
		Page<Question> questionPage = questionRepository.findByStatus(QuestionStatus.published, pageable);
		
		return questionPage.map(this::convertToDto);
	}

	private QuestionDTO convertToDto(Question question) {
		return new QuestionDTO(
				question.getId(),
				question.getQuestionText(),
				question.getShortAnswer(),
				question.getDetailedAnswer(),
				question.getCodeSnippets(),
				question.getDifficulty(),
				question.getTags().stream()
						.map(tag -> new TagDTO(tag.getName(), tag.getSlug()))
						.collect(Collectors.toSet()),
				question.getSubTopic().getName()
		);
	}
}
