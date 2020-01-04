package com.example.sadok.album.domain

data class Song(val id: Long, val albumId: Long, val title: String, val url: String, val thumbnailUrl: String)
