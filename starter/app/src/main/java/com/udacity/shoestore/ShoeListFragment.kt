package com.udacity.shoestore

import android.content.res.Resources
import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.udacity.shoestore.databinding.FragmentShoeListBinding
import com.udacity.shoestore.models.ShoesViewModel
import timber.log.Timber
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.children
import androidx.lifecycle.lifecycleScope
import com.udacity.shoestore.models.Shoe
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import org.w3c.dom.Text
import java.net.URL


class ShoeListFragment : Fragment() {

    private lateinit var viewModel: ShoesViewModel
    private lateinit var binding: FragmentShoeListBinding
    private lateinit var packageName: String

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {

        // Inflate the layout for login fragment
        binding = DataBindingUtil.inflate<FragmentShoeListBinding>(
            inflater, R.layout.fragment_shoe_list, container, false)

        // configure FloatingActionButton
        binding.fabDetails.setOnClickListener { view: View ->
            Navigation.findNavController(view).navigate(R.id.action_shoeListFragment_to_shoeDetailFragment)
        }

        // get reference to view model
        viewModel =  ViewModelProvider(this)
            .get(ShoesViewModel::class.java)
        Timber.i("Reference to viewModel obtained.")

        // bind the ShoesViewModel to the fragment (to make LiveData accessible directly in the UI)
        binding.shoesViewModel = viewModel
        binding.setLifecycleOwner(this)

        // fetch package name - needed to assemble Resource IDs
        // http://daniel-codes.blogspot.com/2009/12/dynamically-retrieving-resources-in.html
        packageName = getActivity()?.getPackageName() ?: "com.udacity.shoestore"

        // install observer to generate shoe list view (everytime the assoc. LiveData changes)
        viewModel.storeInventory.observe(this.viewLifecycleOwner, { newShoeList ->

            // re-create view based on new shoe list data
            createInventoryView(newShoeList)

        })

        // enable overlay menu
        setHasOptionsMenu(true)

        // return View object
        return binding.root
    }

    // programmatically add inventory as child views to the designated parent view (ID: llShoeList)
    private fun createInventoryView(shoeList: ArrayList<Shoe>?) {

        // null safety - LifeData can be 'null'
        shoeList?.let {

            for (shoe in it) {

                // inflate shoe_entry layout (used as "row template")
                // ... inspired by:
                // https://www.geeksforgeeks.org/how-to-add-views-dynamically-and-store-data-in-arraylist-in-android/
                val llShoeEntry: LinearLayout =
                    View.inflate(context, R.layout.shoe_entry, null) as LinearLayout

                // display first image (if any)
                val imgRes = resources.getIdentifier(shoe.images.first(), "drawable", packageName)

                // complete all template entries with data from currently selected shoe
                for (view in llShoeEntry.children) {
                    when (view.tag) {
                        "shoeImage" -> (view as ImageView).setImageResource(imgRes)
                        "shoeName" -> (view as TextView).text = shoe.name
                        "shoeSize" -> (view as TextView).text = String.format(
                            resources.getString(R.string.shoe_size),
                            shoe.size.toString()
                        )
                        "shoeDesc" -> (view as TextView).text = shoe.description
                    }
                }

                // add completed row layout of current shoe to view
                // ... second parameter seems unnecessary - tbc. (fw-210914)
                binding.llShoeList.addView(llShoeEntry, binding.llShoeList.childCount)

                // log-it
                Timber.i("Shoe added to fragment view: ${shoe.name}")

            } // all shoes in shoeList

        } // null safety

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
