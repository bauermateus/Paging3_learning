package com.mbs.paging

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: MainRepository): ViewModel() {

    private val _list = MutableLiveData<List<ResponseModelItem>>()
    val list: LiveData<List<ResponseModelItem>> = _list

    var job: Job? = null

    init {
        request()
    }

    private fun request() {
        job = viewModelScope.launch(Dispatchers.IO) {
            repository.request()
                .catch { t ->
                    t.printStackTrace()
                }
                .collect {
                    _list.postValue(it)
                }
        }
    }
}