package com.example.nemopedia

import android.content.Intent
import android.graphics.Rect
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nemopedia.adapter.ArticleAdapter
import com.example.nemopedia.data.ArticleRepository
import com.example.nemopedia.databinding.ActivityMainBinding
import com.example.nemopedia.model.Article
import com.google.android.material.chip.Chip

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var articleAdapter: ArticleAdapter
    private var currentCategory = "Semua"
    private var allArticles = listOf<Article>()
    private var showBookmarksOnly = false
    private var currentSortType = ArticleRepository.SortType.NEWEST

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        ArticleRepository.init(this)

        setupRecyclerView()
        setupCategories()
        setupSearch()
        setupBottomNavigation()
        setupFAB()
        setupSortButton()
        loadArticles()
        setupRecentlyViewed()
    }

    private fun setupRecyclerView() {
        articleAdapter = ArticleAdapter(
            onItemClick = { article ->
                // Tambahkan ke recently viewed sebelum buka detail
                ArticleRepository.addToRecentlyViewed(article.id)

                val intent = Intent(this, DetailActivity::class.java)
                intent.putExtra("ARTICLE", article)
                startActivity(intent)
            },
            onItemLongClick = { article ->
                // BARU: Long press untuk delete
                showDeleteConfirmationDialog(article)
            }
        )

        binding.rvArticles.apply {
            layoutManager = GridLayoutManager(this@MainActivity, 2)
            adapter = articleAdapter
            setHasFixedSize(true)

            val spacing = resources.getDimensionPixelSize(R.dimen.grid_spacing)
            addItemDecoration(GridSpacingItemDecoration(2, spacing, true))
        }
    }

    private fun setupCategories() {
        val categories = ArticleRepository.getAllCategories()

        categories.forEach { category ->
            val chip = Chip(this).apply {
                text = category
                isCheckable = true
                isChecked = category == "Semua"

                val params = android.widget.LinearLayout.LayoutParams(
                    android.widget.LinearLayout.LayoutParams.WRAP_CONTENT,
                    android.widget.LinearLayout.LayoutParams.WRAP_CONTENT
                )
                params.setMargins(0, 0, 16, 0)
                layoutParams = params

                chipBackgroundColor = ContextCompat.getColorStateList(
                    this@MainActivity,
                    R.color.primary_light
                )
                setTextColor(ContextCompat.getColor(this@MainActivity, R.color.primary))
                chipStrokeWidth = 2f
                chipStrokeColor = ContextCompat.getColorStateList(
                    this@MainActivity,
                    R.color.primary
                )

                setOnClickListener {
                    onCategorySelected(category)
                }
            }

            binding.layoutCategories.addView(chip)
        }
    }

    private fun onCategorySelected(category: String) {
        currentCategory = category

        // Update chip selection
        for (i in 0 until binding.layoutCategories.childCount) {
            val view = binding.layoutCategories.getChildAt(i)
            if (view is Chip) {
                view.isChecked = view.text == category
            }
        }

        // Update statistik
        updateStatistics()

        filterArticles()
    }

    private fun setupSearch() {
        binding.etSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                filterArticles()
            }

            override fun afterTextChanged(s: Editable?) {}
        })
    }

    private fun setupBottomNavigation() {
        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    showBookmarksOnly = false
                    loadArticles()
                    true
                }
                R.id.nav_bookmarks -> {
                    showBookmarksOnly = true
                    loadBookmarks()
                    true
                }
                else -> false
            }
        }
    }

    private fun setupFAB() {
        binding.fabAddArticle.setOnClickListener {
            val intent = Intent(this, AddArticleActivity::class.java)
            startActivity(intent)
        }
    }

    // BARU: Setup Sort Button
    private fun setupSortButton() {
        binding.btnSort.setOnClickListener {
            showSortDialog()
        }
    }

    // BARU: Show Sort Dialog
    private fun showSortDialog() {
        val sortOptions = arrayOf(
            "Judul (A-Z)",
            "Judul (Z-A)",
            "Waktu Baca (Tercepat)",
            "Waktu Baca (Terlama)",
            "Terbaru",
            "Terlama"
        )

        AlertDialog.Builder(this)
            .setTitle("Urutkan Artikel")
            .setItems(sortOptions) { _, which ->
                currentSortType = when (which) {
                    0 -> ArticleRepository.SortType.TITLE_ASC
                    1 -> ArticleRepository.SortType.TITLE_DESC
                    2 -> ArticleRepository.SortType.READ_TIME_ASC
                    3 -> ArticleRepository.SortType.READ_TIME_DESC
                    4 -> ArticleRepository.SortType.NEWEST
                    5 -> ArticleRepository.SortType.OLDEST
                    else -> ArticleRepository.SortType.NEWEST
                }

                Toast.makeText(this, "Diurutkan: ${sortOptions[which]}", Toast.LENGTH_SHORT).show()
                filterArticles()
            }
            .show()
    }

    // BARU: Show Delete Confirmation Dialog
    private fun showDeleteConfirmationDialog(article: Article) {
        // Validasi: Hanya artikel user-created yang bisa dihapus
        if (!article.isUserCreated) {
            Toast.makeText(this, "Artikel bawaan tidak bisa dihapus", Toast.LENGTH_SHORT).show()
            return
        }

        AlertDialog.Builder(this)
            .setTitle("Hapus Artikel")
            .setMessage("Apakah Anda yakin ingin menghapus artikel \"${article.title}\"?")
            .setPositiveButton("Hapus") { _, _ ->
                deleteArticle(article)
            }
            .setNegativeButton("Batal", null)
            .show()
    }

    // BARU: Delete Article
    private fun deleteArticle(article: Article) {
        val success = ArticleRepository.deleteArticle(article.id)

        if (success) {
            Toast.makeText(this, "✓ Artikel berhasil dihapus", Toast.LENGTH_SHORT).show()

            // Refresh list
            if (showBookmarksOnly) {
                loadBookmarks()
            } else {
                loadArticles()
            }

            // Update statistik
            updateStatistics()

            // Refresh recently viewed
            setupRecentlyViewed()
        } else {
            Toast.makeText(this, "Gagal menghapus artikel", Toast.LENGTH_SHORT).show()
        }
    }

    // BARU: Setup Recently Viewed
    private fun setupRecentlyViewed() {
        val recentArticles = ArticleRepository.getRecentlyViewedArticles()

        if (recentArticles.isEmpty()) {
            binding.layoutRecentlyViewed.visibility = View.GONE
            return
        }

        binding.layoutRecentlyViewed.visibility = View.VISIBLE
        binding.layoutRecentChips.removeAllViews()

        recentArticles.forEach { article ->
            val chip = Chip(this).apply {
                text = article.title
                isClickable = true

                val params = android.widget.LinearLayout.LayoutParams(
                    android.widget.LinearLayout.LayoutParams.WRAP_CONTENT,
                    android.widget.LinearLayout.LayoutParams.WRAP_CONTENT
                )
                params.setMargins(0, 0, 8, 0)
                layoutParams = params

                setOnClickListener {
                    val intent = Intent(this@MainActivity, DetailActivity::class.java)
                    intent.putExtra("ARTICLE", article)
                    startActivity(intent)
                }
            }

            binding.layoutRecentChips.addView(chip)
        }
    }

    // BARU: Update Statistics
    private fun updateStatistics() {
        val count = ArticleRepository.getArticleCountByCategory(currentCategory)
        binding.tvTotalArticles.text = if (currentCategory == "Semua") {
            "Total: $count artikel"
        } else {
            "$currentCategory: $count artikel"
        }
    }

    private fun loadArticles() {
        allArticles = ArticleRepository.getArticles()
        currentCategory = "Semua"

        // Reset chip selection
        for (i in 0 until binding.layoutCategories.childCount) {
            val view = binding.layoutCategories.getChildAt(i)
            if (view is Chip) {
                view.isChecked = view.text == "Semua"
            }
        }

        updateStatistics()
        filterArticles()
    }

    private fun loadBookmarks() {
        allArticles = ArticleRepository.getBookmarkedArticles()
        updateStatistics()
        filterArticles()
    }

    private fun filterArticles() {
        val searchQuery = binding.etSearch.text.toString()

        var filteredArticles = if (showBookmarksOnly) {
            allArticles
        } else {
            if (currentCategory == "Semua") {
                allArticles
            } else {
                ArticleRepository.filterByCategory(currentCategory)
            }
        }

        // Apply search filter
        if (searchQuery.isNotBlank()) {
            filteredArticles = filteredArticles.filter {
                it.title.contains(searchQuery, ignoreCase = true) ||
                        it.summary.contains(searchQuery, ignoreCase = true) ||
                        it.content.contains(searchQuery, ignoreCase = true)
            }
        }

        // Apply sort
        filteredArticles = when (currentSortType) {
            ArticleRepository.SortType.TITLE_ASC -> filteredArticles.sortedBy { it.title }
            ArticleRepository.SortType.TITLE_DESC -> filteredArticles.sortedByDescending { it.title }
            ArticleRepository.SortType.READ_TIME_ASC -> filteredArticles.sortedBy { it.readTimeMinutes }
            ArticleRepository.SortType.READ_TIME_DESC -> filteredArticles.sortedByDescending { it.readTimeMinutes }
            ArticleRepository.SortType.NEWEST -> filteredArticles.sortedByDescending { it.id }
            ArticleRepository.SortType.OLDEST -> filteredArticles.sortedBy { it.id }
        }

        articleAdapter.submitList(filteredArticles)

        // Show/hide empty state
        if (filteredArticles.isEmpty()) {
            binding.rvArticles.visibility = View.GONE
            binding.layoutEmpty.visibility = View.VISIBLE

            binding.tvEmptyMessage.text = when {
                showBookmarksOnly -> "Belum ada artikel di bookmark"
                searchQuery.isNotBlank() -> getString(R.string.no_search_results, searchQuery)
                else -> getString(R.string.no_articles_found)
            }
        } else {
            binding.rvArticles.visibility = View.VISIBLE
            binding.layoutEmpty.visibility = View.GONE
        }
    }

    override fun onResume() {
        super.onResume()
        if (showBookmarksOnly) {
            loadBookmarks()
        } else {
            loadArticles()
        }
        setupRecentlyViewed()
    }

    class GridSpacingItemDecoration(
        private val spanCount: Int,
        private val spacing: Int,
        private val includeEdge: Boolean
    ) : RecyclerView.ItemDecoration() {

        override fun getItemOffsets(
            outRect: Rect,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State
        ) {
            val position = parent.getChildAdapterPosition(view)
            val column = position % spanCount

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount
                outRect.right = (column + 1) * spacing / spanCount

                if (position < spanCount) {
                    outRect.top = spacing
                }
                outRect.bottom = spacing
            } else {
                outRect.left = column * spacing / spanCount
                outRect.right = spacing - (column + 1) * spacing / spanCount
                if (position >= spanCount) {
                    outRect.top = spacing
                }
            }
        }
    }
}