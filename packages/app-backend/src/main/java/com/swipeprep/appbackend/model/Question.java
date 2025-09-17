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

	@Column(nullable = false, columnDefinition = "TEXT", length = 1000)
	private String questionText;

	@Column(nullable = false, columnDefinition = "TEXT", length = 1000)
	private String shortAnswer;

	@Column(nullable = false, length = 2000)
	private String detailedAnswer;

	@Type(JsonType.class)
	@Column(columnDefinition = "jsonb")
	private String codeSnippets;


	@Column(nullable = false, length = 255)
	@Enumerated(EnumType.STRING)
	private QuestionDifficulty difficulty;


	@Column(nullable = false, length = 255)
	@Enumerated(EnumType.STRING)
	private QuestionStatus status = QuestionStatus.draft;

	@ManyToMany(cascade = {CascadeType.MERGE})
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
