package com.swipeprep.appbackend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_feedback", uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "question_id"})})
public class UserFeedback {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "question_id", nullable = false)
	private Question question;

	@Enumerated(EnumType.STRING)
	private VoteType vote;

	@Enumerated(EnumType.STRING)
	private ReportReason report;

	@Column(columnDefinition = "TEXT")
	private String reportDetails;

	@Column(nullable = false, updatable = false)
	private OffsetDateTime createdAt;

	@PrePersist
	protected void onCreate() {
		this.createdAt = OffsetDateTime.now();
	}
}

