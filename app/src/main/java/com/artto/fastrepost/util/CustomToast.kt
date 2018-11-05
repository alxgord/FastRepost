package com.artto.fastrepost.util

import android.annotation.SuppressLint
import android.widget.TextView
import android.view.LayoutInflater
import android.widget.Toast
import android.content.Context
import com.artto.fastrepost.R


class CustomToast(context: Context) : Toast(context) {

    companion object {

        @SuppressLint("InflateParams", "ShowToast")
        fun makeText(context: Context, text: CharSequence, duration: Int): Toast {
            val toast = Toast.makeText(context, text, duration)
            val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val layout = inflater.inflate(R.layout.toast_custom_layout, null)
            val textView = layout.findViewById(R.id.text_view_custom_toast) as TextView
            textView.text = text
            toast.view = layout
            return toast
        }

    }

}