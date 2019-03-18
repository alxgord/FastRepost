package com.artto.fastrepost.data.clipboard

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import com.artto.fastrepost.util.rx.RxClipChangedListener
import io.reactivex.Completable
import io.reactivex.Observable

class ClipboardRepository(private val context: Context) {

    private val clipboardManager = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager

    fun clipChangedListener(): Observable<ClipData> = RxClipChangedListener.create(context)

    fun setClipText(label: String, text: String): Completable =
            Completable.fromCallable { clipboardManager.setPrimaryClip(ClipData.newPlainText(label, text) ) }

    fun getCipText(): Observable<String> = Observable.just(clipboardManager.primaryClip?.getItemAt(0)?.text.toString())
}