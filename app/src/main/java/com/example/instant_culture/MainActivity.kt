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
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.example.instant_culture.model.QuizQuestion
import com.example.instant_culture.ui.theme.InstantcultureTheme

class MainActivity : ComponentActivity() {
    private val musicLifecycleObserver = object : LifecycleObserver {

        @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
        fun onBackground() {
            MusicManager.stopMusic()
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
        fun onDestroy() {
            MusicManager.stopMusic()
        }
    }
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
        lifecycle.addObserver(musicLifecycleObserver)
    }
}

fun onBackground() {
    MusicManager.stopMusic()
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    InstantcultureTheme {
        //com.example.instant_culture.ui.compable.ScaffoldComposable(applicationContext)
    }
}