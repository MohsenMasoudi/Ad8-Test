package com.ad8.data.repository.stroopTest


import com.ad8.domain.model.stroopTest.CreateActivity
import com.ad8.domain.model.stroopTest.CreateRecord
import com.ad8.domain.model.stroopTest.Register
import com.ad8.domain.model.stroopTest.UpdateRecord
import com.ad8.domain.requestBody.stroopTest.CreateActivityRB
import com.ad8.domain.requestBody.stroopTest.UpdateRecordeRB
import com.ad8.domain.util.Result

interface StroopTestDataSource {
    suspend fun register(): Result<Register>

    suspend fun createRecord(): Result<CreateRecord>

    suspend fun refreshToken(): Result<CreateRecord>

    suspend fun updateRecord(record_id: String? ,body: UpdateRecordeRB? ): Result<UpdateRecord>

    suspend fun createActivity(body: CreateActivityRB? ): Result<CreateActivity>

}