import android.media.MediaPlayer
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.platform.LocalContext
import com.example.instant_culture.R

@Composable
fun PlayMusic() {
    val context = LocalContext.current

    DisposableEffect(Unit) {
        val mediaPlayer: MediaPlayer = MediaPlayer.create(context, R.raw.eazy)
        mediaPlayer.isLooping = true  // Configure le MediaPlayer pour jouer en boucle
        mediaPlayer.start()

        onDispose {
            mediaPlayer.stop()
            mediaPlayer.release()
        }
    }
}
