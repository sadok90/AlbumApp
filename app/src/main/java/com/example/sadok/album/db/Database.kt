package com.example.sadok.album.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [SongEntity::class], version = 1, exportSchema = false)
abstract class AlbumDatabase: RoomDatabase() {
    abstract val songDao: SongDao

    companion object {

        @Volatile
        private var INSTANCE: AlbumDatabase? = null


        fun getInstance(context: Context): AlbumDatabase {

            synchronized(this) {
                 var instance = INSTANCE
                // If instance is `null` make a new database instance.
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AlbumDatabase::class.java,
                        "album_database")
                        .fallbackToDestructiveMigration()
                        .build()
                    // Assign INSTANCE to the newly created database.
                    INSTANCE = instance
                }
                // Return instance; smart cast to be non-null.
                return instance
            }
        }
    }
}