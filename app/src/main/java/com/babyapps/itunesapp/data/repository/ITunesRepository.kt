package com.babyapps.itunesapp.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.babyapps.itunesapp.data.paging.ITunesPagingSourceApplication
import com.babyapps.itunesapp.data.paging.ITunesPagingSourceTrack
import com.babyapps.itunesapp.data.paging.ITunesPagingSourceAudioBook
import com.babyapps.itunesapp.data.paging.ITunesPagingSourceMusicVideo
import com.babyapps.itunesapp.data.api.ITunesApi
import javax.inject.Inject

class ITunesRepository @Inject constructor(private val api: ITunesApi) {

    fun searchTrack(term: String) = Pager(
        config = PagingConfig(pageSize = 20, maxSize = 100, enablePlaceholders = false),
        pagingSourceFactory = { ITunesPagingSourceTrack(api, term) }
    ).liveData

    fun searchAudioBook(term: String) = Pager(
        config = PagingConfig(pageSize = 20, maxSize = 100, enablePlaceholders = false),
        pagingSourceFactory = { ITunesPagingSourceAudioBook(api, term) }
    ).liveData

    fun searchMusicVideo(term: String) = Pager(
        config = PagingConfig(pageSize = 20, maxSize = 100, enablePlaceholders = false),
        pagingSourceFactory = { ITunesPagingSourceMusicVideo(api, term) }
    ).liveData

    fun searchApplication(term: String) = Pager(
        config = PagingConfig(pageSize = 20, maxSize = 100, enablePlaceholders = false),
        pagingSourceFactory = { ITunesPagingSourceApplication(api, term) }
    ).liveData

}