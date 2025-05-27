package com.alexteodorovici.urlshortener.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.alexteodorovici.urlshortener.data.model.UrlEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UrlDao {

    @Insert(onConflict= OnConflictStrategy.REPLACE)
    suspend fun insertUrl(entity: UrlEntity)

    @Query("SELECT * FROM url_mappings WHERE shortCode = :code LIMIT 1")
    suspend fun getUrlByShortCode(code: String): UrlEntity?

    @Query("SELECT * FROM url_mappings ORDER BY id DESC")
    fun getAllUrls(): Flow<List<UrlEntity>>
}