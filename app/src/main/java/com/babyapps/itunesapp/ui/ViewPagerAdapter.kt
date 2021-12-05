package com.babyapps.itunesapp.ui

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import kotlinx.coroutines.ExperimentalCoroutinesApi

const val TRACK_PAGE_INDEX = 0
const val AUDIOBOOK_PAGE_INDEX = 1
const val MUSICVIDEO_PAGE_INDEX = 2
const val APPLICATION_PAGE_INDEX = 3

class ViewPagerAdapter(fragment: Fragment) :
    FragmentStateAdapter(fragment) {


    @ExperimentalCoroutinesApi
    private val tabFragmentsCreators: Map<Int, () -> Fragment> = mapOf(
        TRACK_PAGE_INDEX to { TrackFragment() },
        AUDIOBOOK_PAGE_INDEX to { AudioBookFragment() },
        MUSICVIDEO_PAGE_INDEX to { MusicVideoFragment() },
        APPLICATION_PAGE_INDEX to { AppFragment() },
    )

    @ExperimentalCoroutinesApi
    override fun getItemCount() = tabFragmentsCreators.size

    @ExperimentalCoroutinesApi
    override fun createFragment(position: Int): Fragment {
        return tabFragmentsCreators[position]?.invoke() ?: throw IndexOutOfBoundsException()
    }


}