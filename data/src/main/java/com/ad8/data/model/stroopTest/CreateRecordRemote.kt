package com.ad8.data.model.stroopTest

import com.ad8.data.base.ResponseObject
import com.ad8.domain.model.stroopTest.CreateRecord

data class CreateRecordRemote(
    var `data`: Data?,
    var status: Int?
) : ResponseObject<CreateRecord> {
    data class Data(
        var created_at: String?,
        var id: Int?,
        var updated_at: String?,
        var user_id: Int?
    ): ResponseObject<CreateRecord.Data> {
        override fun toDomain(): CreateRecord.Data {
            return CreateRecord.Data(created_at,id,updated_at,user_id)
        }
    }

    override fun toDomain(): CreateRecord {
        return CreateRecord(data?.toDomain(),status)
    }
}