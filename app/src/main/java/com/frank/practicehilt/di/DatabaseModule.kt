package com.frank.practicehilt.di

import android.content.Context
import androidx.room.Room
import com.frank.practicehilt.data.database.AppDB
import com.frank.practicehilt.data.database.question.QuestionDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDB(@ApplicationContext appContext: Context): AppDB {
        return Room.databaseBuilder(appContext, AppDB::class.java, "appdb").build()
    }

    @Provides
    fun provideQuestionDao(appDB: AppDB): QuestionDao {
        return appDB.questionDao()
    }

}