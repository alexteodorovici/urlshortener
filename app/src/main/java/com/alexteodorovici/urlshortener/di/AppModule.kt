package com.alexteodorovici.urlshortener.di

import android.content.Context
import androidx.room.Room
import com.alexteodorovici.urlshortener.data.dao.UrlDao
import com.alexteodorovici.urlshortener.data.database.UrlDatabase
import com.alexteodorovici.urlshortener.data.repository.UrlRepository
import dagger.Provides
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

object AppModule {

    @Singleton
    @Provides
    fun provideUrlDatabase(@ApplicationContext context: Context): UrlDatabase {
        return Room.databaseBuilder(
            context = context,
            UrlDatabase::class.java,
            "url_database"
        ).build()
    }

    @Singleton
    @Provides
    fun provideUrlDao(database: UrlDatabase): UrlDao {
        return database.urlDao()
    }

    @Singleton
    @Provides
    fun provideUrlRepository(dao: UrlDao): UrlRepository {
        return UrlRepository(dao)
    }
}