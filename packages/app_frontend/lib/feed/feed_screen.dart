import 'package:app_frontend/auth/auth_notifier.dart';
import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:app_frontend/feed/feed_providers.dart';
import 'package:app_frontend/feed/question_card.dart';

class FeedScreen extends ConsumerWidget {
  const FeedScreen({super.key});

  @override
  Widget build(BuildContext context, WidgetRef ref) {
    final feedAsyncValue = ref.watch(feedProvider);

    return Scaffold(
      appBar: AppBar(
        title: const Text('SwipePrep Feed'),
        backgroundColor: Colors.transparent,
        elevation: 0,
        actions: [
          IconButton(
            icon: const Icon(Icons.logout),
            onPressed: () {
              ref.read(authProvider.notifier).logout();
            },
          ),
        ],
      ),
      extendBodyBehindAppBar: true,
      body: feedAsyncValue.when(
        data: (questions) {
          if (questions.isEmpty) {
            return const Center(child: Text('No questions found.'));
          }

          return PageView.builder(
            scrollDirection: Axis.vertical,
            itemCount: questions.length,
            itemBuilder: (context, index) {
              final question = questions[index];
              return QuestionCard(question: question);
            },
          );
        },
        loading: () => const Center(child: CircularProgressIndicator()),
        error: (error, stackTrace) => Center(child: Text('Error: $error')),
      ),
    );
  }
}