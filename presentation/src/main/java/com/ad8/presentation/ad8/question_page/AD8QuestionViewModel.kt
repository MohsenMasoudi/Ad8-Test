package com.ad8.presentation.ad8.question_page

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.MutableLiveData
import com.ad8.domain.model.stroopTest.Register
import com.ad8.domain.usecase.ad8.AD8RegisterUseCase
import com.ad8.domain.util.Result
import com.ad8.presentation.base.BaseViewModel
import com.ad8.presentation.util.DispatchersProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AD8QuestionViewModel @Inject constructor(
    private val dispatchers: DispatchersProvider
    ,private val registerUseCase: AD8RegisterUseCase

) : BaseViewModel(dispatchers) {

    var isYesSelected:ObservableBoolean = ObservableBoolean(false)
    var isNoSelected:ObservableBoolean = ObservableBoolean(false)
    var isNotSureSelected:ObservableBoolean = ObservableBoolean(false)

    var list= mutableListOf<String>(
        "1. Do you have difficulties with judgment (e.g., difficulties making decisions, bad financial decisions, problems with thinking)?",
        "2. Have you experienced being less interested in your hobbies or activities?",
        "3. Do you repeat the same things often (questions, stories, or statements)?" ,
        "4. Have you experienced having trouble learning how to use a tool, appliance, or gadget (e.g., VCR, computer, microwave, remote control)?",
        "5. Do you forget the correct month or year?",
        "6. Have you experienced having trouble handling complicated financial affairs (e.g., balancing a chequebook, income taxes, paying bills)? ",
        "7. Do you have trouble remembering appointments?",
        "8. Have you experienced daily problems with thinking and/or memory?",
    )

    var answerList= mutableListOf<Int>(0,0,0,0,0,0,0,0)


    private val _register: MutableLiveData<Result<Register>> = MutableLiveData()

    fun fetchRegister() {
        execute {
            _register.postValue(Result.Loading(true))
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
    val getRegisterResult: MutableLiveData<Result<Register>> = _register
}