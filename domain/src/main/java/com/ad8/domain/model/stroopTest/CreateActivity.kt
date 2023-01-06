package com.ad8.domain.model.stroopTest

data class CreateActivity(
    var `data`: Data?,
    var status: Int?
) {
    data class Data(
        var action: String?,
        var created_at: String?,
        var id: Int?,
        var meta: String?,
        var record_id: String?,
        var result: String?,
        var updated_at: String?,
        var user_id: Int?
    )
}