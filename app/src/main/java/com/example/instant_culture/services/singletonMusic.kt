import android.content.Context
import android.media.MediaPlayer
import com.example.instant_culture.R

object MusicManager {
    private var mediaPlayer: MediaPlayer? = null
    private var currentPlayingResId: Int? = null
    private var soundEffectPlayer: MediaPlayer? = null

    fun startDefaultMusic(context: Context, musicResId: Int = R.raw.main) {
        if (mediaPlayer?.isPlaying == true) return
        playMusic(context, musicResId)
    }

    fun playSoundFail(context: Context) {
        stopMusic()
        soundEffectPlayer?.release()
        soundEffectPlayer = MediaPlayer.create(context, R.raw.fail)
        soundEffectPlayer?.start()
        soundEffectPlayer?.setOnCompletionListener {
            it.release()
        }
    }

    fun playSoundGood(context: Context) {
        soundEffectPlayer?.release()
        soundEffectPlayer = MediaPlayer.create(context, R.raw.good)
        soundEffectPlayer?.start()
        soundEffectPlayer?.setOnCompletionListener {
            it.release()
        }
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
        currentPlayingResId = null
    }
}

