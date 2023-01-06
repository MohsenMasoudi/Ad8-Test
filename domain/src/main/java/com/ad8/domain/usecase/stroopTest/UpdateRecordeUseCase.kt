package com.ad8.domain.usecase.stroopTest


import com.ad8.domain.model.stroopTest.UpdateRecord
import com.ad8.domain.repository.*
import com.ad8.domain.requestBody.stroopTest.UpdateRecordeRB
import javax.inject.Inject
import javax.inject.Singleton
import com.ad8.domain.util.Result


@Singleton
class UpdateRecordeUseCase @Inject constructor(private val repository: StroopTestRepository) {
    suspend operator fun invoke(record_id: String? ,body: UpdateRecordeRB?): Result<UpdateRecord> =
        repository.updateRecord(record_id, body)
}