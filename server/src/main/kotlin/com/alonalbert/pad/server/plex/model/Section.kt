package com.alonalbert.pad.server.plex.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import kotlinx.serialization.Serializable

@JsonIgnoreProperties(ignoreUnknown = true)
@Serializable
data class Section(
    val key: String = "",
    val type: String = "",
    val title: String = "",
)