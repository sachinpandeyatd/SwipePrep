import 'package:dio/dio.dart';

class ApiClient{
  ApiClient._();

  static final Dio dio = Dio(
    BaseOptions(
      baseUrl: 'http://10.75.30.33:8080/api/v1',
      connectTimeout: const Duration(seconds: 5),
      receiveTimeout: const Duration(seconds: 5),
    ),
  );
}