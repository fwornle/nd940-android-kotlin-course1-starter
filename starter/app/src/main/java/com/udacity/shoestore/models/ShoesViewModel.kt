package com.udacity.shoestore.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import timber.log.Timber

class ShoesViewModel: ViewModel() {

    // define store inventory as LiveData - encapsulate in ViewModel w/h getter
    private var _storeInventory = MutableLiveData<ArrayList<Shoe>>()
    var storeInventory: LiveData<ArrayList<Shoe>> = _storeInventory

    // new shoe
    var newShoeMask = MutableLiveData<Shoe>()

    // initialize ShoeViewModel class
    init {
        Timber.i("ViewModel created.")

        // initialize store inventory (LiveData)
        _storeInventory.value = initShoeList()

        // initialize shoe mask with empty Shoe
        newShoeMask.value = Shoe(
            name = "",
            company = "",
            description = "",
            size = -1.0,
        )

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
                company = "Salamander",
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
                company = "Salamander",
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
}