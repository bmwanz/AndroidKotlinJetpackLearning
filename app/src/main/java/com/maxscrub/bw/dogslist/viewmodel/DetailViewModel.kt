package com.maxscrub.bw.dogslist.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.maxscrub.bw.dogslist.model.DogBreed

class DetailViewModel : ViewModel() {

    val dogLiveData = MutableLiveData<DogBreed>()

    fun fetch() {

        val dog = DogBreed("1", "Corgi", "15 years", "breedGroup", "bredFor", "temperament", "")
        dogLiveData.value = dog
    }
}