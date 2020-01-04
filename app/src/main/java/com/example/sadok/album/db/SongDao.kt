package com.example.sadok.album.db

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface SongDao {

    @Query("SELECT * from song_table WHERE id = :key")
    fun get(key: Long): SongEntity?

    @Query("SELECT * from song_table WHERE albumId = :key")
    fun getSongsByAlbumId(key: Long): LiveData<List<SongEntity>>

    @Query("SELECT * from song_table")
    fun getSongs(): DataSource.Factory<Int, SongEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg songs: SongEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(song: SongEntity)
}