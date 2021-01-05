package com.amirdaryabak.templateapp.exoplayer.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.amirdaryabak.templateapp.R
import com.amirdaryabak.templateapp.exoplayer.model.MediaObject
import com.amirdaryabak.templateapp.exoplayer.ui.viewmodel.MediaViewModel
import com.amirdaryabak.templateapp.exoplayer.adapter.RecyclerViewScrollListener
import com.amirdaryabak.templateapp.exoplayer.adapter.FacebookRecyclerAdapter
import com.amirdaryabak.templateapp.exoplayer.adapter.PlayerViewAdapter.Companion.playIndexThenPausePreviousPlayer
import com.amirdaryabak.templateapp.exoplayer.adapter.PlayerViewAdapter.Companion.releaseAllPlayers
import kotlinx.android.synthetic.main.fragment_facebook_player.*

/**
 * A simple [Fragment] subclass.
 */
// create by Mostafa Anter
class FacebookScreenFragment : Fragment() {
    private lateinit var mAdapter: FacebookRecyclerAdapter
    private val modelList = ArrayList<MediaObject>()

    // for handle scroll and get first visible item index
    private lateinit var scrollListener: RecyclerViewScrollListener


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =
            inflater.inflate(R.layout.fragment_facebook_player, container, false)
        return view
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)
        setAdapter()

        // load data
        val model: MediaViewModel by viewModels()
        model.getMedia().observe(requireActivity(), {
            mAdapter.updateList(arrayListOf(*it.toTypedArray()))
        })
    }

    private fun setAdapter() {
        mAdapter = FacebookRecyclerAdapter(requireActivity(), modelList)
        recycler_view.setHasFixedSize(true)

        // use a linear layout manager
        val layoutManager = LinearLayoutManager(activity)
        recycler_view.layoutManager = layoutManager
        recycler_view.adapter = mAdapter
        scrollListener = object : RecyclerViewScrollListener() {
            override fun onItemIsFirstVisibleItem(index: Int) {
                Log.d("visible item index", index.toString())
                // play just visible item
                if (index != -1)
                    playIndexThenPausePreviousPlayer(index)
            }

        }
        recycler_view.addOnScrollListener(scrollListener)
        mAdapter.SetOnItemClickListener(object : FacebookRecyclerAdapter.OnItemClickListener {
            override fun onItemClick(view: View?, position: Int, model: MediaObject?) {

            }
        })
    }

    override fun onPause() {
        super.onPause()
        releaseAllPlayers()
    }
}