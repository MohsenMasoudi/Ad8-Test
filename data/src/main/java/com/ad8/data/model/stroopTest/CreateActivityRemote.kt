package com.ad8.data.model.stroopTest

import com.ad8.data.base.ResponseObject
import com.ad8.domain.model.stroopTest.CreateActivity

data class CreateActivityRemote(
    var `data`: Data?,
    var status: Int?
) : ResponseObject<CreateActivity>{
    data class Data(
        var action: String?,
        var created_at: String?,
        var id: Int?,
        var meta: String?,
        var record_id: String?,
        var result: String?,
        var updated_at: String?,
        var user_id: Int?
    ): ResponseObject<CreateActivity.Data> {
        override fun toDomain(): CreateActivity.Data {
            return CreateActivity.Data(action,created_at,id,meta,record_id,result,updated_at,user_id)
        }
    }

    override fun toDomain(): CreateActivity {
        return CreateActivity(data?.toDomain(),status)
    }
}