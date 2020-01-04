package com.example.sadok.album.network

import com.example.sadok.album.domain.Song
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET



interface AlbumService {
    @GET("technical-test.json")
    fun getSongs(): Deferred<List<NetworkSong>>
}

private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()


object Network {
    // Configure retrofit to parse JSON and use coroutines
    private val retrofit = Retrofit.Builder()
            .baseUrl("https://static.leboncoin.fr/img/shared/")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()

    val albums = retrofit.create(AlbumService::class.java)
}