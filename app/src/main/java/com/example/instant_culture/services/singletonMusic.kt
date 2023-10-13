import android.content.Context
import android.media.MediaPlayer
import com.example.instant_culture.R

object MusicManager {
    private var mediaPlayer: MediaPlayer? = null
    private var currentPlayingResId: Int? = null

    fun startDefaultMusic(context: Context, musicResId: Int = R.raw.main) {
        if (mediaPlayer?.isPlaying == true) return
        playMusic(context, musicResId)
    }

    fun playMusic(context: Context, musicResId: Int) {
        if (mediaPlayer?.isPlaying == true && getCurrentPlayingResId() == musicResId) return

        stopMusic()
        mediaPlayer = MediaPlayer.create(context, musicResId)
        mediaPlayer?.isLooping = true
        mediaPlayer?.start()

        currentPlayingResId = musicResId
    }

    fun getCurrentPlayingResId(): Int? {
        return currentPlayingResId
    }

    fun stopMusic() {
        mediaPlayer?.stop()
        mediaPlayer?.release()
        mediaPlayer = null
        currentPlayingResId = null // Réinitialiser l'identifiant de la ressource lorsque la musique est arrêtée
    }
}

