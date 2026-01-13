package com.example.nemopedia

import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import android.text.Html
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.nemopedia.data.ArticleRepository
import com.example.nemopedia.databinding.ActivityDetailBinding
import com.example.nemopedia.model.Article
import java.io.File

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private lateinit var article: Article

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        article = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra("ARTICLE", Article::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra("ARTICLE")
        } ?: run {
            finish()
            return
        }

        // BARU: Tambahkan ke recently viewed saat detail dibuka
        ArticleRepository.addToRecentlyViewed(article.id)

        setupUI()
        setupListeners()
    }

    private fun setupUI() {
        binding.apply {
            tvTitle.text = article.title
            tvCategory.text = article.category
            tvSummary.text = article.summary
            tvReadTime.text = getString(R.string.read_time, article.readTimeMinutes)

            tvContent.text = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                Html.fromHtml(article.content, Html.FROM_HTML_MODE_COMPACT)
            } else {
                @Suppress("DEPRECATION")
                Html.fromHtml(article.content)
            }

            updateBookmarkIcon()

            // BARU: Show edit button hanya untuk artikel user-created
            if (article.isUserCreated) {
                btnEdit.visibility = View.VISIBLE
            } else {
                btnEdit.visibility = View.GONE
            }

            if (article.imageUrl.isNotEmpty()) {
                val file = File(article.imageUrl)
                if (file.exists()) {
                    val bitmap = BitmapFactory.decodeFile(file.absolutePath)
                    ivArticleImage.setImageBitmap(bitmap)
                    ivArticleImage.visibility = View.VISIBLE
                } else {
                    ivArticleImage.visibility = View.GONE
                }
            } else {
                ivArticleImage.visibility = View.GONE
            }
        }
    }

    private fun setupListeners() {
        binding.btnBack.setOnClickListener {
            finish()
        }

        binding.btnBookmark.setOnClickListener {
            toggleBookmark()
        }

        // BARU: Edit button (hanya untuk artikel user-created)
        binding.btnEdit.setOnClickListener {
            if (article.isUserCreated) {
                val intent = Intent(this, EditArticleActivity::class.java)
                intent.putExtra("ARTICLE", article)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Artikel bawaan tidak bisa diedit", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun toggleBookmark() {
        article.isBookmarked = !article.isBookmarked
        ArticleRepository.toggleBookmark(article.id)

        updateBookmarkIcon()

        val message = if (article.isBookmarked) {
            getString(R.string.bookmark_added)
        } else {
            getString(R.string.bookmark_removed)
        }
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun updateBookmarkIcon() {
        val iconRes = if (article.isBookmarked) {
            R.drawable.ic_bookmark
        } else {
            R.drawable.ic_bookmark_border
        }
        binding.btnBookmark.setImageResource(iconRes)
    }
}