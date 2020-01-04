package com.example.sadok.album.repository

import androidx.paging.LivePagedListBuilder
import com.example.sadok.album.db.AlbumDatabase
import com.example.sadok.album.db.asDomainModel
import com.example.sadok.album.network.Network
import com.example.sadok.album.network.asDatabaseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class Repository(private val database: AlbumDatabase) {

    private val factory = database.songDao.getSongs().map { it.asDomainModel() }
    val songs = LivePagedListBuilder(factory,  /* page size */50).build()

    suspend fun refreshSongs() {
        withContext(Dispatchers.IO) {
            val songs = Network.albums.getSongs().await()
            database.songDao.insertAll(*songs.asDatabaseModel())
        }
    }
}