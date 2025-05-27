package com.alexteodorovici.urlshortener.data.repository

import androidx.compose.ui.text.LinkAnnotation
import com.alexteodorovici.urlshortener.data.dao.UrlDao
import com.alexteodorovici.urlshortener.data.model.UrlEntity
import kotlinx.coroutines.flow.Flow

class UrlRepository (private val dao: UrlDao) {

    suspend fun insertUrl(originalUrl: String, shortcode: String) {
        dao.insertUrl(entity = UrlEntity(originalUrl = originalUrl, shortCode = shortcode))
    }

    suspend fun getUrlByShortCode(shortCode: String): UrlEntity? {
        return dao.getUrlByShortCode(code = shortCode)
    }

    fun getAllUrls(): Flow<List<UrlEntity>> {
        return dao.getAllUrls()
    }
}