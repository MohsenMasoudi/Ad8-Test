package com.ad8.data.repository.stroopTest

import com.ad8.domain.model.stroopTest.CreateActivity
import com.ad8.domain.model.stroopTest.CreateRecord
import com.ad8.domain.model.stroopTest.Register
import com.ad8.domain.model.stroopTest.UpdateRecord
import com.ad8.domain.repository.StroopTestRepository
import com.ad8.domain.requestBody.stroopTest.CreateActivityRB
import com.ad8.domain.requestBody.stroopTest.UpdateRecordeRB
import com.ad8.domain.util.Result
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StroopTestRepositoryImpl @Inject constructor(private val dataSource: StroopTestDataSource) :
    StroopTestRepository {
    override suspend fun register(): Result<Register> {
        return dataSource.register()
    }

    override suspend fun createRecord(): Result<CreateRecord> {
       return dataSource.createRecord()
    }

    override suspend fun refreshToken(): Result<CreateRecord> {
        return dataSource.refreshToken()
    }

    override suspend fun updateRecord(
        record_id: String?,
        body: UpdateRecordeRB?
    ): Result<UpdateRecord> {
        return dataSource.updateRecord(record_id, body)
    }

    override suspend fun createActivity(body: CreateActivityRB?): Result<CreateActivity> {
       return dataSource.createActivity(body)
    }


}