package com.uipractice.roomfinder

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableString
import android.text.TextPaint
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.view.View
import android.widget.Toast

fun SpannableString.clickableForegroundColorSpan(color: String, currentActivity: Activity, nextActivity: Class<*>): SpannableString {
    val clickableSpan: ClickableSpan = object : ClickableSpan() {
        override fun onClick(view: View) {
            currentActivity.startActivity(Intent(currentActivity, nextActivity))
        }
        override fun updateDrawState(ds: TextPaint) {
            super.updateDrawState(ds)
            ds.isUnderlineText = false
        }
    }
    this.setSpan(clickableSpan, 0, this.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
    this.setSpan(ForegroundColorSpan(Color.parseColor(color)), 0, this.length, 0)
    return this
}

fun SpannableString.boldSpan(): SpannableString {
    this.setSpan(StyleSpan(Typeface.BOLD), 0, this.length, 0)
    return this
}

fun String.createToast(context: Context, length: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(context, this, length).show()
}
