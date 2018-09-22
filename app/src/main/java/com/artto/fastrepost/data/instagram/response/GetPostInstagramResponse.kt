package com.artto.fastrepost.data.instagram.response

import com.artto.fastrepost.data.instagram.response.post.InstagramUserPost
import com.fasterxml.jackson.databind.annotation.JsonDeserialize

@JsonDeserialize(using = GetPostInstagramDeserializer::class)
class GetPostInstagramResponse(val post : InstagramUserPost)