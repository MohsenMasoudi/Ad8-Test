package com.ad8.data.model.ad8

import com.ad8.data.base.ResponseObject
import com.ad8.domain.model.ad8.AD8Answer

data class AD8AnswerRemote(
    var `data`: Data?,
    var status: Int?
): ResponseObject<AD8Answer> {
    data class Data(
        var answers: String?,
        var created_at: String?,
        var id: Int?,
        var updated_at: String?,
        var user_id: Int?
    ): ResponseObject<AD8Answer.Data> {
        override fun toDomain(): AD8Answer.Data {
            return AD8Answer.Data(answers,created_at,id,updated_at,user_id)
        }
    }

    override fun toDomain(): AD8Answer {
       return AD8Answer(data?.toDomain(),status)
    }
}