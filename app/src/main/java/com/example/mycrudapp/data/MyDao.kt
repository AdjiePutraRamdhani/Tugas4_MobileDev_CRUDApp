package com.example.mycrudapp.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface MyDao {
    @Insert
    suspend fun insertCategory(category: Category)

    @Insert
    suspend fun insertItem(item: Item)

    @Query("SELECT * FROM Category")
    fun getAllCategories(): LiveData<List<Category>>

    @Query("SELECT * FROM Item WHERE categoryId = :categoryId")
    fun getItemsByCategory(categoryId: Int): LiveData<List<Item>>

    @Update
    suspend fun updateItem(item: Item)

    @Delete
    suspend fun deleteItem(item: Item)
}
