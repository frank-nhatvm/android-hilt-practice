package com.frank.practicehilt.data.entities

import com.frank.practicehilt.data.database.question.QuestionEntity


fun QuestionEntity.toQuestion(): Question {
    return Question(
        questionId = this.questionId,
        title = this.title
    )
}

fun Question.toQuestionEntity(): QuestionEntity {
    return QuestionEntity(
        questionId = this.questionId,
        title = this.title
    )
}

fun List<QuestionEntity>.toListQuestions(): List<Question> {
    return this.map { it.toQuestion() }
}

fun List<Question>.toListQuestionEntities(): List<QuestionEntity> {
    return this.map { it.toQuestionEntity() }
}
