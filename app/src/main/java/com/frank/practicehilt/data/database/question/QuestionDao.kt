package com.frank.practicehilt.data.database.question

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface QuestionDao {

    @Insert
    suspend fun insertAll(questions: List<QuestionEntity>)

    @Query("SELECT * FROM question")
    suspend fun getAll(): List<QuestionEntity>

    @Query("DELETE FROM question")
    suspend fun deleteAll( )

}