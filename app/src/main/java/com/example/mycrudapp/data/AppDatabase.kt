package com.example.mycrudapp.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Category::class, Item::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun myDao(): MyDao
}
