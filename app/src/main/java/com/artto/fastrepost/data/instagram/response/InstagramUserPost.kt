package com.artto.fastrepost.data.instagram.response

class InstagramUserPost {

    companion object {
        const val TYPE_IMAGE = "GraphImage"
        const val TYPE_VIDEO = "GraphVideo"
        const val TYPE_SIDECAR = "GraphSidecar"
    }

    var type = ""
    var id = ""
    var shortCode = ""
    var caption = ""
    var likesCount = 0
    var content = ArrayList<InstagramPostContentItem>()

    var ownerId = ""
    var profilePicUrl = ""
    var ownerUserName = ""

}