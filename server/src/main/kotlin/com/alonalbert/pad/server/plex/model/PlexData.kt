package com.alonalbert.pad.server.plex.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@JsonIgnoreProperties(ignoreUnknown = true)
@Serializable
data class PlexData<T>(
    @JsonProperty("MediaContainer")
    @SerialName("MediaContainer")
    var mediaContainer: MediaContainer<T> = MediaContainer(),
)