package com.artto.fastrepost.data.instagram.response

import com.artto.fastrepost.data.instagram.response.post.InstagramPostContentItem
import com.artto.fastrepost.data.instagram.response.post.InstagramUserPost
import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.deser.std.StdDeserializer
import java.io.IOException

class GetPostInstagramDeserializer(vc: Class<*>? = null) : StdDeserializer<GetPostInstagramResponse>(vc) {

    @Throws(IOException::class, JsonProcessingException::class)
    override fun deserialize(jp: JsonParser, ctxt: DeserializationContext): GetPostInstagramResponse {

        val rootNode = jp.codec.readTree<JsonNode>(jp)
                .get("graphql")
                .get("shortcode_media")


        val post = InstagramUserPost().apply {
            type = rootNode.get("__typename").textValue()
            id = rootNode.get("id").textValue()
            shortCode = rootNode.get("shortcode").textValue()

            rootNode.get("edge_media_to_caption").get("edges").apply {
                if (has(0))
                    caption = get(0)
                            .get("node")
                            .get("text")
                            .textValue()
            }

            likesCount = rootNode
                    .get("edge_media_preview_like")
                    .get("count")
                    .intValue()

            ownerId = rootNode
                    .get("owner")
                    .get("id")
                    .textValue()

            profilePicUrl = rootNode
                    .get("owner")
                    .get("profile_pic_url")
                    .textValue()

            ownerUserName = rootNode
                    .get("owner")
                    .get("username")
                    .textValue()

            content.addAll(
                    when (type) {
                        InstagramUserPost.TYPE_IMAGE, InstagramUserPost.TYPE_VIDEO ->
                            listOf(InstagramPostContentItem(type, id, shortCode, rootNode.get("display_url").textValue())
                                    .apply {
                                        if (type == InstagramUserPost.TYPE_VIDEO)
                                            videoUrl = rootNode.get("video_url").textValue()
                                    })

                        InstagramUserPost.TYPE_SIDECAR ->
                            rootNode
                                    .get("edge_sidecar_to_children")
                                    .get("edges")
                                    .map {
                                        val node = it.get("node")
                                        InstagramPostContentItem(
                                                node.get("__typename").textValue(),
                                                node.get("id").textValue(),
                                                node.get("shortcode").textValue(),
                                                node.get("display_url").textValue()
                                        ).apply {
                                            if (type == InstagramUserPost.TYPE_VIDEO)
                                                videoUrl = node.get("video_url").textValue()
                                        }
                                    }
                        else -> listOf(InstagramPostContentItem())
                    }
            )
        }
        return GetPostInstagramResponse(post)
    }
}