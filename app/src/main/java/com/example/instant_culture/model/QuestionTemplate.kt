package com.example.instant_culture.model

data class QuizQuestion(
    val question: String,
    val response: Int,
    val proposal: Proposal,
    val descriptionBad: String,
    val descriptionGood: String,
)

data class Proposal(
    val p1: String,
    val p2: String,
    val p3: String,
    val p4: String
)

data class QuizCategory(
    val eazy: List<QuizQuestion>,
    val hard: List<QuizQuestion>,
    val impossible: List<QuizQuestion>
)
