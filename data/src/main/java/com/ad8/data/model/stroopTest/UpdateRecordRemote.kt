package com.ad8.data.model.stroopTest

import com.ad8.data.base.ResponseObject
import com.ad8.domain.model.stroopTest.UpdateRecord

data class UpdateRecordRemote(
    var `data`: Data?,
    var status: Int?
) : ResponseObject<UpdateRecord> {
    data class Data(
        var accuracy: Int?,
        var created_at: String?,
        var id: Int?,
        var speed: Int?,
        var updated_at: String?,
        var user_id: Int?
    ): ResponseObject<UpdateRecord.Data> {
        override fun toDomain(): UpdateRecord.Data {
            return UpdateRecord.Data(accuracy,created_at,id,speed,updated_at,user_id)
        }
    }

    override fun toDomain(): UpdateRecord {
        return UpdateRecord(data?.toDomain(),status)
    }
}