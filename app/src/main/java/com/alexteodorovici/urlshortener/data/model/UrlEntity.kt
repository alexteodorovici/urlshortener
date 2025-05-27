package com.alexteodorovici.urlshortener.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "url_mappings")
data class UrlEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0L,
    val originalUrl: String,
    val shortCode: String
)