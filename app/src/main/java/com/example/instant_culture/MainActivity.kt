package com.example.instant_culture

import com.example.instant_culture.ui.compable.ScaffoldComposable
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.instant_culture.model.QuizQuestion
import com.example.instant_culture.ui.theme.InstantcultureTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            InstantcultureTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ScaffoldComposable(applicationContext)
                }
            }
        }
    }
}

@Composable
fun test(questions: List<QuizQuestion>?) {
    Text(questions?.size.toString())
}
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    InstantcultureTheme {
        //com.example.instant_culture.ui.compable.ScaffoldComposable(applicationContext)
    }
}