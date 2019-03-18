package com.artto.fastrepost.util.rx

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.ObservableOnSubscribe

class RxClipChangedListener(context: Context) : ObservableOnSubscribe<ClipData>, ClipboardManager.OnPrimaryClipChangedListener {

    companion object {
        fun create(context: Context): Observable<ClipData> =
                Observable.defer { Observable.create(RxClipChangedListener(context)) }
    }

    private val clipboardManager = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
    private lateinit var emitter: ObservableEmitter<ClipData>

    override fun subscribe(e: ObservableEmitter<ClipData>) {
        emitter = e
        clipboardManager.addPrimaryClipChangedListener(this)
        emitter.setCancellable { clipboardManager.removePrimaryClipChangedListener(this) }
    }

    override fun onPrimaryClipChanged() {
        clipboardManager.primaryClip?.let { emitter.onNext(it) }
    }
}