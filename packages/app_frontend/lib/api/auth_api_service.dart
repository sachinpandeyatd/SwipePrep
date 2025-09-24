import 'package:dio/dio.dart';
import 'api_client.dart';
import 'models/auth_models.dart';

class ApiException implements Exception {
  final String message;
  ApiException(this.message);
}

class AuthApiService {
  final Dio _dio = ApiClient.dio;

  Future<AuthenticationResponse> register(RegisterRequest request) async {
    try {
      final response = await _dio.post(
        '/auth/register',
        data: request.toJson(),
      );

      return AuthenticationResponse.fromJson(response.data);
    } on DioException catch (e) {
      throw ApiException(e.response?.data['message'] ?? 'Registration failed');
    }
  }

  Future<AuthenticationResponse> login(LoginRequest request) async {
    try {
      final response = await _dio.post(
        '/auth/login',
        data: request.toJson(),
      );
      return AuthenticationResponse.fromJson(response.data);
    } on DioException catch (e) {

      throw ApiException(e.response?.data['message'] ?? 'Invalid credentials');
    }
  }
}