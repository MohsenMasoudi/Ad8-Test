package com.ad8.data.model


import android.annotation.SuppressLint
import android.os.Parcelable
import com.ad8.data.base.ResponseObject
import com.ad8.domain.model.ErrorMessage
import com.ad8.domain.util.HttpErrors
import kotlinx.parcelize.Parcelize

@SuppressLint("ParcelCreator")
@Parcelize
data class ErrorBody(
    val code: String?,
    val message: String?,
    val path: String?,
    val statusCode: Int?,
    val timestamp: String?
) : Parcelable,ResponseObject<ErrorMessage> {
    override fun toDomain(): ErrorMessage {
        return ErrorMessage(
            message=message,
            status=when(statusCode){
                401-> HttpErrors.Unauthorized
                403-> HttpErrors.Forbidden
                400-> HttpErrors.BadRequest
                500-> HttpErrors.ServerError
                409-> HttpErrors.Conflict
                else-> HttpErrors.NotDefined
            })
    }
}