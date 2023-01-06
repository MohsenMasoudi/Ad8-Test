package com.ad8.data.util

import android.annotation.SuppressLint
import timber.log.Timber
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

object TimeUtil {

    @SuppressLint("SimpleDateFormat")
    fun covertTimeToText(dataDate: String?): String? {
        var convertedTime: String? = null
        val prefix = ""
        val suffix = ""
        try {

            val pasTime = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ").parse(
                dataDate?.replace(
                    "Z$".toRegex(),
                    "+0000"
                )
            )
            val nowTime = Date()
            val dateDiff = nowTime.time - pasTime.time
            val second = TimeUnit.MILLISECONDS.toSeconds(dateDiff)
            val minute = TimeUnit.MILLISECONDS.toMinutes(dateDiff)
            val hour = TimeUnit.MILLISECONDS.toHours(dateDiff)
            val day = TimeUnit.MILLISECONDS.toDays(dateDiff)
            if (second < 60) {
                convertedTime = "$second second$suffix"
            } else if (minute < 60) {
                convertedTime = "$minute minute$suffix"
            } else if (hour < 24) {
                convertedTime = "$hour hour$suffix"
            } else if (day >= 7) {
                convertedTime = if (day > 360) {
                    (day / 360).toString() + " year" + suffix
                } else if (day > 30) {
                    (day / 30).toString() + " month" + suffix
                } else {
                    (day / 7).toString() + " week" + suffix
                }
            } else if (day < 7) {
                convertedTime = "$day day$suffix"
            }
        } catch (e: ParseException) {
            e.printStackTrace()
            Timber.e(e.message!!)
        }
        return convertedTime
    }

    @SuppressLint("SimpleDateFormat")
    fun covertToSimpleDate(dataDate: String?): String? {
        if(dataDate==null) return ""
        var convertedTime: String? = null
        try {

            val pasTime = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ")
            pasTime.timeZone = TimeZone.getTimeZone("GMT")
            val formatter = SimpleDateFormat("dd LLLL EE",  Locale("tr"))
            val formattedDate = formatter.format(pasTime.parse(
                dataDate.replace(
                    "Z$".toRegex(),
                    "+0000"
                )
            ))
            convertedTime=formattedDate

        } catch (e: ParseException) {
            e.printStackTrace()
            Timber.e(e.message!!)
        }
        return convertedTime
    }
    @SuppressLint("SimpleDateFormat")
    fun covertToSimpleDateWithYear(dataDate: String?): String? {
        if(dataDate==null) return ""
        var convertedTime: String? = null
        try {

            val pasTime = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ")
            pasTime.timeZone = TimeZone.getTimeZone("GMT")
            val formatter = SimpleDateFormat("LLLL dd yyyy",  Locale("tr"))
            val formattedDate = formatter.format(pasTime.parse(
                dataDate.replace(
                    "Z$".toRegex(),
                    "+0000"
                )
            ))
            convertedTime=formattedDate

        } catch (e: ParseException) {
            e.printStackTrace()
            Timber.e(e.message!!)
        }
        return convertedTime
    }
    @SuppressLint("SimpleDateFormat")
    fun covertToSimpleDateTime(dataDate: String?): String? {
        if(dataDate==null) return ""
        var convertedTime: String? = null
        try {

            val pasTime = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ")
            pasTime.timeZone = TimeZone.getTimeZone("GMT")
           // val formatter = SimpleDateFormat("LLLL dd yyyy - HH:mm",  Locale("tr"))
            val formatter = SimpleDateFormat("dd LLLL EE",  Locale("tr"))
            val formattedDate = formatter.format(pasTime.parse(
                dataDate.replace(
                    "Z$".toRegex(),
                    "+0000"
                )
            ))
            convertedTime=formattedDate

        } catch (e: ParseException) {
            e.printStackTrace()
            Timber.e(e.message!!)
        }
        return convertedTime
    }
    @SuppressLint("SimpleDateFormat")
    fun covertToSimpleDateTimeWithHoursAndMinute(dataDate: String?): String? {
        if(dataDate==null) return ""
        var convertedTime: String? = null
        try {

            val pasTime = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ")
            pasTime.timeZone = TimeZone.getTimeZone("GMT")
            // val formatter = SimpleDateFormat("LLLL dd yyyy - HH:mm",  Locale("tr"))
            val formatter = SimpleDateFormat("dd LLLL EE - HH:mm",  Locale("tr"))
            val formattedDate = formatter.format(pasTime.parse(
                dataDate.replace(
                    "Z$".toRegex(),
                    "+0000"
                )
            ))
            convertedTime=formattedDate

        } catch (e: ParseException) {
            e.printStackTrace()
            Timber.e(e.message!!)
        }
        return convertedTime
    }

    @SuppressLint("SimpleDateFormat")
    fun covertToSimpleDateTimeYear(dataDate: String?): String? {
        if(dataDate==null) return ""
        var convertedTime: String? = null
        try {

            val pasTime = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ")
            pasTime.timeZone = TimeZone.getTimeZone("GMT")
            // val formatter = SimpleDateFormat("LLLL dd yyyy - HH:mm",  Locale("tr"))
            val formatter = SimpleDateFormat("LLLL dd yyyy",  Locale("tr"))
            val formattedDate = formatter.format(pasTime.parse(
                dataDate.replace(
                    "Z$".toRegex(),
                    "+0000"
                )
            ))
            convertedTime=formattedDate

        } catch (e: ParseException) {
            e.printStackTrace()
            Timber.e(e.message!!)
        }
        return convertedTime
    }

    @SuppressLint("SimpleDateFormat")
    fun covertNumericDate(dataDate: String?): String? {
        if(dataDate==null) return ""
        var convertedTime: String? = null
        try {
            val pasTime = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ")
            pasTime.timeZone = TimeZone.getTimeZone("GMT")
            val formatter = SimpleDateFormat("yyyy.MM.dd")
            val formattedDate = formatter.format(pasTime.parse(
                dataDate.replace(
                    "Z$".toRegex(),
                    "+0000"
                )
            ))
            convertedTime=formattedDate

        } catch (e: ParseException) {
            e.printStackTrace()
            Timber.e(e.message!!)
        }
        return convertedTime
    }
}