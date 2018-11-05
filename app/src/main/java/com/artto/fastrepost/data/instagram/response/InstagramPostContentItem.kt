package com.artto.fastrepost.data.instagram.response

data class InstagramPostContentItem(
        var type: String = "",
        var id: String = "",
        var shortCode: String = "",

        var cacheUrl: String = "",
        var storageUrl: String = "",

        var displayUrl: String = "",
        var videoUrl: String = ""
) {
    fun getContentUrl(): String = if (type == InstagramUserPost.TYPE_VIDEO) videoUrl else displayUrl

    fun getFileName(): String = "$id.${getContentUrl().substringAfterLast('.')}"
}