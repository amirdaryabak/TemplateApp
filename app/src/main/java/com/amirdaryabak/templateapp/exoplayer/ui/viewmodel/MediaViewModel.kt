package com.amirdaryabak.templateapp.exoplayer.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.amirdaryabak.templateapp.exoplayer.model.MediaObject
import com.amirdaryabak.templateapp.exoplayer.repository.MediaRepo

class MediaViewModel: ViewModel() {
    private val mediaData: MutableLiveData<MutableList<MediaObject>> = MediaRepo().getMediaData()
    fun getMedia(): MutableLiveData<MutableList<MediaObject>>{
        return mediaData
    }
}