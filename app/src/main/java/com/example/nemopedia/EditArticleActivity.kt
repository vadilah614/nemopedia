package com.example.nemopedia

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.nemopedia.data.ArticleRepository
import com.example.nemopedia.databinding.ActivityEditArticleBinding
import com.example.nemopedia.model.Article
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream

class EditArticleActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditArticleBinding
    private lateinit var article: Article
    private var selectedImageUri: Uri? = null
    private var savedImagePath: String? = null

    private val pickImageLauncher = registerForActivityResult(
        ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let {
            selectedImageUri = it
            binding.ivPreview.setImageURI(it)
            saveImageToInternalStorage(it)
        }
    }

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            openImagePicker()
        } else {
            Toast.makeText(this, "Permission ditolak. Anda tetap bisa mengupdate artikel tanpa gambar.", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditArticleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        // Get article data from intent
        article = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra("ARTICLE", Article::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra("ARTICLE")
        } ?: run {
            Toast.makeText(this, "Error: Artikel tidak ditemukan", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        // Check if article is user-created
        if (!article.isUserCreated) {
            Toast.makeText(this, "Artikel bawaan tidak bisa diedit", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        setupCategorySpinner()
        prefillForm()
        setupListeners()
    }

    private fun setupCategorySpinner() {
        val categories = ArticleRepository.getCategoriesForSpinner()
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, categories)
        binding.spinnerCategory.adapter = adapter
    }

    private fun prefillForm() {
        binding.apply {
            etTitle.setText(article.title)
            etSummary.setText(article.summary)

            // Convert HTML <br> back to newlines for editing
            val contentText = article.content.replace("<br>", "\n")
            etContent.setText(contentText)

            etReadTime.setText(article.readTimeMinutes.toString())

            // Set category in spinner
            val categories = ArticleRepository.getCategoriesForSpinner()
            val categoryIndex = categories.indexOf(article.category)
            if (categoryIndex >= 0) {
                spinnerCategory.setSelection(categoryIndex)
            }

            // Load existing image
            if (article.imageUrl.isNotEmpty()) {
                val file = File(article.imageUrl)
                if (file.exists()) {
                    val bitmap = BitmapFactory.decodeFile(file.absolutePath)
                    ivPreview.setImageBitmap(bitmap)
                    savedImagePath = article.imageUrl
                }
            }
        }
    }

    private fun setupListeners() {
        binding.btnSelectImage.setOnClickListener {
            checkPermissionAndPickImage()
        }

        binding.btnSave.setOnClickListener {
            updateArticle()
        }

        binding.btnCancel.setOnClickListener {
            finish()
        }
    }

    private fun checkPermissionAndPickImage() {
        when {
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU -> {
                if (ContextCompat.checkSelfPermission(
                        this,
                        Manifest.permission.READ_MEDIA_IMAGES
                    ) == PackageManager.PERMISSION_GRANTED
                ) {
                    openImagePicker()
                } else {
                    requestPermissionLauncher.launch(Manifest.permission.READ_MEDIA_IMAGES)
                }
            }
            else -> {
                if (ContextCompat.checkSelfPermission(
                        this,
                        Manifest.permission.READ_EXTERNAL_STORAGE
                    ) == PackageManager.PERMISSION_GRANTED
                ) {
                    openImagePicker()
                } else {
                    requestPermissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
                }
            }
        }
    }

    private fun openImagePicker() {
        pickImageLauncher.launch("image/*")
    }

    private fun saveImageToInternalStorage(uri: Uri) {
        try {
            val inputStream: InputStream? = contentResolver.openInputStream(uri)
            val bitmap = BitmapFactory.decodeStream(inputStream)

            val directory = File(filesDir, "article_images")
            if (!directory.exists()) {
                directory.mkdirs()
            }

            val filename = "img_${System.currentTimeMillis()}.jpg"
            val file = File(directory, filename)

            val outputStream = FileOutputStream(file)
            bitmap.compress(Bitmap.CompressFormat.JPEG, 80, outputStream)
            outputStream.flush()
            outputStream.close()

            savedImagePath = file.absolutePath

            Toast.makeText(this, "Gambar berhasil diubah", Toast.LENGTH_SHORT).show()

        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(this, "Gagal menyimpan gambar", Toast.LENGTH_SHORT).show()
        }
    }

    private fun updateArticle() {
        val title = binding.etTitle.text.toString().trim()
        val category = binding.spinnerCategory.selectedItem.toString()
        val summary = binding.etSummary.text.toString().trim()
        val content = binding.etContent.text.toString().trim()
        val readTimeStr = binding.etReadTime.text.toString().trim()

        if (title.isEmpty()) {
            Toast.makeText(this, "Judul tidak boleh kosong", Toast.LENGTH_SHORT).show()
            return
        }

        if (summary.isEmpty()) {
            Toast.makeText(this, "Ringkasan tidak boleh kosong", Toast.LENGTH_SHORT).show()
            return
        }

        if (content.isEmpty()) {
            Toast.makeText(this, "Konten tidak boleh kosong", Toast.LENGTH_SHORT).show()
            return
        }

        val readTime = readTimeStr.toIntOrNull() ?: 5

        val updatedArticle = Article(
            id = article.id, // Keep same ID
            title = title,
            category = category,
            summary = summary,
            content = content.replace("\n", "<br>"),
            readTimeMinutes = readTime,
            imageUrl = savedImagePath ?: article.imageUrl, // Keep old image if not changed
            isBookmarked = article.isBookmarked, // Preserve bookmark status
            isUserCreated = true // Must remain true
        )

        val success = ArticleRepository.updateArticle(updatedArticle)

        if (success) {
            Toast.makeText(this, "✓ Artikel berhasil diupdate!", Toast.LENGTH_SHORT).show()
            finish()
        } else {
            Toast.makeText(this, "Gagal mengupdate artikel", Toast.LENGTH_SHORT).show()
        }
    }
}