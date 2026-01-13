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
import com.example.nemopedia.databinding.ActivityAddArticleBinding
import com.example.nemopedia.model.Article
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream

class AddArticleActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddArticleBinding
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
            Toast.makeText(this, "Permission ditolak. Anda tetap bisa menambah artikel tanpa gambar.", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddArticleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        setupCategorySpinner()
        setupListeners()
    }

    private fun setupCategorySpinner() {
        val categories = ArticleRepository.getCategoriesForSpinner()
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, categories)
        binding.spinnerCategory.adapter = adapter
    }

    private fun setupListeners() {
        binding.btnSelectImage.setOnClickListener {
            checkPermissionAndPickImage()
        }

        binding.btnSave.setOnClickListener {
            saveArticle()
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

            Toast.makeText(this, "Gambar dipilih", Toast.LENGTH_SHORT).show()

        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(this, "Gagal menyimpan gambar", Toast.LENGTH_SHORT).show()
        }
    }

    private fun saveArticle() {
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

        val newArticle = Article(
            id = System.currentTimeMillis().toInt(),
            title = title,
            category = category,
            summary = summary,
            content = content.replace("\n", "<br>"),
            readTimeMinutes = readTime,
            imageUrl = savedImagePath ?: "", // OPSIONAL: Empty string jika tidak ada gambar
            isUserCreated = true // PENTING: Artikel buatan user
        )

        ArticleRepository.addArticle(newArticle)

        Toast.makeText(this, "✓ Artikel berhasil ditambahkan!", Toast.LENGTH_SHORT).show()
        finish()
    }
}