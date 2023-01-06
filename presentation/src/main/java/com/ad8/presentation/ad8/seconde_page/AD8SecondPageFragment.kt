package com.ad8.presentation.ad8.seconde_page

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.RequestManager
import com.ad8.presentation.R
import com.ad8.presentation.base.BaseFragment
import com.ad8.presentation.databinding.Ad8SecondePageBinding
import com.ad8.presentation.util.UserHelper
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class AD8SecondPageFragment : BaseFragment<Ad8SecondePageBinding>(R.layout.ad8_seconde_page) {
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

    inner class ClickHandler(fragment: AD8SecondPageFragment) {

        fun onBackClickListener(view: View) {
            findNavController().popBackStack()
        }

        fun onNextClicked(view: View) {
            findNavController().navigate(AD8SecondPageFragmentDirections.actionAD8SecondPageFragmentToAD8QuestionPageFragment())
        }
    }

}