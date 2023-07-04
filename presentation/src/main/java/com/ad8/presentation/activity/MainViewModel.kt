package com.ad8.presentation.activity

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.work.WorkManager
import com.ad8.domain.model.stroopTest.CreateActivity
import com.ad8.domain.model.stroopTest.CreateRecord
import com.ad8.domain.model.stroopTest.Register
import com.ad8.domain.model.stroopTest.UpdateRecord
import com.ad8.domain.requestBody.stroopTest.CreateActivityRB
import com.ad8.domain.requestBody.stroopTest.UpdateRecordeRB
import com.ad8.domain.usecase.stroopTest.CreateActivityUseCase
import com.ad8.domain.usecase.stroopTest.CreateRecordUseCase
import com.ad8.domain.usecase.stroopTest.RegisterUseCase
import com.ad8.domain.usecase.stroopTest.UpdateRecordeUseCase
import com.ad8.domain.util.Result
import com.ad8.presentation.base.BaseViewModel
import com.ad8.presentation.util.DispatchersProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    private val dispatchers: DispatchersProvider
    ,private val registerUseCase: RegisterUseCase
    ,private val createActivityUseCase: CreateActivityUseCase
    , private val createRecordUseCase: CreateRecordUseCase
    ,private val updateRecordeUseCase: UpdateRecordeUseCase


) : BaseViewModel(dispatchers) {
    private var mWorkManager: WorkManager? = null
    private val _activityRecord: MutableLiveData<Result<CreateActivity>> = MutableLiveData()
    private val _createRecorde: MutableLiveData<Result<CreateRecord>> = MutableLiveData()



    private val _register: MutableLiveData<Result<Register>> = MutableLiveData()

    fun fetchRegister() {
        execute {
            when (val result = registerUseCase()) {
                is Result.Success -> {
                    _register.postValue(result)
                }
                is Result.Error -> {
                    _register.postValue(result)
                }
                else -> {}
            }
        }
    }
    fun fetchCreateActivity(body: CreateActivityRB?) {
        if (body?.record_id!="null"){
            execute {
                when (val result = createActivityUseCase(body)) {
                    is Result.Success -> {
                        _activityRecord.postValue(result)
                    }
                    is Result.Error -> {
                        _activityRecord.postValue(result)
                    }
                    else -> {}
                }
            }
        }

    }
    val getRegisterResult: MutableLiveData<Result<Register>> = _register
    fun fetchCreateRecord() {
        execute {
            when (val result = createRecordUseCase()) {
                is Result.Success -> {
                    _createRecorde.postValue(result)
                }
                is Result.Error -> {
                    _createRecorde.postValue(result)
                }
                else -> {}
            }
        }
    }
    val getCreateRecordResult: MutableLiveData<Result<CreateRecord>> = _createRecorde
    private val _updateRecord: MutableLiveData<Result<UpdateRecord>> = MutableLiveData()

    fun updateRecord(record_id: String? ,body: UpdateRecordeRB?) {

        if (record_id!="null"){
            execute {
                when (val result = updateRecordeUseCase(record_id,body)) {
                    is Result.Success -> {
                        _updateRecord.postValue(result)
                    }
                    is Result.Error -> {
                        _updateRecord.postValue(result)
                    }
                    else -> {}
                }
            }
        }

    }
    val getUpdateRecordResult: MutableLiveData<Result<UpdateRecord>> = _updateRecord

}