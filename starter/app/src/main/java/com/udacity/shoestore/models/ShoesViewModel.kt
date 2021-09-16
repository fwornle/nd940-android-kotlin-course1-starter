package com.udacity.shoestore.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import timber.log.Timber

class ShoesViewModel: ViewModel() {

    // define store inventory as LiveData - private to ShoesViewModel w/h getter for r/o access
    private var _storeInventory = MutableLiveData<ArrayList<Shoe>>()
    var storeInventory: LiveData<ArrayList<Shoe>> = _storeInventory

    // intermediate LifeData object for shoe data entry
    var newShoe = MutableLiveData<Shoe>()

    // intermediate LifeData object for (validated) entry of shoe size data
    var newShoeSizeString = MutableLiveData<String>()

    // initialize ShoeViewModel class
    init {
        Timber.i("ViewModel created.")

        // initialize store inventory (LiveData)
        _storeInventory.value = initShoeList()

        // initialize shoe mask: empty Shoe / Shoe with default data (development)
        newShoe.value = initShoe()

        // initialized intermediate LiveData for String-to-Double transformatin (and input checking)
        newShoeSizeString.value = ""

    }

    override fun onCleared() {
        super.onCleared()
        Timber.i("ViewModel destroyed.")
    }

    // define shoe list
    private fun initShoeList(): ArrayList<Shoe> {

        // initialize shoe list
        return arrayListOf(
            Shoe(
                name = "Sneaker 'Twister'",
                size = 40.0,
                company = "Tagger",
                description = "Blue, running, men, textile",
                images = mutableListOf(
                    "shoe1",
                    "shoe1",
                    "shoe1",
                )
            ),
            Shoe(
                name = "Sneaker 'Dumbo'",
                size = 38.5,
                company = "Target",
                description = "Blue, leisure, unisex, textile",
                images = mutableListOf(
                    "shoe2",
                    "shoe2",
                    "shoe2",
                )
            ),
            Shoe(
                name = "Sneaker 'Space'",
                size = 41.0,
                company = "Nike",
                description = "Black/Red, running, men, textile",
                images = mutableListOf(
                    "shoe3",
                    "shoe3",
                    "shoe3",
                )
            ),
            Shoe(
                name = "Avato Sneaker",
                size = 43.0,
                company = "Coors",
                description = "White, indoors, men, leather",
                images = mutableListOf(
                    "shoe4",
                    "shoe4",
                    "shoe4",
                )
            ),
            Shoe(
                name = "Sneaker 'Air'",
                size = 43.0,
                company = "Coors",
                description = "Blue, indoors, men, textile",
                images = mutableListOf(
                    "shoe5",
                    "shoe5",
                    "shoe5",
                )
            ),
            Shoe(
                name = "Sneaker 'Cobra'",
                size = 40.5,
                company = "Nike",
                description = "Black, indoors, men, textile",
                images = mutableListOf(
                    "shoe6",
                    "shoe6",
                    "shoe6",
                )
            ),
            Shoe(
                name = "Sneaker 'Wind'",
                size = 41.0,
                company = "Nike",
                description = "Blue, running, men, textile",
                images = mutableListOf(
                    "shoe7",
                    "shoe7",
                    "shoe7",
                )
            ),
            Shoe(
                name = "Pumps",
                size = 36.0,
                company = "Tiffany",
                description = "Yellow, 10 inch heels, women, leather",
                images = mutableListOf(
                    "shoe8",
                    "shoe8",
                    "shoe8",
                )
            ),
            Shoe(
                name = "Sneaker 'Trouble'",
                size = 40.0,
                company = "Nike",
                description = "Black, running, men, textile",
                images = mutableListOf(
                    "shoe9",
                    "shoe9",
                    "shoe9",
                )
            ),
            Shoe(
                name = "Loafer 'Marine'",
                size = 39.0,
                company = "Gap",
                description = "Blue, leisure, unisex, textile",
                images = mutableListOf(
                    "shoe10",
                    "shoe10",
                )
            ),
            Shoe(
                name = "Sneaker 'Queen'",
                size = 36.0,
                company = "Nike",
                description = "White, indoor, women, leather",
                images = mutableListOf(
                    "shoe11",
                    "shoe11",
                    "shoe11",
                )
            ),
        )
    }

    // append a new shoe entry to the list of shoes in the store
    fun addShoeToInventory() {

        // set new shoe size, turning user input to valid shoe size (or "-2.0" as error indicator)
        newShoe.value?.size = newShoeSizeString.value?.let {

            // only allow for full and half sizes
            Math.round(it.toDouble() * 2.0) / 2.0

        } ?: -2.0  // "-2.0" (a mere error indicator for debugging)


        // add newShoe to the beginning of the list of shoes
        _storeInventory.value?.add(0, newShoe.value!!)

        // perform some logging to keep track of data flow in log messages
        Timber.i("Store updated: ${_storeInventory.value?.size} entries, ${_storeInventory.value}")

    }

    // return an "empty" shoe
    fun initShoe(): Shoe {
        return Shoe(
//            name = "Test shoe",
//            company = "Runner's High",
//            description = "It's a brand new shoe",
//            size = -1.0,  // invalid
            name = "",
            company = "",
            description = "",
            size = -1.0,  // invalid
        )
    }

}