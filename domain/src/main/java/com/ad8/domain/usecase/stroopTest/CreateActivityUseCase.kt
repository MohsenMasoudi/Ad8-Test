package com.ad8.domain.usecase.stroopTest


import com.ad8.domain.model.stroopTest.CreateActivity
import com.ad8.domain.repository.*
import com.ad8.domain.requestBody.stroopTest.CreateActivityRB
import javax.inject.Inject
import javax.inject.Singleton
import com.ad8.domain.util.Result


@Singleton
class CreateActivityUseCase @Inject constructor(private val repository: StroopTestRepository) {
    suspend operator fun invoke(body: CreateActivityRB? ): Result<CreateActivity> =
        repository.createActivity(body)
}