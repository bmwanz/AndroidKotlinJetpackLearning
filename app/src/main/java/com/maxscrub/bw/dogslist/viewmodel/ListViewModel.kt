package com.maxscrub.bw.dogslist.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.maxscrub.bw.dogslist.model.DogBreed
import com.maxscrub.bw.dogslist.model.DogDatabase
import com.maxscrub.bw.dogslist.model.DogsApiService
import com.maxscrub.bw.dogslist.util.SharedPreferencesHelper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch

class ListViewModel(application: Application) : BaseViewModel(application) {

    private var prefHelper = SharedPreferencesHelper(getApplication())

    private val dogsService = DogsApiService()
    private val disposable = CompositeDisposable()


    // LiveData variables
    val dogs = MutableLiveData<List<DogBreed>>()
    val dogsLoadError = MutableLiveData<Boolean>() // true=error, false=noError
    val loading = MutableLiveData<Boolean>()       // display loading spinner

    fun refresh() {
        // refresh information

        fetchFromRemote()
    }

    private fun fetchFromRemote() {
        loading.value = true
        disposable.add(
            dogsService.getDogs()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object: DisposableSingleObserver<List<DogBreed>>() {

                    override fun onSuccess(dogList: List<DogBreed>) {
                       storeDogsLocally(dogList)
                    }

                    override fun onError(e: Throwable) {
                        dogsLoadError.value = true
                        loading.value = false
                        e.printStackTrace()
                    }

                })
        )
    }

    private fun dogsRetrieved(dogList: List<DogBreed>) {
        dogs.value = dogList
        dogsLoadError.value = false
        loading.value = false
    }

    private fun storeDogsLocally(list: List<DogBreed>) {
        // run as coroutine in a separate thread
        launch {
            // clear before putting new list of dogs in
            val dao = DogDatabase(getApplication()).dogDao()
            dao.deleteAllDogs()

            // gets a list and expands to individual elements to pass into insertAll function
            val result = dao.insertAll(*list.toTypedArray())

            var i = 0
            while (i < list.size) {
                list[i].uuid = result[i].toInt()
                i++
            }
            dogsRetrieved(list)
        }
        prefHelper.saveUpdateTime(System.nanoTime())
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}