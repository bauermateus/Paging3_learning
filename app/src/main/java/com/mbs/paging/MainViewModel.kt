package com.mbs.paging

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: MainRepository): ViewModel() {

    private val _list = MutableLiveData<List<ResponseModelItem>>()
    val list: LiveData<List<ResponseModelItem>> = _list

    init {
        request()
    }

    private fun request() {
        viewModelScope.launch(Dispatchers.IO) {
        val call = repository.request()
            call.enqueue(object : Callback<List<ResponseModelItem>> {
                override fun onResponse(
                    call: Call<List<ResponseModelItem>>,
                    response: Response<List<ResponseModelItem>>
                ) {
                    if (response.isSuccessful) _list.postValue(response.body())
                }

                override fun onFailure(call: Call<List<ResponseModelItem>>, t: Throwable) {
                    t.printStackTrace()
                }

            })
        }
    }
}