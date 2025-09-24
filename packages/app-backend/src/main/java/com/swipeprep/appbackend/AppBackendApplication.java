package com.swipeprep.appbackend;

import com.swipeprep.appbackend.model.*;
import com.swipeprep.appbackend.repository.QuestionRepository;
import com.swipeprep.appbackend.repository.SubTopicRepository;
import com.swipeprep.appbackend.repository.TagRepository;
import com.swipeprep.appbackend.repository.TopicRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

import java.util.Set;

@SpringBootApplication
@RequiredArgsConstructor
public class AppBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppBackendApplication.class, args);
	}


	private final TopicRepository topicRepository;
	private final SubTopicRepository subTopicRepository;
	private final TagRepository tagRepository;
	private final QuestionRepository questionRepository;

	@Bean
	@Profile("dev")
	CommandLineRunner databaseSeeder() {
		return args -> {
			if (questionRepository.count() > 0) {
				System.out.println("Database already seeded. Skipping.");
				return;
			}

			System.out.println("🚀 Seeding database with initial data...");

			Topic seTopic = new Topic();
			seTopic.setName("Software Engineering");
			seTopic.setSlug("software-engineering");
			topicRepository.save(seTopic);

			SubTopic javaSubTopic = new SubTopic();
			javaSubTopic.setTopic(seTopic);
			javaSubTopic.setName("Java");
			javaSubTopic.setSlug("java");
			subTopicRepository.save(javaSubTopic);

			SubTopic dsaSubTopic = new SubTopic();
			dsaSubTopic.setTopic(seTopic);
			dsaSubTopic.setName("Data Structures & Algorithms");
			dsaSubTopic.setSlug("dsa");
			subTopicRepository.save(dsaSubTopic);

			Tag oopTag = findOrCreateTag("OOP", "oop");
			Tag coreTag = findOrCreateTag("Core Concepts", "core-concepts");
			Tag dsaTag = findOrCreateTag("Data Structures", "data-structures");
			Tag arrayTag = findOrCreateTag("Arrays", "arrays");
			Tag stringTag = findOrCreateTag("Strings", "strings");


			Question q1 = new Question();
			q1.setSubTopic(javaSubTopic);
			q1.setQuestionText("What is Polymorphism in Java?");
			q1.setShortAnswer("Polymorphism is the ability of an object to take on many forms, most commonly when a parent class reference is used to refer to a child class object.");
			q1.setDifficulty(QuestionDifficulty.Easy);
			q1.setStatus(QuestionStatus.published);
			q1.setTags(Set.of(oopTag, coreTag));
			questionRepository.save(q1);


			Question q2 = new Question();
			q2.setSubTopic(dsaSubTopic);
			q2.setQuestionText("What is a Hash Map?");
			q2.setShortAnswer("A Hash Map is a data structure that stores key-value pairs. It uses a hash function to compute an index into an array of buckets or slots, from which the desired value can be found.");
			q2.setDifficulty(QuestionDifficulty.Easy);
			q2.setStatus(QuestionStatus.published);
			q2.setTags(Set.of(dsaTag, coreTag));
			questionRepository.save(q2);

			Question q3 = new Question();
			q3.setSubTopic(dsaSubTopic);
			q3.setQuestionText("How do you reverse a string in place?");
			q3.setShortAnswer("The most common method is using a two-pointer approach. One pointer starts at the beginning of the string and the other at the end. Swap the characters at both pointers, then move them towards the center until they meet or cross.");
			q3.setCodeSnippets("""
                [
                  {
                    "lang": "java",
                    "code": "public void reverseString(char[] s) {\\n  int left = 0, right = s.length - 1;\\n  while (left < right) {\\n    char tmp = s[left];\\n    s[left++] = s[right];\\n    s[right--] = tmp;\\n  }\\n}"
                  }
                ]
                """);
			q3.setDifficulty(QuestionDifficulty.Medium);
			q3.setStatus(QuestionStatus.published);
			q3.setTags(Set.of(dsaTag, stringTag, arrayTag));
			questionRepository.save(q3);

			Question q4 = new Question();
			q4.setSubTopic(javaSubTopic);
			q4.setQuestionText("Explain the difference between final, finally, and finalize in Java.");
			q4.setShortAnswer("'final' is a keyword used for variables, methods, and classes to make them unchangeable. 'finally' is a block used in exception handling to ensure code gets executed. 'finalize' is a method called by the garbage collector before an object is reclaimed.");
			q4.setDifficulty(QuestionDifficulty.Medium);
			q4.setStatus(QuestionStatus.published);
			q4.setTags(Set.of(coreTag));
			questionRepository.save(q4);

			System.out.println("✅ Database seeding complete! Total questions: " + questionRepository.count());
		};
	}

	private Tag findOrCreateTag(String name, String slug) {
		return tagRepository.findByName(name).orElseGet(() -> {
			Tag newTag = new Tag();
			newTag.setName(name);
			newTag.setSlug(slug);
			return tagRepository.save(newTag);
		});
	}
}
