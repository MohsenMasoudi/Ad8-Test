package com.ad8.presentation.util.extentions

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.widget.Toast
import androidx.fragment.app.Fragment

fun Fragment.copyToClipboard(title: String, value: String) {
    val clipboard = app.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager?
    val clip = ClipData.newPlainText(title, value)
    clipboard?.setPrimaryClip(clip)
    Toast.makeText(requireContext(), "Saved to clipboard", Toast.LENGTH_SHORT).show()
}