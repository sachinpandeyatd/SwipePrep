import 'package:app_frontend/api/models/question_models.dart';
import 'package:app_frontend/api/question_api_service.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';

final questionApiServiceProvider = Provider((ref) => QuestionApiService());

final feedProvider = FutureProvider<List<Question>>((ref){
  final questionService = ref.watch(questionApiServiceProvider);
  return questionService.fetchQuestions();
});