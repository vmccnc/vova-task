package com.app.vova_task.data.remote.dto

import com.app.vova_task.data.local.HitEntity
import com.squareup.moshi.Json

data class Welcome10(
    val total: Long,
    val totalHits: Long,
    val hits: List<Hit>
)


data class Hit(
    val id: Long,
    val pageURL: String,
//    val type: Type,
    val tags: String,
    val previewURL: String,
    val previewWidth: Long,
    val previewHeight: Long,
    val webformatURL: String,
    val webformatWidth: Long,
    val webformatHeight: Long,
    val largeImageURL: String,
    val imageWidth: Long,
    val imageHeight: Long,
    val imageSize: Long,
    val views: Long,
    val downloads: Long,
    val collections: Long,
    val likes: Long,
    val comments: Long,
    @Json(name = "user_id") val userID: Long,
    val user: String,
    val userImageURL: String
) {
    fun toHitEntity() =
        HitEntity(
            id = id,
            pageURL = pageURL,

            tags = tags,
            previewURL = previewURL,
            previewWidth = previewWidth,
            previewHeight = previewHeight,
            webformatURL = webformatURL,
            webformatWidth = webformatWidth,
            webformatHeight = webformatHeight,
            largeImageURL = largeImageURL,
            imageWidth = imageWidth,
            imageHeight = imageHeight,
            imageSize = imageSize,
            views = views,
            downloads = downloads,
            collections = collections,
            likes = likes,
            comments = comments,
            userID = userID,
            user = user,
            userImageURL = userImageURL,
        )
}