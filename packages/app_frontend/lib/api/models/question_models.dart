import 'package:equatable/equatable.dart';

class Tag extends Equatable {
  final String name;
  final String slug;

  const Tag({required this.name, required this.slug});

  factory Tag.fromJson(Map<String, dynamic> json) {
    return Tag(
      name: json['name'],
      slug: json['slug'],
    );
  }

  @override
  List<Object?> get props => [name, slug];
}

class Question extends Equatable{
  final String id;
  final String questionText;
  final String shortAnswer;
  final String difficulty;
  final Set<Tag> tags;
  final String subTopicName;

  const Question({
    required this.id,
    required this.questionText,
    required this.shortAnswer,
    required this.difficulty,
    required this.tags,
    required this.subTopicName,
  });

  factory Question.fromJson(Map<String, dynamic> json){
    var tagsList = json['tags'] as List;
    Set<Tag> tagsSet = tagsList.map((tagJson) => Tag.fromJson(tagJson)).toSet();

    return Question(
      id: json['id'],
      questionText: json['questionText'],
      shortAnswer: json['shortAnswer'],
      difficulty: json['difficulty'],
      tags: tagsSet,
      subTopicName: json['subTopicName'],
    );
  }

  @override
  List<Object?> get props => [id, questionText, shortAnswer, difficulty, tags, subTopicName];
}