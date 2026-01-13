package com.example.nemopedia.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.nemopedia.databinding.ItemArticleBinding
import com.example.nemopedia.model.Article
import java.io.File

class ArticleAdapter(
    private val onItemClick: (Article) -> Unit,
    private val onItemLongClick: (Article) -> Unit // BARU: Long press listener
) : ListAdapter<Article, ArticleAdapter.ArticleViewHolder>(ArticleDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val binding = ItemArticleBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ArticleViewHolder(binding, onItemClick, onItemLongClick)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ArticleViewHolder(
        private val binding: ItemArticleBinding,
        private val onItemClick: (Article) -> Unit,
        private val onItemLongClick: (Article) -> Unit // BARU
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(article: Article) {
            binding.apply {
                tvTitle.text = article.title
                tvCategory.text = article.category
                tvSummary.text = article.summary
                tvReadTime.text = "${article.readTimeMinutes} menit baca"

                // Set warna badge kategori
                try {
                    val color = Color.parseColor(article.getCategoryColor())
                    tvCategory.setBackgroundColor(color)
                } catch (e: Exception) {
                    tvCategory.setBackgroundColor(Color.parseColor("#757575"))
                }

                // Load gambar
                if (article.imageUrl.isNotEmpty()) {
                    val file = File(article.imageUrl)
                    if (file.exists()) {
                        val bitmap = android.graphics.BitmapFactory.decodeFile(file.absolutePath)
                        ivArticleImage.setImageBitmap(bitmap)
                        imageBackground.visibility = android.view.View.GONE
                    } else {
                        setDefaultImage()
                    }
                } else {
                    setDefaultImage()
                }

                // Click listener
                root.setOnClickListener {
                    onItemClick(article)
                }

                // BARU: Long press listener untuk delete
                root.setOnLongClickListener {
                    onItemLongClick(article)
                    true
                }
            }
        }

        private fun setDefaultImage() {
            binding.apply {
                ivArticleImage.setImageDrawable(null)
                imageBackground.visibility = android.view.View.VISIBLE
            }
        }
    }

    class ArticleDiffCallback : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }
    }
}