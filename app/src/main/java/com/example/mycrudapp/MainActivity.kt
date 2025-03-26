package com.example.mycrudapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mycrudapp.R
import com.example.mycrudapp.databinding.ActivityMainBinding
import com.example.mycrudapp.CategoryAdapter
import com.example.mycrudapp.ui.fragments.AddEditCategoryFragment
import com.example.mycrudapp.viewmodel.MyViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MyViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set up RecyclerView
        val categoryAdapter = CategoryAdapter { category ->
            // Handle click for each category
            val intent = Intent(this, DetailActivity::class.java).apply {
                putExtra("CATEGORY_ID", category.categoryId)
            }
            startActivity(intent)
        }
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = categoryAdapter
        }

        // Observe ViewModel
        viewModel.categories.observe(this) { categories ->
            categoryAdapter.submitList(categories)
        }

        // Floating Action Button to add a new category
        binding.fabAdd.setOnClickListener {
            val dialog = AddEditCategoryFragment()
            dialog.show(supportFragmentManager, "AddEditCategory")
        }
    }
}
