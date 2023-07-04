package com.ad8.data.api

import com.ad8.data.model.*
import com.ad8.data.model.ad8.AD8AnswerRemote
import com.ad8.data.model.stroopTest.CreateActivityRemote
import com.ad8.data.model.stroopTest.CreateRecordRemote
import com.ad8.data.model.stroopTest.RegisterRemote
import com.ad8.data.model.stroopTest.UpdateRecordRemote
import com.ad8.domain.requestBody.*
import com.ad8.domain.requestBody.stroopTest.Ad8AnswerRB
import com.ad8.domain.requestBody.stroopTest.CreateActivityRB
import com.ad8.domain.requestBody.stroopTest.UpdateRecordeRB
import kotlinx.coroutines.Deferred
import retrofit2.http.*


interface ApiService {

    @Headers("Content-Type: application/json")
    @POST("api/v1/user/register")
    fun stroopTestRegisterAsync(): Deferred<RegisterRemote>


    @Headers("Content-Type: application/json")
    @POST("api/v1/records")
    fun stroopTestCreateRecordAsync(): Deferred<CreateRecordRemote>



    @Headers("Content-Type: application/json")
    @POST("api/v1/records/{record_id}")
    fun stroopTestUpdateRecordAsync(
        @Path("record_id") record_id: String? ,
        @Body body: UpdateRecordeRB? = null

    ): Deferred<UpdateRecordRemote>





    @Headers("Content-Type: application/json")
    @POST("api/v1/activities")
    fun stroopTestCreateActivityAsync(
        @Body body: CreateActivityRB? = null
    ): Deferred<CreateActivityRemote>

    /**
     * AD8 Application
     */

    @Headers("Content-Type: application/json")
    @POST("api/v1/user/register")
    fun ad8RegisterAsync(): Deferred<RegisterRemote>

    @Headers("Content-Type: application/json")
    @POST("api/v2/records")
    fun ad8CreateRecordAsync(
        @Body body: Ad8AnswerRB?
    ): Deferred<AD8AnswerRemote>


    @Headers("Content-Type: application/json")
    @POST("api/v2/records")
    fun ad8UpdateRecordAsync(
        @Body body: Ad8AnswerRB?
    ): Deferred<AD8AnswerRemote>


    @Headers("Content-Type: application/json")
    @POST("api/v2/records/{record_id}")
    fun GoogleAuthSignInAsync(
        @Body body: SingInRB? = null
    ): Deferred<GoogleSignInRemote>



}



