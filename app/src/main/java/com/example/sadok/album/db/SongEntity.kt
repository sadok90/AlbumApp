package com.example.sadok.album.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.sadok.album.domain.Song

@Entity(tableName = "song_table")
data class SongEntity(@PrimaryKey val id: Long, val albumId: Long, val title: String, val url: String, val thumbnailUrl: String)

fun SongEntity.asDomainModel(): Song {
    return  Song(
        id = id,
        albumId = albumId,
        title = title,
        url = url,
        thumbnailUrl = thumbnailUrl)
}
fun List<SongEntity>.asDomainModel(): List<Song> {
    return map {
        it.asDomainModel()
    }
}

