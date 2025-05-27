package com.alexteodorovici.urlshortener.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.alexteodorovici.urlshortener.data.dao.UrlDao
import com.alexteodorovici.urlshortener.data.model.UrlEntity

@Database(entities = [UrlEntity::class], version = 1, exportSchema = false)
abstract class UrlDatabase: RoomDatabase() {
    abstract fun urlDao(): UrlDao
}