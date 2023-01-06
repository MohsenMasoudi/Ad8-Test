package com.ad8.presentation.ad8.result_page

import androidx.lifecycle.MutableLiveData
import com.ad8.domain.model.ad8.AD8Answer
import com.ad8.domain.requestBody.stroopTest.Ad8AnswerRB
import com.ad8.domain.usecase.ad8.AD8CreateAnswerUseCase
import com.ad8.presentation.base.BaseViewModel
import com.ad8.domain.util.Result
import com.ad8.presentation.util.DispatchersProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class Ad8ResultPageViewModel @Inject constructor(
    private val dispatchers: DispatchersProvider,
    private val aD8CreateAnswerUseCase: AD8CreateAnswerUseCase

) : BaseViewModel(dispatchers) {
    private val _answer: MutableLiveData<Result<AD8Answer>> = MutableLiveData()

    fun fetchAnswerFetch(body: Ad8AnswerRB?) {
        execute {

            _answer.postValue(Result.Loading(true))
            when (val result = aD8CreateAnswerUseCase(body)) {
                is Result.Success -> {
                    _answer.postValue(result)
                }
                is Result.Error -> {
                    _answer.postValue(result)
                }
                else -> {}
            }
        }
    }
    val getAnswerResult: MutableLiveData<Result<AD8Answer>> = _answer

}