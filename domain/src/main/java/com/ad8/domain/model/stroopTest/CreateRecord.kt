package com.ad8.domain.model.stroopTest

data class CreateRecord(
    var `data`: Data?,
    var status: Int?
) {
    data class Data(
        var created_at: String?,
        var id: Int?,
        var updated_at: String?,
        var user_id: Int?
    )
}