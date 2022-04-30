package com.aqsin.cryptotracker.ui.history

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aqsin.cryptotracker.data.model.toModelList
import com.aqsin.cryptotracker.events.CryptoUiEvents
import com.aqsin.cryptotracker.repository.local.CryptoDbHistoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject



//Inject CryptoDbHistoryRepository

@HiltViewModel
class CryptoHistoryViewModel @Inject constructor(
    var historyRepo : CryptoDbHistoryRepository
) : ViewModel() {

    val uiEvent = MutableLiveData<CryptoUiEvents>()

    fun getHistory(){
        viewModelScope.launch {
            val history = historyRepo.getAllHistory()
            uiEvent.postValue(CryptoUiEvents.LoadHistory(history.toModelList()))
        }
    }
}


// or

//Inject Directly room
/*
@HiltViewModel
class CryptoHistoryViewModel @Inject constructor(
    var roomDb : RoomDB
) : ViewModel() {

    val uiEvent = MutableLiveData<CryptoUiEvents>()

    fun getHistory(){
        viewModelScope.launch {
            val history = roomDb.cryptoHistoryDao().getAllHistory()
            uiEvent.postValue(CryptoUiEvents.LoadHistory(history.toModelList()))
        }
    }
}
*/

// or

//Inject Directly HistoryDao
/*
@HiltViewModel
class CryptoHistoryViewModel @Inject constructor(
    var historyData : CryptoHistoryDao
) : ViewModel() {

    val uiEvent = MutableLiveData<CryptoUiEvents>()

    fun getHistory(){
        viewModelScope.launch {
            val history = historyData.getAllHistory()
            uiEvent.postValue(CryptoUiEvents.LoadHistory(history.toModelList()))
        }
    }
}
*/





