import 'package:app_frontend/api/auth_api_service.dart';
import 'package:app_frontend/api/models/auth_models.dart';
import 'package:app_frontend/auth/auth_repository.dart';
import 'package:app_frontend/auth/auth_state.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';

class AuthNotifier extends StateNotifier<AuthState> {
  final AuthApiService _apiService;
  final AuthRepository _authRepository;

  AuthNotifier(this._apiService, this._authRepository) : super(AuthInitial()) {
    checkAuthStatus();
  }

  Future<void> checkAuthStatus() async {
    final token = await _authRepository.getToken();
    if (token != null) {
      state = Authenticated();
    } else {
      state = Unauthenticated();
    }
  }

  Future<void> login(String email, String password) async {
    try {
      state = AuthLoading();
      final request = LoginRequest(email: email, password: password);
      final response = await _apiService.login(request);
      await _authRepository.saveToken(response.token);
      state = Authenticated();
    } on ApiException catch (e) {
      state = AuthError(e.message);
      await Future.delayed(const Duration(milliseconds: 100));
      state = Unauthenticated();
    }
  }

  Future<void> logout() async {
    await _authRepository.deleteToken();
    state = Unauthenticated();
  }
}

final authApiServiceProvider = Provider((ref) => AuthApiService());

final authRepositoryProvider = Provider((ref) => AuthRepository());

final authProvider = StateNotifierProvider<AuthNotifier, AuthState>((ref) {
  final apiService = ref.watch(authApiServiceProvider);
  final authRepository = ref.watch(authRepositoryProvider);
  return AuthNotifier(apiService, authRepository);
});