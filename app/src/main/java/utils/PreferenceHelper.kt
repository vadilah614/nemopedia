package com.example.nemopedia.utils

import android.content.Context
import android.content.SharedPreferences

class PreferenceHelper(context: Context) {

    private val prefs: SharedPreferences =
        context.getSharedPreferences("NemopediaPrefs", Context.MODE_PRIVATE)

    companion object {
        private const val KEY_FIRST_LAUNCH = "first_launch"
        private const val KEY_BOOKMARKED_IDS = "bookmarked_ids"
        private const val KEY_RECENTLY_VIEWED = "recently_viewed" // BARU untuk recently viewed
    }

    // Check apakah first launch
    fun isFirstLaunch(): Boolean {
        return prefs.getBoolean(KEY_FIRST_LAUNCH, true)
    }

    // Set first launch selesai
    fun setFirstLaunchComplete() {
        prefs.edit().putBoolean(KEY_FIRST_LAUNCH, false).apply()
    }

    // Simpan bookmarked article IDs
    fun saveBookmarkedIds(ids: Set<Int>) {
        val idsString = ids.joinToString(",")
        prefs.edit().putString(KEY_BOOKMARKED_IDS, idsString).apply()
    }

    // Ambil bookmarked article IDs
    fun getBookmarkedIds(): Set<Int> {
        val idsString = prefs.getString(KEY_BOOKMARKED_IDS, "") ?: ""
        return if (idsString.isEmpty()) {
            emptySet()
        } else {
            idsString.split(",").mapNotNull { it.toIntOrNull() }.toSet()
        }
    }

    // BARU: Simpan recently viewed articles (maksimal 5)
    fun addRecentlyViewed(articleId: Int) {
        val recentIds = getRecentlyViewed().toMutableList()

        // Hapus jika sudah ada (untuk pindah ke depan)
        recentIds.remove(articleId)

        // Tambahkan di posisi pertama
        recentIds.add(0, articleId)

        // Batasi maksimal 5 artikel
        val limitedIds = recentIds.take(5)

        val idsString = limitedIds.joinToString(",")
        prefs.edit().putString(KEY_RECENTLY_VIEWED, idsString).apply()
    }

    // BARU: Ambil recently viewed article IDs
    fun getRecentlyViewed(): List<Int> {
        val idsString = prefs.getString(KEY_RECENTLY_VIEWED, "") ?: ""
        return if (idsString.isEmpty()) {
            emptyList()
        } else {
            idsString.split(",").mapNotNull { it.toIntOrNull() }
        }
    }

    // BARU: Clear recently viewed
    fun clearRecentlyViewed() {
        prefs.edit().remove(KEY_RECENTLY_VIEWED).apply()
    }

    // Kategori FIXED (tidak bisa ditambah lagi)
    fun getCategories(): List<String> {
        return listOf(
            "Sains",
            "Sejarah",
            "Teknologi",
            "Seni",
            "Geografi",
            "Biologi",
            "Pengetahuan Umum"
        )
    }
}