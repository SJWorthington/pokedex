package com.example.anotherpokedex.data.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class NamedApiResource(
    val name: String,
    val url: String
)