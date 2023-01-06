package com.ad8.data.repository.ad8


import com.ad8.data.api.ApiService
import com.ad8.data.exceptions.NetworkExceptionHandler
import com.ad8.domain.model.ad8.AD8Answer
import com.ad8.domain.model.stroopTest.Register
import com.ad8.domain.requestBody.stroopTest.Ad8AnswerRB
import javax.inject.Inject
import javax.inject.Singleton
import com.ad8.domain.util.Result


@Singleton
class AD8RemoteDataSource @Inject constructor(
    private val apiService: ApiService,
    private val apiExceptionHandler: NetworkExceptionHandler
) : AD8DataSource {
    override suspend fun register(): Result<Register> {
        return try {
            val result = apiService.stroopTestRegisterAsync().await()
            Result.Success(result.toDomain())
        } catch (e: Exception) {
            Result.Error(apiExceptionHandler.traceErrorException(e))
        }
    }

    override suspend fun createAnswer(body: Ad8AnswerRB?): Result<AD8Answer> {
        return try {
            val result = apiService.ad8CreateRecordAsync(body).await()
            Result.Success(result.toDomain())
        } catch (e: Exception) {
            Result.Error(apiExceptionHandler.traceErrorException(e))
        }
    }


}