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
	CommandLineRunner databaseSeeder(){
		return args -> {
			if (topicRepository.count() > 0){
				System.out.println("Database already seeded.Skipping.");
				return;
			}

			System.out.println("Seeding DB with initial data...");

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

			Tag oopTag = tagRepository.findByName("OOP").orElseGet(() -> {
				Tag newTag = new Tag();
				newTag.setName("OOP");
				newTag.setSlug("oop");
				return tagRepository.save(newTag);
			});

			Tag coreTag = tagRepository.findByName("Core Concepts").orElseGet(() -> {
				Tag newTag = new Tag();
				newTag.setName("Core Concepts");
				newTag.setSlug("core-concepts");
				return tagRepository.save(newTag);
			});

			Question q1 = new Question();
			q1.setSubTopic(javaSubTopic);
			q1.setQuestionText("What is Polymorphism in Java?");
			q1.setShortAnswer("Polymorphism is the ability of an object to take on many forms. The most common use of polymorphism in OOP occurs when a parent class reference is used to refer to a child class object.");
			q1.setDetailedAnswer("In Java, polymorphism is mainly divided into two types: compile-time polymorphism (method overloading) and runtime polymorphism (method overriding). Method overriding allows a subclass to provide a specific implementation of a method that is already provided by its superclass.");
			q1.setCodeSnippets("""
            [
              {
                "lang": "java",
                "code": "class Animal { void sound() { System.out.println(\\"Animal makes a sound\\"); } }\\nclass Dog extends Animal { void sound() { System.out.println(\\"Dog barks\\"); } }"
              }
            ]
            """);
			q1.setDifficulty(QuestionDifficulty.Easy);
			q1.setStatus(QuestionStatus.published);

			q1.getTags().add(oopTag);
			q1.getTags().add(coreTag);

			questionRepository.save(q1);

			System.out.println("✅ Database seeding complete!");
		};
	}
}
