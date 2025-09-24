import 'package:dio/dio.dart';
import 'package:app_frontend/auth/auth_repository.dart';

class ApiClient{
  ApiClient._();

  static final Dio dio = _createDio();

  static final AuthRepository _authRepository = AuthRepository();

  static Dio _createDio(){
    var dio = Dio(
      BaseOptions(
        baseUrl: 'http://10.75.30.33:8080/api/v1',
        connectTimeout: const Duration(seconds: 5),
        receiveTimeout: const Duration(seconds: 5),
      ),
    );

   dio.interceptors.add(InterceptorsWrapper(
     onRequest: (options, handler) async{
       final token = await _authRepository.getToken();
       if(token != null){
         options.headers['Authorization'] = 'Bearer $token';
       }
       return handler.next(options);
     },
   ));

   return dio;
  }
}