import 'package:app_frontend/auth/auth_notifier.dart';
import 'package:app_frontend/auth/auth_state.dart';
import 'package:app_frontend/auth/login_screen.dart';
import 'package:app_frontend/feed/feed_screen.dart';
import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';

class AuthWrapper extends ConsumerWidget {
  const AuthWrapper({super.key});

  @override
  Widget build(BuildContext context, WidgetRef ref) {
    final authState = ref.watch(authProvider);

    if (authState is Authenticated) {
      return const FeedScreen();
    }
    if (authState is Unauthenticated || authState is AuthError) {
      return const LoginScreen();
    }
    return const Scaffold(
      body: Center(child: CircularProgressIndicator()),
    );
  }
}