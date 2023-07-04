package com.ad8.presentation.ad8.result_page

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.RequestManager
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.ad8.domain.model.ad8.AD8Answer
import com.ad8.domain.requestBody.stroopTest.Ad8AnswerRB
import com.ad8.domain.util.Result
import com.ad8.presentation.R
import com.ad8.presentation.base.AD8_ANSWER
import com.ad8.presentation.base.BaseFragment
import com.ad8.presentation.base.stringAnswer
import com.ad8.presentation.databinding.Ad8ResultPageBinding
import com.ad8.presentation.util.UserHelper
import com.ad8.presentation.util.extentions.loadFromSp
import com.ad8.presentation.util.observe
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class AD8ResultPageFragment : BaseFragment<Ad8ResultPageBinding>(R.layout.ad8_result_page) {
    private val clickHandler = ClickHandler(this)
    private val mViewModel: Ad8ResultPageViewModel by viewModels()


    @Inject
    lateinit var glide: RequestManager

    @Inject
    lateinit var userHelper: UserHelper

    val args: AD8ResultPageFragmentArgs by navArgs()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupObservers()
    }

    private fun setupObservers() {
        mViewModel.run {
            observe(getAnswerResult, ::answerResult)

        }

    }

    private fun answerResult(result: Result<AD8Answer>) {
        if (result is Result.Success) {
            val answer = result.data
            Log.e("AD8ResultPageFragment", "answerResult: ${answer.data}")


        } else if (result is Result.Error) {
            Log.e("AD8ResultPageFragment", "answerResult: ${result.error.message}")
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
        binding.clickHandler = clickHandler
        var result = args.result
        if (result < 2) {
            binding.resultText.text = "Your test result is normal. Everything seems fine"
            binding.descriptionText.text =
                "It's best to check your cognitive skills every once in a while by taking the test more often."
        } else {
            binding.resultText.text =
                "Your test result shows signs of MCI (Mild Cognitive Impairment)"
            binding.descriptionText.text =
                "The best thing to do is to talk to your healthcare provider as soon as possible."
        }
        val answer = loadFromSp<ArrayList<String>>(AD8_ANSWER, arrayListOf<String>())
        main(stringAnswer)
        Log.e("AD8ResultPageFragment", "setupUI: ${stringAnswer}")


    }

    override fun expireToken() {
    }

inner class ClickHandler(fragment: AD8ResultPageFragment) {


    fun onNextClicked(view: View) {
        findNavController().navigate(AD8ResultPageFragmentDirections.actionAD8ResultPageFragmentToAD8FirstPageFragment(

        ))
    }
}

fun main(args: ArrayList<String>) {
        val gson = Gson()
        val gsonPretty = GsonBuilder().setPrettyPrinting().create()

        val tut = args

//        val jsonTut: String = gson.toJson(args)
//        println(jsonTut)

        val jsonTutPretty: String = gsonPretty.toJson(tut)
        mViewModel.fetchAnswerFetch(Ad8AnswerRB(answers = jsonTutPretty))

//        println(jsonTutPretty)
}
}