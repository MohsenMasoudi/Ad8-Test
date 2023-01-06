package com.ad8.presentation.activity

import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.res.Configuration
import android.os.Bundle
import android.view.MotionEvent
import androidx.activity.viewModels
import androidx.navigation.fragment.NavHostFragment
import com.aghajari.zoomhelper.ZoomHelper
import com.ad8.domain.model.stroopTest.Register
import com.ad8.domain.util.Constants
import com.ad8.domain.util.Result
import com.ad8.presentation.R
import com.ad8.presentation.base.BaseActivity
import com.ad8.presentation.base.IS_USER_LOGIN
import com.ad8.presentation.base.NOTIFICATION_TYPE_ORDER_DETAIL
import com.ad8.presentation.databinding.ActivityMainBinding
import com.ad8.presentation.util.UserHelper
import com.ad8.presentation.util.extentions.loadFromSp
import com.ad8.presentation.util.extentions.saveToSp
import com.ad8.presentation.util.fcm.ApplicationNotification
import com.ad8.presentation.util.fcm.MyNotificationReceiver.Companion.NOTIFICATION_BROADCAST
import com.ad8.presentation.util.observe
import dagger.hilt.android.AndroidEntryPoint
import java.util.Timer
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {
    private var token: String? = null
    private val viewModel: MainViewModel by viewModels()

    val timer = Timer()
    private val broadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
//            e("onReceive", "onReceive0")
            if (intent == null)
                return

            try {
                when (intent.action) {
                    NOTIFICATION_BROADCAST -> {
                        val key = intent.getStringExtra("key")
                        val noticeId = intent.getIntExtra("noticeId", 0)
                        val notificationDb = loadFromSp<ApplicationNotification>("NOTIFICATION$noticeId", ApplicationNotification())
                        if (notificationDb.key == NOTIFICATION_TYPE_ORDER_DETAIL) {
                            val isLogin = loadFromSp<Boolean>(IS_USER_LOGIN, false)
//                            if (isLogin) {
//                                val id = notificationDb.orderId
//                                val arg = bundleOf("order" to id.toLong())
//                                findNavController(R.id.nav_host_fragment).navigate(
//                                    R.id.orderDetailStoneListFragment, OrderDetailFragmentArgs.fromBundle(arg).toBundle()
//                                )
//                            }
                        }


//

                    }
                }
            } catch (e: Exception) {
            }
        }
    }

    @Inject
    lateinit var userHelper: UserHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupObservers()
        setupUI()



    }

    override fun expireToken() {

    }

    override fun onStop() {
        super.onStop()
        timer.cancel()
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        return super.onTouchEvent(event)
    }

    private fun setupObservers() {
        viewModel.run {
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

    fun setupUI() {
        token = loadFromSp(Constants.ACCESS_TOKEN, "")
        if (token.isNullOrEmpty()) {
            viewModel.fetchRegister()
        }


        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment

    }




    companion object {
        lateinit var MESSAGE_STATUS: String
    }

    override fun onResume() {
        super.onResume()
        val intentFilter = IntentFilter()
        intentFilter.addAction(NOTIFICATION_BROADCAST)
        registerReceiver(broadcastReceiver, intentFilter)
        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.cancelAll()
    }





    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        try {
            return ZoomHelper.getInstance().dispatchTouchEvent(ev!!, this) || super.dispatchTouchEvent(ev)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return false
    }


    override fun attachBaseContext(newBase: Context?) {
        val newOverride = Configuration(newBase?.resources?.configuration)
        newOverride.fontScale = 1.0f
        applyOverrideConfiguration(newOverride)
        super.attachBaseContext(newBase)
    }


}