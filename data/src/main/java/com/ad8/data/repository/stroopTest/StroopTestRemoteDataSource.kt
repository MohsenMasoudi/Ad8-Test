package com.ad8.data.repository.stroopTest


import com.ad8.data.api.ApiService
import com.ad8.data.exceptions.NetworkExceptionHandler
import com.ad8.domain.model.stroopTest.CreateActivity
import com.ad8.domain.model.stroopTest.CreateRecord
import com.ad8.domain.model.stroopTest.Register
import com.ad8.domain.model.stroopTest.UpdateRecord
import com.ad8.domain.requestBody.stroopTest.CreateActivityRB
import com.ad8.domain.requestBody.stroopTest.UpdateRecordeRB
import javax.inject.Inject
import javax.inject.Singleton
import com.ad8.domain.util.Result


@Singleton
class StroopTestRemoteDataSource @Inject constructor(
    private val apiService: ApiService,
    private val apiExceptionHandler: NetworkExceptionHandler
) : StroopTestDataSource {
    override suspend fun register(): Result<Register> {
        return try {
            val result = apiService.stroopTestRegisterAsync().await()
            Result.Success(result.toDomain())
        } catch (e: Exception) {
            Result.Error(apiExceptionHandler.traceErrorException(e))
        }
    }

    override suspend fun createRecord(): Result<CreateRecord> {
        return try {
            val result = apiService.stroopTestCreateRecordAsync().await()
            Result.Success(result.toDomain())
        } catch (e: Exception) {
            Result.Error(apiExceptionHandler.traceErrorException(e))
        }
    }

    override suspend fun refreshToken(): Result<CreateRecord> {
        return try {
            val result = apiService.stroopTestCreateRecordAsync().await()
            Result.Success(result.toDomain())
        } catch (e: Exception) {
            Result.Error(apiExceptionHandler.traceErrorException(e))
        }
    }

    override suspend fun updateRecord(
        record_id: String?,
        body: UpdateRecordeRB?
    ): Result<UpdateRecord> {
        return try {
            val result = apiService.stroopTestUpdateRecordAsync(record_id, body).await()
            Result.Success(result.toDomain())
        } catch (e: Exception) {
            Result.Error(apiExceptionHandler.traceErrorException(e))
        }
    }

    override suspend fun createActivity(body: CreateActivityRB?): Result<CreateActivity> {
        return try {
            val result = apiService.stroopTestCreateActivityAsync(body).await()
            Result.Success(result.toDomain())
        } catch (e: Exception) {
            Result.Error(apiExceptionHandler.traceErrorException(e))
        }
    }


}