package com.example.app.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.example.mycrudapp.databinding.FragmentAddEditCategoryBinding
import com.example.mycrudapp.model.Category
import com.example.mycrudapp.viewmodel.MyViewModel

class AddEditCategoryFragment : DialogFragment() {
    private var _binding: FragmentAddEditCategoryBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MyViewModel by activityViewModels()
    private var category: Category? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddEditCategoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Ambil argumen (jika ada) untuk edit kategori
        category = arguments?.getParcelable("CATEGORY")
        if (category != null) {
            binding.editTextCategoryName.setText(category!!.name)
        }

        // Tombol simpan
        binding.buttonSave.setOnClickListener {
            val categoryName = binding.editTextCategoryName.text.toString().trim()
            if (categoryName.isEmpty()) {
                Toast.makeText(context, "Nama kategori tidak boleh kosong", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (category == null) {
                // Menambahkan kategori baru
                val newCategory = Category(categoryId = 0, name = categoryName)
                viewModel.addCategory(newCategory)
                Toast.makeText(context, "Kategori berhasil ditambahkan", Toast.LENGTH_SHORT).show()
            } else {
                // Mengupdate kategori yang sudah ada
                val updatedCategory = category!!.copy(name = categoryName)
                viewModel.updateCategory(updatedCategory)
                Toast.makeText(context, "Kategori berhasil diperbarui", Toast.LENGTH_SHORT).show()
            }

            dismiss() // Tutup dialog setelah menyimpan
        }

        // Tombol batal
        binding.buttonCancel.setOnClickListener {
            dismiss() // Tutup dialog
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
