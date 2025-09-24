import 'package:app_frontend/api/models/question_models.dart';
import 'package:flutter/material.dart';

class QuestionCard extends StatelessWidget{
  final Question question;

  const QuestionCard({super.key, required this.question});

  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: const EdgeInsets.symmetric(horizontal: 16.0, vertical: 80.0),
      child: Card(
        color: Colors.grey[900],
        shape: RoundedRectangleBorder(borderRadius: BorderRadius.circular(20)),
        child: Padding(
          padding: const EdgeInsets.all(24.0),
          child: Column(
            crossAxisAlignment: CrossAxisAlignment.start,
            mainAxisAlignment: MainAxisAlignment.center,
            children: [
              Text(
                question.questionText,
                style: const TextStyle(
                  fontSize: 22,
                  fontWeight: FontWeight.bold,
                  height: 1.3,
                ),
              ),
              const SizedBox(height: 24),
              const Divider(), // A nice separator
              const SizedBox(height: 24),
              Text(
                question.shortAnswer,
                style: TextStyle(
                  fontSize: 18,
                  color: Colors.grey[300],
                  height: 1.5,
                ),
              ),
              const Spacer(),
              Wrap(
                spacing: 8.0,
                runSpacing: 4.0,
                children: question.tags
                    .map((tag) => Chip(label: Text(tag.name)))
                    .toList(),
              )
            ],
          ),
        ),
      ),
    );
  }
}