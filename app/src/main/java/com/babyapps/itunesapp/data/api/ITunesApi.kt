package com.babyapps.itunesapp.data.api

import com.babyapps.itunesapp.data.model.ITunesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ITunesApi {

    companion object {
        const val BASE_URL = " https://itunes.apple.com/"
    }

    @GET("search")
    suspend fun searchTrack(
        @Query("term") term: String,
        @Query("entity") entity: String,
        @Query("limit") limit: Int = 20
    ): ITunesResponse

    @GET("search")
    suspend fun searchAudioBook(
        @Query("term") term: String,
        @Query("entity") entity: String,
        @Query("limit") limit: Int = 20
    ): ITunesResponse

    @GET("search")
    suspend fun searchMusicVideo(
        @Query("term") term: String,
        @Query("entity") entity: String,
        @Query("limit") limit: Int = 20
    ): ITunesResponse

    @GET("search")
    suspend fun searchApplication(
        @Query("term") term: String,
        @Query("entity") entity: String,
        @Query("limit") limit: Int = 20
    ): ITunesResponse

}