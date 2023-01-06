package com.ad8.domain.usecase.ad8


import com.ad8.domain.model.ad8.AD8Answer
import com.ad8.domain.repository.*
import com.ad8.domain.requestBody.stroopTest.Ad8AnswerRB
import javax.inject.Inject
import javax.inject.Singleton
import com.ad8.domain.util.Result


@Singleton
class AD8CreateAnswerUseCase @Inject constructor(private val repository: AD8Repository) {
    suspend operator fun invoke(body: Ad8AnswerRB?): Result<AD8Answer> =
        repository.createAnswer(body)
}