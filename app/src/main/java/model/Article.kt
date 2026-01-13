package com.example.nemopedia.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Article(
    val id: Int,
    val title: String,
    val category: String,
    val summary: String,
    val content: String,
    val readTimeMinutes: Int,
    var isBookmarked: Boolean = false,
    val imageUrl: String = "",
    val isUserCreated: Boolean = false // BARU: Tandai artikel buatan user
) : Parcelable {

    fun getCategoryColor(): String {
        return when (category) {
            "Sains" -> "#4CAF50"
            "Teknologi" -> "#2196F3"
            "Sejarah" -> "#FF9800"
            "Seni" -> "#E91E63"
            "Geografi" -> "#009688"
            "Biologi" -> "#8BC34A"
            "Pengetahuan Umum" -> "#9C27B0"
            else -> "#757575"
        }
    }
}