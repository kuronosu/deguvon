package dev.kuronosu.deguvon.ui.animeDetails

import android.animation.ObjectAnimator
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.navigation.navGraphViewModels
import dev.kuronosu.deguvon.R
import dev.kuronosu.deguvon.databinding.DialogFragmentAnimeDetailsBinding
import dev.kuronosu.deguvon.datasource.model.Anime
import dev.kuronosu.deguvon.utils.load


const val KEY_ANIME = "ANIME"

class AnimeDetailsDialogFragment : DialogFragment() {

    private val viewModel: AnimeDetailsViewModel by navGraphViewModels(R.id.mobile_navigation)

    private var _binding: DialogFragmentAnimeDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.FullScreenDialogStyle)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DialogFragmentAnimeDetailsBinding.inflate(inflater, container, false)
        binding.detailToolbar.navigationIcon?.setTint(Color.WHITE)
        binding.detailToolbar.setTitleTextColor(Color.WHITE)
        binding.detailToolbar.setNavigationOnClickListener { dismiss() }
        val anime = requireArguments().getParcelable<Anime>(KEY_ANIME)!!
        binding.cover.load("https://kuronosu.dev/${anime.cover}")
        binding.toolbarLayout.title = anime.name
        binding.synopsis.visibility = View.GONE
        binding.synopsis2.visibility = View.GONE
        if (anime.synopsis.isNotBlank()) {
            binding.synopsis.text = anime.synopsis
            binding.synopsis2.text = anime.synopsis
            binding.synopsis.visibility = View.VISIBLE
            binding.synopsis2.visibility = View.INVISIBLE
        }
        binding.type.text = anime.type.name
        binding.state.text = anime.state.name
        binding.score.text = anime.score.toString()

        binding.videoCount.text = when (anime.episodes.size) {
            0 -> "Sin videos"
            1 -> "${anime.episodes.size} video"
            else -> "${anime.episodes.size} videos"
        }

        binding.synopsis.setOnClickListener {
            if (binding.synopsis2.lineCount > 3) {
                val nml = if (binding.synopsis.maxLines == 3) binding.synopsis2.lineCount else 3
                val animation = ObjectAnimator.ofInt(binding.synopsis, "maxLines", nml)
                animation.setDuration(100).start()
            }
        }
        return binding.root
    }
}