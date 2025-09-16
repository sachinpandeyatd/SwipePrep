package com.swipeprep.appbackend.model;

import io.hypersistence.utils.hibernate.type.json.JsonType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
@Table(name = "questions")
public class Question {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sub_topic_id", nullable = false)
	private SubTopic subTopic;

	@Column(nullable = false, columnDefinition = "TEXT")
	private String questionText;

	@Column(nullable = false, columnDefinition = "TEXT")
	private String shortAnswer;

	@Column(nullable = false)
	private String detailedAnswer;

	@Type(JsonType.class)
	@Column(columnDefinition = "jsonb")
	private String codeSnippets;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false, columnDefinition = "question_difficulty")
	private QuestionDifficulty difficulty;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false, columnDefinition = "question_status")
	private QuestionStatus status = QuestionStatus.draft;

	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinTable(
			name = "question_tags",
			joinColumns = @JoinColumn(name = "question_id"),
			inverseJoinColumns = @JoinColumn(name = "tag_id")
	)
	private Set<Tag> tags = new HashSet<>();

	@Column(nullable = false, updatable = false)
	private OffsetDateTime createdAt = OffsetDateTime.now();

	@Column(nullable = false)
	private OffsetDateTime updatedAt = OffsetDateTime.now();

	private OffsetDateTime publishedAt;

	@PreUpdate
	protected void onUpdate(){
		updatedAt = OffsetDateTime.now();
	}
}
