package com.udacity.shoestore

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.udacity.shoestore.databinding.FragmentShoeDetailBinding

class ShoeDetailFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {

        // Inflate the layout for login fragment
        val binding: FragmentShoeDetailBinding = DataBindingUtil.inflate<FragmentShoeDetailBinding>(
            inflater, R.layout.fragment_shoe_detail, container, false)

        // hook up button to navController: Cancel
        binding.btCancel.setOnClickListener { view: View ->
            Navigation.findNavController(view).navigate(R.id.action_shoeDetailFragment_to_shoeListFragment)
        }

        // hook up button to navController: Save
        binding.btSave.setOnClickListener { view: View ->
            Navigation.findNavController(view).navigate(R.id.action_shoeDetailFragment_to_shoeListFragment)
        }

        // enable overlay menu
        setHasOptionsMenu(true)

        // return View object
        return binding.root
    }

    // inflate menu
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.overflow_menu, menu)
    }

    // instruct "selecting the menu item" to navigate to the configured destination (menu item id)
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item, requireView().findNavController())
                || super.onOptionsItemSelected(item)
    }
}
