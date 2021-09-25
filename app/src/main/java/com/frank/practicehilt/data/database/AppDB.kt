package com.frank.practicehilt.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.frank.practicehilt.data.database.question.QuestionDao
import com.frank.practicehilt.data.database.question.QuestionEntity


@Database(entities = [QuestionEntity::class],version = 1,exportSchema = false)
abstract class AppDB : RoomDatabase(){

    abstract fun questionDao(): QuestionDao

}