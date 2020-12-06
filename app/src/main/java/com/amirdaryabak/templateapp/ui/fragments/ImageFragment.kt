package com.amirdaryabak.templateapp.ui.fragments

import android.os.Build
import android.os.Bundle
import android.transition.TransitionInflater
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.amirdaryabak.templateapp.R

class ImageFragment : Fragment(R.layout.fragment_image) {

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val animation = TransitionInflater.from(requireContext()).inflateTransition(
            android.R.transition.move
        )
        sharedElementEnterTransition = animation
        sharedElementReturnTransition = animation
    }
}