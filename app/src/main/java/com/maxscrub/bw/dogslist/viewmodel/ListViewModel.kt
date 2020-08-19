package com.maxscrub.bw.dogslist.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.maxscrub.bw.dogslist.model.DogBreed

class ListViewModel : ViewModel() {

    // LiveData variables
    val dogs = MutableLiveData<List<DogBreed>>()
    val dogsLoadError = MutableLiveData<Boolean>() // true=error, false=noError
    val loading = MutableLiveData<Boolean>()       // display loading spinner

    fun refresh() {
        // refresh information

        // temporary initialize with test data
        val dog1 = DogBreed("1", "Corgi", "15 years", "breedGroup", "bredFor", "temperament", "")
        val dog2 = DogBreed("2", "Labrador", "10 years", "breedGroup", "bredFor", "temperament", "")
        val dog3 = DogBreed("3", "Rottweiler", "20 years", "breedGroup", "bredFor", "temperament", "")
        val dogList = arrayListOf(dog1, dog2, dog3)
        dogs.value = dogList
        dogsLoadError.value = false
        loading.value = false
    }
}