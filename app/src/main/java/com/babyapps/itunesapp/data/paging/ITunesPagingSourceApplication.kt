package com.babyapps.itunesapp.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.babyapps.itunesapp.data.api.ITunesApi
import com.babyapps.itunesapp.data.model.Result
import retrofit2.HttpException
import java.io.IOException

class ITunesPagingSourceApplication(
    private val api: ITunesApi,
    private val term: String
) : PagingSource<Int, Result>() {


    override fun getRefreshKey(state: PagingState<Int, Result>): Int? {
        TODO("Not yet implemented")
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Result> {
        val position = params.key ?: 1
        return try {
            val response = api.searchTrack(term,"software",params.loadSize)
            val applications = response.results

            LoadResult.Page(
                data = applications,
                prevKey = if (position == 1) null else position - 1,
                nextKey = if (applications.isEmpty()) null else position + 1
            )
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }


    }


}