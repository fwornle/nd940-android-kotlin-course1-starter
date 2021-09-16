package com.udacity.shoestore

import android.os.Bundle
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
import com.udacity.shoestore.models.Shoe

class ShoeListFragment : Fragment() {

    private lateinit var viewModel: ShoesViewModel
    private lateinit var binding: FragmentShoeListBinding
    private lateinit var packageName: String

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {

        // fetch package name - needed to assemble Resource IDs
        // http://daniel-codes.blogspot.com/2009/12/dynamically-retrieving-resources-in.html
        packageName = getActivity()?.getPackageName() ?: "com.udacity.shoestore"

        // Inflate the layout for login fragment
        binding = DataBindingUtil.inflate<FragmentShoeListBinding>(
            inflater, R.layout.fragment_shoe_list, container, false)

        // configure FloatingActionButton
        binding.fabDetails.setOnClickListener { view: View ->
            Navigation.findNavController(view).navigate(R.id.action_shoeListFragment_to_shoeDetailFragment)
        }

        // get reference to view model
        // ... bind lifecycle scope to activity (to allow sharing of ViewModel across all fragments)
        // ... see: answer to question https://knowledge.udacity.com/questions/691693
        viewModel =  ViewModelProvider(requireActivity())
            .get(ShoesViewModel::class.java)
        Timber.i("Reference to viewModel obtained.")

        // fragment (also) listens to LiveData in ViewModel --> is this needed here?
        binding.setLifecycleOwner(this)

        // install observer to generate shoe list view (everytime the assoc. LiveData changes)
        viewModel.storeInventory.observe(this.viewLifecycleOwner, { newShoeList ->

            // re-create view based on new shoe list data
            updateInventoryView(binding.llShoeList, newShoeList)

        })

        // enable overlay menu
        setHasOptionsMenu(true)

        // return View object
        return binding.root
    }

    // programmatically add inventory as child views to the designated parent view (ID: llShoeList)
    private fun updateInventoryView(listNodeView: LinearLayout, shoeList: ArrayList<Shoe>?) {

        // null safety - LifeData can be 'null'
        shoeList?.let {

            Timber.i("Adding shoe list to fragment...")

            for (shoe in it) {

                // inflate shoe_entry layout (used as "row template")
                // ... inspired by:
                // https://www.geeksforgeeks.org/how-to-add-views-dynamically-and-store-data-in-arraylist-in-android/
                val llShoeEntry: LinearLayout =
                    View.inflate(context, R.layout.shoe_entry, null) as LinearLayout

                // display first image (if any)
                var imgRes: Int?
                if (!shoe.images.isEmpty()) {
                    // image resource ID stored in list --> fetch first element
                    imgRes = resources.getIdentifier(shoe.images.first(), "drawable", packageName)
                } else {
                    // no image
                    imgRes = null
                }

                // complete all template entries with data from currently selected shoe
                for (view in llShoeEntry.children) {
                    when (view.tag) {
                        "shoeImage" -> imgRes?.let { (view as ImageView).setImageResource(it) }
                        "shoeName" -> (view as TextView).text = shoe.name
                        "shoeDesc" -> (view as TextView).text = shoe.description
                        "shoeSize" -> (view as TextView).text =
                            String.format(resources.getString(R.string.shoe_size), shoe.size.toString())
                    }
                }

                // add completed row layout of current shoe to view
                // ... second parameter seems unnecessary - tbc. (fw-210914)
                listNodeView.addView(llShoeEntry, listNodeView.childCount)
                Timber.i("Shoe entry added to fragment view: ${shoe.name}")

            } // all shoes in shoeList

            Timber.i("Shoe list added to fragment.")

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
