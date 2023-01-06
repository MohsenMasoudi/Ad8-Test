package com.ad8.data.repository.ad8

import com.ad8.domain.model.ad8.AD8Answer
import com.ad8.domain.model.stroopTest.Register
import com.ad8.domain.repository.AD8Repository
import com.ad8.domain.requestBody.stroopTest.Ad8AnswerRB
import com.ad8.domain.util.Result
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AD8RepositoryImpl @Inject constructor(private val dataSource: AD8DataSource) :
    AD8Repository {
    override suspend fun register(): Result<Register> {
        return dataSource.register()
    }

    override suspend fun createAnswer(body: Ad8AnswerRB?): Result<AD8Answer> {
        return dataSource.createAnswer(body)
    }


}