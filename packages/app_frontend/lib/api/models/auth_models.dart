import 'package:equatable/equatable.dart';

class RegisterRequest extends Equatable{
  final String name;
  final String email;
  final String password;

  const RegisterRequest({
    required this.name,
    required this.email,
    required this.password,
  });

  Map<String, dynamic> toJson() {
    return {
      'name': name,
      'email': email,
      'password': password,
    };
  }

  @override
  List<Object?> get props => [name, email, password];
}

class LoginRequest extends Equatable{
  final String email;
  final String password;

  const LoginRequest({
    required this.email,
    required this.password,
  });

  Map<String, dynamic> toJson() {
    return {
      'email': email,
      'password': password,
    };
  }

  @override
  List<Object?> get props => [email, password];
}

class AuthenticationResponse extends Equatable {
  final String token;

  const AuthenticationResponse({required this.token});

  factory AuthenticationResponse.fromJson(Map<String, dynamic> json) {
    return AuthenticationResponse(
      token: json['token'],
    );
  }

  @override
  List<Object> get props => [token];
}