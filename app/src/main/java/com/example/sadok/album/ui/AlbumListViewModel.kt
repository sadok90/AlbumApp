package com.example.sadok.album.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.example.sadok.album.db.AlbumDatabase
import com.example.sadok.album.domain.Song
import com.example.sadok.album.repository.Repository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch


class AlbumListViewModel(application: Application) : AndroidViewModel(application) {

    private val viewModelJob = SupervisorJob()
    private val viewModelScope = CoroutineScope(viewModelJob + Dispatchers.Main)
    private val database = AlbumDatabase.getInstance(application)
    private val repository = Repository(database)


    init {
        viewModelScope.launch {
            repository.refreshSongs()
        }
    }

    val songList: LiveData<PagedList<Song>> = repository.songs

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }



}
