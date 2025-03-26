package com.example.mycrudapp.data

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity
data class Category(
    @PrimaryKey(autoGenerate = true) val categoryId: Int,
    val name: String
)

@Entity(foreignKeys = [ForeignKey(
    entity = Category::class,
    parentColumns = ["categoryId"],
    childColumns = ["categoryId"],
    onDelete = ForeignKey.CASCADE
)])
data class Item(
    @PrimaryKey(autoGenerate = true) val itemId: Int,
    val name: String,
    val categoryId: Int
)
