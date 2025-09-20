package com.swipeprep.appbackend.repository;

import com.swipeprep.appbackend.model.UserFeedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserFeedbackRepository extends JpaRepository<UserFeedback, Long> {
	Optional<UserFeedback> findByUserIdAndQuestionId(UUID userId, UUID questionId);
}
