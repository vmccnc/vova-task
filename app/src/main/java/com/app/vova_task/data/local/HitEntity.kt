package com.app.vova_task.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.app.vova_task.data.remote.dto.Hit


@Entity(tableName = "hits")
data class HitEntity(

    @PrimaryKey(autoGenerate = false)
    val id: Long,
    val pageURL: String,
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
    val userID: Long,
    val user: String,
    val userImageURL: String
) {

    fun toHit() =
        Hit(
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

fun List<HitEntity>.toListHits(): List<Hit> = this.map { it.toHit() }