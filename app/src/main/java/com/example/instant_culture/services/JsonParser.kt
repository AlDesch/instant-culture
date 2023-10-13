package com.example.instant_culture.services

import android.content.Context
import com.example.instant_culture.model.QuizCategory
import com.example.instant_culture.model.QuizQuestion
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.IOException

class JsonParser {
    fun convertJsonToString(context: Context, fileName: String): String? {
        return try {
            val assets = context.assets
            val jsonString = assets
                .open(fileName)
                .bufferedReader()
                .use {
                    it.readText()
                }
            jsonString
        } catch (error: IOException) {
            println(error.message)
            null
        }
    }

    fun getQuestionsFromJson(context: Context, difficulty: Int): List<QuizQuestion>? {
       // val jsonFile = convertJsonToString(context = context, "question.json")
        val jsonFile = convertJsonToString(context = context, "question_gpt.json")
        if (jsonFile != null) {
            val gson = Gson()
            val type = object : TypeToken<QuizCategory>() {}.type
            val quizCategory: QuizCategory = gson.fromJson(jsonFile, type)

            return when (difficulty) {
                0 -> quizCategory.eazy
                1 -> quizCategory.hard
                2 -> quizCategory.impossible
                else -> null
            }
        }
        return null
    }
}