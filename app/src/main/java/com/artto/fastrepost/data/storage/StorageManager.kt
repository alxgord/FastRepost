package com.artto.fastrepost.data.storage

import android.content.Context
import android.os.Environment
import com.artto.fastrepost.R
import com.artto.fastrepost.data.instagram.response.InstagramPostContentItem
import com.artto.fastrepost.data.instagram.response.InstagramUserPost
import com.artto.fastrepost.util.extension.enqueue
import io.reactivex.Single
import okhttp3.*
import okio.Source
import okio.buffer
import okio.sink
import okio.source
import java.io.File

class StorageManager(context: Context,
                     private val okHttpClient: OkHttpClient) {

    private val appDirectoryName = context.getString(R.string.app_name)
    private val cacheDirectory = File(context.cacheDir, appDirectoryName)
    private val externalDirectory = File(Environment.getExternalStorageDirectory(), appDirectoryName)

    fun savePostContent(post: InstagramUserPost): Single<InstagramUserPost> =
            Single.zip(post.content.map { saveToCache(it) }) { result ->
                post.apply { content = ArrayList(result.map { it as InstagramPostContentItem }) }
            }

    private fun saveToCache(item: InstagramPostContentItem): Single<InstagramPostContentItem> = Single.create { emitter ->
        cacheDirectory.apply { if (!exists()) mkdirs() }
        File(cacheDirectory, item.getFileName()).apply {
            if (exists()) emitter.onSuccess(item.apply { cacheUrl = absolutePath })
        }

        okHttpClient.newCall(Request.Builder().url(item.getContentUrl()).build())
                .enqueue(
                        onFailure = { _, e -> emitter.onError(e) },
                        onResponse = { _, response ->
                            response.body()?.source()
                                    ?: emitter.onError(Exception("Empty response body"))
                            response.body()?.source()?.let {
                                emitter.onSuccess(item.apply { cacheUrl = writeFile(item.getFileName(), cacheDirectory, it) })
                            }
                        }
                )
    }

    fun saveToExternal(item: InstagramPostContentItem): Single<InstagramPostContentItem> = Single.create { emitter ->
        externalDirectory.apply { if (!exists()) mkdirs() }

        with(File(externalDirectory, item.getFileName())) {
            if (exists()) emitter.onSuccess(item.apply { storageUrl = absolutePath })
        }

        if (item.isItemInCache()) {
            emitter.onSuccess(item.apply { storageUrl = writeFile(item.getFileName(), externalDirectory, File(cacheUrl).source()) })
        }

        okHttpClient.newCall(Request.Builder().url(item.getContentUrl()).build())
                .enqueue(
                        onFailure = { _, e -> emitter.onError(e) },
                        onResponse = { _, response ->
                            response.body()?.source()
                                    ?: emitter.onError(Exception("Empty response body"))
                            response.body()?.source()?.let {
                                emitter.onSuccess(item.apply { storageUrl = writeFile(item.getFileName(), externalDirectory, it) })
                            }
                        }
                )
    }

    private fun writeFile(fileName: String, directory: File, source: Source): String {
        val file = File(directory, fileName)
        file.sink().buffer().apply {
            writeAll(source)
            close()
        }
        return file.absolutePath
    }

    private fun InstagramPostContentItem.isItemInCache(): Boolean = File(this.cacheUrl).exists()
}