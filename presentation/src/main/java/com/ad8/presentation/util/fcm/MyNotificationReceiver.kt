package com.ad8.presentation.util.fcm

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.ad8.presentation.util.extentions.deleteInSp
import com.ad8.presentation.util.extentions.loadFromSp


class MyNotificationReceiver : BroadcastReceiver() {
    companion object {
        const val NOTIFICATION_BROADCAST = "NOTIFICATION_BROADCAST"
    }

    override fun onReceive(context: Context, intent: Intent) {

        when (intent.action) {
            NOTIFICATION_BROADCAST -> {
                val noticeId = intent.getIntExtra("noticeId", 0)
                if (noticeId > 0) {
                    try {
                        val notificationDb = loadFromSp<ApplicationNotification>("NOTIFICATION$noticeId",ApplicationNotification())
                        deleteInSp("NOTIFICATION$noticeId")
                    } catch (e: Exception) {
                    }
                }
            }

        }
    }
}