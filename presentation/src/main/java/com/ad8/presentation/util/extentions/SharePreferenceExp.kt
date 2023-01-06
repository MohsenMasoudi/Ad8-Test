package com.ad8.presentation.util.extentions

import com.orhanobut.hawk.Hawk

fun <T> saveToSp(key: String, t: T) {
    Hawk.put(key, t)
}

//@JvmName("loadFromSp1")
//fun <T> loadFromSp(key: String, defaultValue: T): T {
//    return if (existInSp(key)) {
//        Hawk.get(key)
//
//    } else {
//        defaultValue
//    }
//}

@Suppress("UNCHECKED_CAST")
fun <T> loadFromSp(key: String, defaultValue: T): T {
     if (existInSp(key)) {
         return  Hawk.get(key)
    }else{
         return defaultValue

    }
}

fun existInSp(key: String): Boolean {
    return Hawk.contains(key)
}


fun deleteAllInSp() {
    Hawk.deleteAll()
}

fun deleteInSp(key: String) {
    Hawk.delete(key)
}

