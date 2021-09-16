package com.udacity.shoestore

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.udacity.shoestore.databinding.FragmentShoeDetailBinding
import com.udacity.shoestore.models.ShoesViewModel
import timber.log.Timber

class ShoeDetailFragment : Fragment() {

    private lateinit var viewModel: ShoesViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {

        // Inflate the layout for login fragment
        val binding: FragmentShoeDetailBinding = DataBindingUtil.inflate<FragmentShoeDetailBinding>(
            inflater, R.layout.fragment_shoe_detail, container, false)

        // get reference to view model
        viewModel =  ViewModelProvider(this)
            .get(ShoesViewModel::class.java)
        Timber.i("Reference to viewModel obtained.")

        // give fragment access to LiveData in ViewModel
        // ... enabling "direct" two-way binding "fragment <--> ViewModel" (no need for observers)
        binding.shoesViewModel = viewModel

        // fragment (also) listens to LiveData in ViewModel
        binding.setLifecycleOwner(this)

        // hook up button to navController: Cancel
        binding.btCancel.setOnClickListener { view: View ->
            // navigate back to shoe list destination
            Navigation.findNavController(view).navigate(R.id.action_shoeDetailFragment_to_shoeListFragment)
        }

        // hook up button to navController: Save
        binding.btSave.setOnClickListener { view: View ->

            // add newly created shoe to inventory
            viewModel.addShoeToInventory()

            // navigate back to shoe list destination
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
