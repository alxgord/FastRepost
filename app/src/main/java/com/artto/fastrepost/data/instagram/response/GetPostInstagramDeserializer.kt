package com.artto.fastrepost.data.instagram.response

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
            id = rootNode.get("id").textValue()
            displayUrl = rootNode.get("display_url").textValue()
            shortCode = rootNode.get("shortcode").textValue()

            likesCount = rootNode
                    .get("edge_media_preview_like")
                    .get("count")
                    .intValue()

            ownerId = rootNode
                    .get("owner")
                    .get("id")
                    .textValue()

            ownerUserName = rootNode
                    .get("owner")
                    .get("username")
                    .textValue()

        }
        return GetPostInstagramResponse(post)

    }
}