package com.ilham.event.movie_detail.data.remote.models

data class VideoDto(
    val id: String?,
    val key: String?,
    val name: String?,
    val site: String?,
    val type: String?
)

data class VideosResponseDto(
    val results: List<VideoDto>?
)