package com.ad8.presentation.ad8.question_page

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.RequestManager
import com.ad8.domain.model.stroopTest.Register
import com.ad8.domain.util.Constants
import com.ad8.domain.util.Result
import com.ad8.presentation.R
import com.ad8.presentation.base.AD8_ANSWER
import com.ad8.presentation.base.BaseFragment
import com.ad8.presentation.base.stringAnswer
import com.ad8.presentation.databinding.Ad8QuestionPageBinding
import com.ad8.presentation.util.UserHelper
import com.ad8.presentation.util.extentions.loadFromSp
import com.ad8.presentation.util.extentions.saveToSp
import com.ad8.presentation.util.observe
import dagger.hilt.android.AndroidEntryPoint
import org.json.JSONArray
import javax.inject.Inject


@AndroidEntryPoint
class AD8QuestionPageFragment : BaseFragment<Ad8QuestionPageBinding>(R.layout.ad8_question_page) {
    private val clickHandler = ClickHandler(this)
    private val mViewModel: AD8QuestionViewModel by viewModels()
    var position = 0

    @Inject
    lateinit var glide: RequestManager

    @Inject
    lateinit var userHelper: UserHelper


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupObservers()
        stringAnswer= arrayListOf<String>("","","","","","","","")
    }

    private fun setupObservers() {
        mViewModel.run {
            observe(getRegisterResult, ::registerResult)

        }

    }

    private fun registerResult(result: Result<Register>) {
        if (result is Result.Success) {
            val register = result.data
            saveToSp(Constants.ACCESS_TOKEN, register.data?.token)
            userHelper.saveToken(register.data?.token)

        }

    }

    private fun isLoadingResult(isLoaded: Boolean?) {
        //findNavController().navigate(SplashFragmentDirections.splashToHome())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
    }

    private fun setupUI() {
        val token = loadFromSp(Constants.ACCESS_TOKEN, "")
        if (token.isEmpty()) {
            Log.d("AD8QuestionPageFragment", "fetchRegister")
            mViewModel.fetchRegister()
        }
        binding.clickHandler = clickHandler
        binding.viewModel = mViewModel
        setQuestion()

    }

    override fun expireToken() {
    }

    inner class ClickHandler(fragment: AD8QuestionPageFragment) {
        fun onBackClickListener(view: View) {
            if (position == 0) {
                findNavController().popBackStack()
            } else {
                position--

                mViewModel.isNoSelected.set(false)
                mViewModel.isNotSureSelected.set(false)
                mViewModel.isYesSelected.set(false)
                if (stringAnswer[position] == "yes") {
                    mViewModel.isYesSelected.set(true)

                } else if (stringAnswer[position] == "no") {
                    mViewModel.isNoSelected.set(true)

                } else if (stringAnswer[position] == "notSure") {
                    mViewModel.isNotSureSelected.set(true)

                }
                setQuestion()
            }
        }

        fun onNoClickListener(view: View) {
            mViewModel.answerList[position] = 0
            stringAnswer[position] = "no"

            if (mViewModel.isNoSelected.get()) {
                mViewModel.isNoSelected.set(false)
            } else {
                mViewModel.isNoSelected.set(true)
                mViewModel.isYesSelected.set(false)
                mViewModel.isNotSureSelected.set(false)

            }
        }

        fun onYesClickListener(view: View) {
            mViewModel.answerList[position] = 1
            stringAnswer[position] = "yes"

            if (mViewModel.isYesSelected.get()) {
                mViewModel.isYesSelected.set(false)
            } else {
                mViewModel.isNoSelected.set(false)
                mViewModel.isYesSelected.set(true)
                mViewModel.isNotSureSelected.set(false)

            }

        }

        fun onNotSureClickListener(view: View) {
            mViewModel.answerList[position] = 0
            stringAnswer[position] = "notSure"
            if (mViewModel.isNotSureSelected.get()) {
                mViewModel.isNotSureSelected.set(false)
            } else {
                mViewModel.isNoSelected.set(false)
                mViewModel.isYesSelected.set(false)
                mViewModel.isNotSureSelected.set(true)

            }
        }


        fun onNextClicked(view: View) {
            if (!mViewModel.isYesSelected.get() && !mViewModel.isNoSelected.get() && !mViewModel.isNotSureSelected.get()) {
                Toast.makeText(
                    requireContext(),
                    "Choose one of the answers",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                if (position < 7) {

                    position++
                    setQuestion()
                    if (position == 7) {
                        binding.btn.text = "Finish Test"
                    } else {
                        binding.btn.text = "Next Question"
                    }
                    if (position != 0) {
                        binding.txtToolbar.text = "Previous Question"
                    } else {
                        binding.txtToolbar.text = "txtToolbarAD8 Cognition Test"
                    }
                    (binding.percentView.layoutParams as LinearLayout.LayoutParams).weight =
                        ((position + 1).toFloat() / 8)
                    (binding.exteraView.layoutParams as LinearLayout.LayoutParams).weight =
                        1f - ((position + 1).toFloat() / 8)
                    binding.percentView.requestLayout()
                    binding.exteraView.requestLayout()
                    mViewModel.isNoSelected.set(false)
                    mViewModel.isNotSureSelected.set(false)
                    mViewModel.isYesSelected.set(false)


                } else {
                    var result = 0
                    for (i in mViewModel.answerList) {
                        if (i != 0) {
                            result++
                        }
                    }
                    val answerString = JSONArray(stringAnswer.toString())

                    saveToSp<ArrayList<String>>(AD8_ANSWER, stringAnswer)
                    findNavController().navigate(
                        AD8QuestionPageFragmentDirections.actionAD8QuestionPageFragmentToAD8ResultPageFragment(
                            result = result
                        )
                    )
                }
            }

        }
    }

    @SuppressLint("SetTextI18n")
    fun setQuestion() {
        binding.position.text = "Question ${position + 1}/8"
        binding.question.text = mViewModel.list[position]
    }

}