package com.artto.fastrepost.interact

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import com.artto.fastrepost.rx.RxClipChangedListener
import io.reactivex.Observable

class ClipboardInteract(private val context: Context) {

    private val clipboardManager = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager

    fun clipChangedListener(): Observable<ClipData> = RxClipChangedListener.create(context)

    fun setClipText(label: String, text: String) {
        clipboardManager.primaryClip = ClipData.newPlainText(label, text)
    }

    fun getCipText(): Observable<String> = Observable.just(clipboardManager.primaryClip?.getItemAt(0)?.text.toString())
}