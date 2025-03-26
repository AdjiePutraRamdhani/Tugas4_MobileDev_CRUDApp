package com.example.mycrudapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.example.mycrudapp.data.AppDatabase
import com.example.mycrudapp.data.Category
import kotlinx.coroutines.launch

class MyViewModel(application: Application) : AndroidViewModel(application) {
    private val dao = Room.databaseBuilder(
        application,
        AppDatabase::class.java,
        "my_database"
    ).build().myDao()

    val categories: LiveData<List<Category>> = dao.getAllCategories()

    fun addCategory(category: Category) = viewModelScope.launch {
        dao.insertCategory(category)
    }
}
