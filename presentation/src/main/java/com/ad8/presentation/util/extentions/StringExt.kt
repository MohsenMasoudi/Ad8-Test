package com.ad8.presentation.util.extentions

import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


fun String?.emptyNullString(): String {
    return if (this.isNullOrBlank()) {
        ""
    } else {
        this@emptyNullString
    }
}

fun String.translatePieceTitles(): String {
    return when (this) {
        "Necklace" -> "Kolye"
        "necklace" -> "Kolye"

        "Pendant" -> "Kolye Ucu"
        "pendant" -> "Kolye Ucu"

        "Ring" -> "Yüzük"
        "ring" -> "Yüzük"

        "Earrings" -> "Küpe"
        "earrings" -> "Küpe"

        "Bracelet" -> "Bileklik"
        "bracelet" -> "Bileklik"

        "Cuff" -> "Kelepçe"
        "cuff" -> "Kelepçe"

        "Ear-rings" -> "Küpe"
        "ear-rings" -> "Küpe"
        "Curved" -> "Kolye"
        "curved" -> "Kolye"
        "Straight" -> "bileklik"
        "straight" -> "bileklik"
        "Both" -> "Kolye-Bileklik"
        "both" -> "Kolye-Bileklik"
        else -> this
    }
}
fun String.thumbnail():String{
    var text=""
    text=this.replace(".png","-thumbnail.png",false)
    text=this.replace(". png","-thumbnail. png",false)
    text=this.replace(". jpg","-thumbnail. jpg",false)
    text=this.replace(".PNG","-thumbnail.PNG",false)
    text=this.replace(".jpg","-thumbnail.jpg",false)
    text= this.replace(".JPG","-thumbnail.JPG",false)
    return text
}
fun String.originalImage():String{
     this.replace("-thumbnail.png",".png",true)
     this.replace("-thumbnail.PNG",".PNG",true)
     this.replace("-thumbnail.jpg",".jpg",true)
    return this.replace("-thumbnail.JPG",".JPG",true)
}
fun spannableString(
    firstString: String,
    secondString: String,
    @ColorRes firstColor: Int = 0,
    @ColorRes secondColor: Int = 0,
    thirdString: String = "",
    fourthString: String = "",
    @ColorRes thirdColor: Int = firstColor,
    @ColorRes fourthColor: Int = firstColor,


): SpannableString {


    val totalStringPart1 = firstString + secondString
    val endFirst = firstString.length
    val endSecond = totalStringPart1.length
    val totalStringPart2 = totalStringPart1 + thirdString
    val endThird = totalStringPart2.length
    val totalStringPart3 = totalStringPart2 + fourthString
    val endFourth = totalStringPart3.length
    val wordToSpan = SpannableString(totalStringPart3)

    if (firstColor != 0)
        wordToSpan.setSpan(
            ForegroundColorSpan(ContextCompat.getColor(app.applicationContext, firstColor)),
            0,
            endFirst,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )

    if (secondColor != 0)
        wordToSpan.setSpan(
            ForegroundColorSpan(ContextCompat.getColor(app.applicationContext, secondColor)),
            endFirst,
            endSecond,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )

    if (thirdColor != 0)
        wordToSpan.setSpan(
            ForegroundColorSpan(ContextCompat.getColor(app.applicationContext, thirdColor)),
            endSecond,
            endThird,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )

    if (fourthColor != 0)
        wordToSpan.setSpan(
            ForegroundColorSpan(ContextCompat.getColor(app.applicationContext, fourthColor)),
            endThird,
            endFourth,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )



    return wordToSpan
}


fun attachString(
    isUseSpace: Boolean, vararg strings: String
): String {
    return if (isUseSpace) {
        strings.joinToString(" ")
    } else {
        strings.joinToString("")
    }
}
fun String?.checkDate(): Boolean? {
    if(this==null) return null
    var convertedTime: String? = null

    val date: Date
    val now: Date = try {
        date = SimpleDateFormat("dd LLLL EE",  Locale("tr")).parse(this)
        SimpleDateFormat("dd LLLL EE",  Locale("tr")).parse(SimpleDateFormat("dd LLLL EE",  Locale("tr")).format(Date()))
    } catch (e: Exception) {
        return null
    }


    return date >= now


}