package com.example.sadok.album.network

import com.example.sadok.album.db.SongEntity
import com.example.sadok.album.domain.Song
import com.squareup.moshi.JsonClass

data class NetworkSongContainer(val songs: List<NetworkSong>)

data class NetworkSong(
        val albumId: Long,
        val id: Long,
        val title: String,
        val url: String,
        val thumbnailUrl: String)

fun NetworkSong.asDatabaseModel(): SongEntity = SongEntity(
    albumId = albumId,
    id = id,
    title = title,
    url = url,
    thumbnailUrl = thumbnailUrl)

fun List<NetworkSong>.asDatabaseModel(): Array<SongEntity> {
    return map {
        SongEntity(
            albumId = it.albumId,
            id = it.id,
            title = it.title,
            url = it.url,
            thumbnailUrl = it.thumbnailUrl)
    }.toTypedArray()
}
/**
 * Convert Network results to database objects
 */
fun NetworkSongContainer.asDomainModel(): List<Song> {
    return songs.map {
        Song(
                albumId = it.albumId,
                id = it.id,
                title = it.title,
                url = it.url,
                thumbnailUrl = it.thumbnailUrl)
    }
}

fun NetworkSongContainer.asDatabaseModel(): Array<SongEntity> {
    return songs.map {
        SongEntity(
            albumId = it.albumId,
            id = it.id,
            title = it.title,
            url = it.url,
            thumbnailUrl = it.thumbnailUrl)
    }.toTypedArray()
}