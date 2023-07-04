package com.ad8.presentation.util.fcm

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.ad8.presentation.R
import com.ad8.presentation.activity.MainActivity
import com.ad8.presentation.util.extentions.saveToSp
import com.ad8.presentation.util.fcm.MyNotificationReceiver.Companion.NOTIFICATION_BROADCAST


import java.text.SimpleDateFormat
import java.util.*


class MyMessagingService : FirebaseMessagingService() {


    private var imageNotify: Bitmap? = null
    private var mBuilder: NotificationCompat.Builder? = null


    private val CHANNEL_ID = "Sozer"


    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)





        if (remoteMessage == null)
            return

//        if (remoteMessage.data.isNullOrEmpty())
//            return

        try {
            val noticeId = createID()

            //region Save in database

            val notificationDb = ApplicationNotification()

            notificationDb.id = noticeId
            notificationDb.key = remoteMessage.data["key"].toString()
            remoteMessage.data["id"]?.let {
                notificationDb.orderId = it.toInt()
            }
            notificationDb.description = remoteMessage.data["description"].toString()

//            d(notificationDb, "savenotice")
            saveToSp("NOTIFICATION$noticeId", notificationDb)
            val broadCastIntent = Intent(NOTIFICATION_BROADCAST)
            broadCastIntent.putExtra("key", notificationDb.key)
            broadCastIntent.putExtra("orderId", notificationDb.orderId)
            broadCastIntent.putExtra("noticeId", noticeId)

            sendBroadcast(broadCastIntent)
            val resultIntent = Intent(this, MainActivity::class.java)
            resultIntent.action = NOTIFICATION_BROADCAST
            resultIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_REORDER_TO_FRONT or Intent.FLAG_ACTIVITY_NEW_TASK)
            resultIntent.putExtra("key", remoteMessage.data["key"])
            broadCastIntent.putExtra("orderId", notificationDb.orderId)
            resultIntent.putExtra("noticeId", noticeId)

            val pendingIntent: PendingIntent = PendingIntent.getActivity(this, 0, resultIntent, PendingIntent.FLAG_UPDATE_CURRENT)
            //endregion

            //region Delete Notification
            val deletePendingIntent: PendingIntent
            val deleteIntent = Intent(this, MyNotificationReceiver::class.java)
            deleteIntent.action = NOTIFICATION_BROADCAST
            deleteIntent.putExtra("noticeId", noticeId)

            deletePendingIntent = PendingIntent.getBroadcast(this, 0, deleteIntent, PendingIntent.FLAG_UPDATE_CURRENT)
            //endregion

            val largeIcon = BitmapFactory.decodeResource(resources, R.mipmap.ic_launcher)

            //region Big text style


            val noticeStyle = NotificationCompat.BigTextStyle()
                .bigText(remoteMessage.notification?.title)
                .setBigContentTitle(remoteMessage.notification?.body)


            if (imageNotify != null) {
                mBuilder = NotificationCompat.Builder(this, CHANNEL_ID)
                    .setLargeIcon(imageNotify)
                    .setContentTitle(remoteMessage.notification?.title) //Title small notice on app icon touch hold and collapsed notice
                    .setContentText(remoteMessage.notification?.body) //Content small notice on app icon touch hold
//                    .setColor(ContextCompat.getColor(this, R.color.green_school))
                    .setStyle(noticeStyle)
                    .setPriority(NotificationCompat.PRIORITY_MAX)
                    .setDefaults(NotificationCompat.DEFAULT_ALL)
                    .setContentIntent(pendingIntent)
                    .setDeleteIntent(deletePendingIntent)
                    .setAutoCancel(true)
            } else {
                mBuilder = NotificationCompat.Builder(this, CHANNEL_ID)
//                    .setSmallIcon(R.drawable.ic_sozer_notification)
                    .setLargeIcon(largeIcon)
                    .setContentTitle(remoteMessage.notification?.title) //Title small notice on app icon touch hold and collapsed notice
                    .setContentText(remoteMessage.notification?.body) //Content small notice on app icon touch hold
//                    .setColor(ContextCompat.getColor(this, R.color.green_school))
                    .setStyle(noticeStyle)
                    .setPriority(NotificationCompat.PRIORITY_MAX)
                    .setDefaults(NotificationCompat.DEFAULT_ALL)
                    .setContentIntent(pendingIntent)
                    .setDeleteIntent(deletePendingIntent)
                    .setAutoCancel(true)
            }


            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                mBuilder?.build()?.let { createNotificationChannel(it, noticeId) }
            } else {
                val notificationManager = NotificationManagerCompat.from(this)
                mBuilder?.build()?.let { notificationManager.notify(noticeId, it) }
            }
            //endregion
        } catch (e: Exception) {
            e.printStackTrace()

//            d(e.message)
        }
    }

    //region Create channel
    private fun createNotificationChannel(notification: Notification, noticeId: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            //region Variables
            val name = "Sozer"//getString(R.string.defaultNotificationsName)
            val description = "" //getString(R.string.defaultNotificationsDescription)
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel(CHANNEL_ID, name, importance)
            //endregion

            //region Channel
            channel.description = description
            channel.enableVibration(true)
            channel.enableLights(true)
            channel.setShowBadge(true)
            channel.lockscreenVisibility = Notification.VISIBILITY_PUBLIC
            //endregion

            //region Notification Manager
            val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
            notificationManager.notify(noticeId, notification)
            //endregion
        }
    }
    //endregion

    //region Create notification id
    fun createID(): Int {
        val now = Date()
        return Integer.parseInt(SimpleDateFormat("ddHHmmss", Locale.US).format(now))
    }
    //endregion
}