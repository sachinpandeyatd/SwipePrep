package com.swipeprep.appbackend.dto;

import com.swipeprep.appbackend.model.QuestionDifficulty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionDTO {
    private UUID id;
    private String questionText;
    private String shortAnswer;
    private String detailedAnswer;
    private String codeSnippets;
    private QuestionDifficulty difficulty;
    private Set<TagDTO> tags;
    private String subTopicName;
}