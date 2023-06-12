package br.com.luishenrique.moviesbrasil.common

import android.os.Bundle
import android.view.View
import androidx.fragment.app.DialogFragment
import br.com.luishenrique.moviesbrasil.R
import br.com.luishenrique.moviesbrasil.details.models.VideoMovie
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView

class DialogYoutubePlayer(
    val videoInfo: VideoMovie
) : DialogFragment(R.layout.dialog_youtube_player) {

    private lateinit var playerView: YouTubePlayerView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        playerView = view.findViewById(R.id.youtube_player_container)

        playerView.addYouTubePlayerListener(
            object : AbstractYouTubePlayerListener() {
                override fun onReady(youTubePlayer: YouTubePlayer) {
                    youTubePlayer.loadVideo(videoInfo.key, START_OF_VIDEO)
                }
            }
        )
    }


    companion object {
        private const val START_OF_VIDEO = 0f
        const val TAG = "DIALOG_YOUTUBE_PLAYER"
    }
}
