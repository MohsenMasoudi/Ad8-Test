package com.ad8.presentation.ad8.first_page

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.RequestManager
import com.ad8.presentation.R
import com.ad8.presentation.base.BaseFragment
import com.ad8.presentation.databinding.Ad8FirstPageBinding
import com.ad8.presentation.util.UserHelper
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class AD8FirstPageFragment : BaseFragment<Ad8FirstPageBinding>(R.layout.ad8_first_page) {
    private val clickHandler = ClickHandler(this)

    @Inject
    lateinit var glide: RequestManager

    @Inject
    lateinit var userHelper: UserHelper


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    private fun setupObservers() {


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


    }

    override fun expireToken() {
    }

    inner class ClickHandler(fragment: AD8FirstPageFragment) {

        fun onNotificationButtonClicked(view: View) {

        }

        fun onNextClicked(view: View) {
            findNavController().navigate(AD8FirstPageFragmentDirections.actionAD8FirstPageFragmentToAD8QuestionPageFragment())
        }
    }

}