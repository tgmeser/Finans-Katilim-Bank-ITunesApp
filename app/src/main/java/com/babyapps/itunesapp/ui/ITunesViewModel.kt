package com.babyapps.itunesapp.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.babyapps.itunesapp.data.repository.ITunesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ITunesViewModel @Inject constructor(private val repository: ITunesRepository) : ViewModel() {

    companion object {
        const val DEFAULT_TERM = ""
    }

    private val currentTerm = MutableLiveData(DEFAULT_TERM)

    val tracks = currentTerm.switchMap { repository.searchTrack(it).cachedIn(viewModelScope) }
    val audiobooks =
        currentTerm.switchMap { repository.searchAudioBook(it).cachedIn(viewModelScope) }
    val musicVideos =
        currentTerm.switchMap { repository.searchMusicVideo(it).cachedIn(viewModelScope) }
    val applications =
        currentTerm.switchMap { repository.searchApplication(it).cachedIn(viewModelScope) }

    fun searchTrack(term: String) {
        currentTerm.value = term
    }

    fun searchAudioBook(term: String) {
        currentTerm.value = term
    }

    fun searchMusicVideo(term: String) {
        currentTerm.value = term
    }
    fun searchApplication(term: String) {
        currentTerm.value = term
    }

}