package com.amirdaryabak.templateapp.ui.fragments

import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.amirdaryabak.templateapp.ui.viewmodels.MainViewModel
import com.amirdaryabak.templateapp.R
import dagger.hilt.android.AndroidEntryPoint
import es.dmoral.toasty.Toasty
import kotlinx.android.synthetic.main.fragment_eighth.*

@AndroidEntryPoint
class EighthFragmentOpenContextMenuToAView : Fragment(R.layout.fragment_eighth) {

    private val viewModel: MainViewModel by viewModels()

    val TAG = "EighthFragment"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        registerForContextMenu(menuButton)

    }

    override fun onCreateContextMenu(
        menu: ContextMenu,
        v: View,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        requireActivity().menuInflater.inflate(R.menu.floatin_context_menu, menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.item1 -> Toasty.warning(requireContext(), "Item 1").show()
            R.id.item2 -> Toasty.warning(requireContext(), "Item 2").show()
            R.id.item3 -> Toasty.warning(requireContext(), "Item 3").show()
        }
        return super.onContextItemSelected(item)
    }

}
