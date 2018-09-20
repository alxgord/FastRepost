package com.artto.fastrepost.data.instagram.response

import com.fasterxml.jackson.databind.annotation.JsonDeserialize

@JsonDeserialize(using = GetPostInstagramDeserializer::class)
class GetPostInstagramResponse(val post : InstagramUserPost)