package com.ad8.data.util

import java.text.DecimalFormat


fun attachString(
    isUseSpace: Boolean, vararg strings: String
): String {
    return if (isUseSpace) {
        strings.joinToString(" ")
    } else {
        strings.joinToString("")
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

        else -> this
    }
}


fun Double?.formatDouble(): String? {
    val format = DecimalFormat("0.#")
     return if (this!=null){
        format.format(this)
    }else{
        null
    }
}