package dev.kuronosu.deguvon.datasource.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class Anime(
    val flvid: Int,
    val name: String,
    val slug: String,
    val nextEpisodeDate: String,
    val url: String,
    val state: Generic,
    val type: Generic,
    val genres: List<Generic>,
    val otherNames: List<String>,
    val synopsis: String,
    val score: Float,
    val votes: Int,
    val cover: String,
    val banner: String,
    val relations: List<Relation>,
    val episodes: List<Episode>
) : Parcelable
