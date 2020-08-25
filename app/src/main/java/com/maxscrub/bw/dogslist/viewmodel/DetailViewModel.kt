package com.maxscrub.bw.dogslist.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.maxscrub.bw.dogslist.model.DogBreed
import com.maxscrub.bw.dogslist.model.DogDatabase
import kotlinx.coroutines.launch

class DetailViewModel(application: Application) : BaseViewModel(application) {

    val dogLiveData = MutableLiveData<DogBreed>()

     fun fetch(uuid: Int) {
         launch {
             val dog = DogDatabase(getApplication()).dogDao().getDog(uuid)
             dogLiveData.value = dog
         }
    }
}