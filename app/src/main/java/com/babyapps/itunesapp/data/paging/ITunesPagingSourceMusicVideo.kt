package com.babyapps.itunesapp.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.babyapps.itunesapp.data.api.ITunesApi
import com.babyapps.itunesapp.data.model.Result
import retrofit2.HttpException
import java.io.IOException

class ITunesPagingSourceMusicVideo(
    private val api: ITunesApi,
    private val term: String
) : PagingSource<Int, Result>() {


    override fun getRefreshKey(state: PagingState<Int, Result>): Int? {
        TODO("Not yet implemented")
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Result> {
        val position = params.key ?: 1
        return try {
            val response = api.searchMusicVideo(term,"musicVideo",params.loadSize)
            val musicVideos = response.results

            LoadResult.Page(
                data = musicVideos,
                prevKey = if (position == 1) null else position - 1,
                nextKey = if (musicVideos.isEmpty()) null else position + 1
            )
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }


    }


}