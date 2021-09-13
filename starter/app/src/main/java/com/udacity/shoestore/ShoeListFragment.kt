package com.udacity.shoestore

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.udacity.shoestore.databinding.FragmentShoeListBinding
import com.udacity.shoestore.models.ShoesViewModel
import timber.log.Timber

class ShoeListFragment : Fragment() {

    private lateinit var viewModel: ShoesViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {

        // Inflate the layout for login fragment
        val binding: FragmentShoeListBinding = DataBindingUtil.inflate<FragmentShoeListBinding>(
            inflater, R.layout.fragment_shoe_list, container, false)

        // get reference to view model
        viewModel =  ViewModelProvider(this)
            .get(ShoesViewModel::class.java)
        Timber.i("Reference to viewModel obtained.")


        // return View object
        return binding.root
    }
}
