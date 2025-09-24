import 'package:app_frontend/api/api_client.dart';
import 'package:app_frontend/api/auth_api_service.dart';
import 'package:app_frontend/api/models/question_models.dart';
import 'package:dio/dio.dart';

class QuestionApiService{
  final Dio _dio = ApiClient.dio;

  Future<List<Question>> fetchQuestions({int page = 0, int size = 10}) async{
    try{
      final response = await _dio.get(
        '/questions',
        queryParameters: {'page': page, 'size': size},
      );

      final List<dynamic> questionListJson = response.data['content'];
      return questionListJson.map((json) => Question.fromJson(json)).toList();
    }on DioException catch (e){
      throw ApiException(e.response?.data['message'] ?? 'Failed to questions.');
    }
  }
}