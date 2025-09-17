package com.swipeprep.appbackend.repository;

import com.swipeprep.appbackend.model.Question;
import com.swipeprep.appbackend.model.QuestionStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface QuestionRepository extends JpaRepository<Question, UUID> {
	Page<Question> findByStatus(QuestionStatus questionStatus, Pageable pageable);
}
