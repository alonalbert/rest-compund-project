package com.alonalbert.pad.server.plex.model

import kotlinx.serialization.Serializable

@Serializable
data class Section(
    val key: String = "",
    val type: String = "",
    val title: String = "",
)