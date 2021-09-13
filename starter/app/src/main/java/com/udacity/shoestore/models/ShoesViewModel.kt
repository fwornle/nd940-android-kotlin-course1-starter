package com.udacity.shoestore.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import timber.log.Timber

class ShoesViewModel: ViewModel() {

    // define store inventory as LiveData - encapsulate in ViewModel w/h getter
    private var _storeInventory = MutableLiveData<ArrayList<Shoe>>()
    var storeInventory: LiveData<ArrayList<Shoe>> = _storeInventory

    // initialize class
    init {
        Timber.i("ViewModel created.")

        // initialize store inventory (LiveData)
        _storeInventory.value = initShoeList()
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
                name = "Lace-up ankle boots",
                size = 45.0,
                company = "Salamander",
                description = "Gray winter boot for men",
                images = mutableListOf(
                    "https://img01.ztat.net/article/spp-media-p1/03832823a5d84a6cad2632d3e430a79d/3b1c8921821f4654be778b20bff44ac0.jpg",
                    "https://img01.ztat.net/article/spp-media-p1/da11809b1de14a73a060d572cc3aa247/ea73e9f37a6043f6ba04a16a2c96b561.jpg",
                    "https://img01.ztat.net/article/spp-media-p1/0b8eb2dd745845f9a57e50d298df1189/6eb7dc139fe841179bb9c321eb9c4157.jpg",
                    "https://img01.ztat.net/article/spp-media-p1/65a68d7c4936401a8949b81f756e1f9b/893da73ffb1e4c8e9e2228cdd62962f4.jpg",
                    "https://img01.ztat.net/article/spp-media-p1/4292cd50ca364c029d79d6cf3a4cb6f6/95a9ea6ce4f846269030796650046426.jpg",
                )
            ),
            Shoe(
                name = "Avato Sneaker",
                size = 43.0,
                company = "Salamander",
                description = "Leather and textile men's shoe",
                images = mutableListOf(
                    "https://salamander-cdn.b-cdn.net/media/image/cd/c0/6a/900000000-385190-0KrIt8ua5LeNmt_200x200.jpg",
                    "https://salamander-cdn.b-cdn.net/media/image/73/a7/dd/900000000-385190-0-00010029052760000GtKAqPcO4HGHP_200x200.jpg",
                    "https://salamander-cdn.b-cdn.net/media/image/30/34/d0/900000000-385190-0-00010029052780000fvg0B3oCeq8qq_200x200.jpg",
                    "https://salamander-cdn.b-cdn.net/media/image/4f/96/18/900000000-385190-0-00010029052800000lQ4n8vLIpARF1_200x200.jpg",
                )
            ),
            Shoe(
                name = "Henderson Herren",
                size = 41.0,
                company = "Salamander",
                description = "Brown loafer",
                images = mutableListOf(
                    "https://encrypted-tbn1.gstatic.com/shopping?q=tbn:ANd9GcSM5EXXf2d4hzsJQ-lnpX5yaLleBk8xT87uudXQOVbL_neUzTBzMkwAcs-IfuBpTQ&usqp=CAc",
                )
            ),
            Shoe(
                name = "Stiefelette Glattleder",
                size = 39.0,
                company = "Salamander",
                description = "Brown women's shoe",
                images = mutableListOf(
                    "https://salamander-cdn.b-cdn.net/media/image/0b/7d/6c/900000000-478903-0G7o451Q61IaGw_200x200.jpg",
                    "https://salamander-cdn.b-cdn.net/media/image/97/ba/74/900000000-478903-0-00010004056990000vceXhCWR90gwP_200x200.jpg",
                    "https://salamander-cdn.b-cdn.net/media/image/28/27/02/900000000-478903-0-00010004057030000Lb4FVDSaDsSiq_200x200.jpg",
                    "https://salamander-cdn.b-cdn.net/media/image/29/2b/0d/900000000-478903-0-00010004057050000IMECxd260Kb9O_200x200.jpg",
                )
            ),
        )
    }
}