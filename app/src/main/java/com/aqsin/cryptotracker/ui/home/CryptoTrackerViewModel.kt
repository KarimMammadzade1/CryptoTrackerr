package com.aqsin.cryptotracker.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aqsin.cryptotracker.data.model.*
import com.aqsin.cryptotracker.db.CryptoFilterDao
import com.aqsin.cryptotracker.db.RoomDB
import com.aqsin.cryptotracker.events.CryptoUiEvents
import com.aqsin.cryptotracker.repository.remote.CryptoApiRepository
import com.aqsin.cryptotracker.repository.local.CryptoDbFilterRepository
import com.aqsin.helpers.Constants
//import com.aqsin.cryptotracker.repository.local.CryptoDbFilterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject


//Inject RoomDb

/*
@HiltViewModel
class CryptoTrackerViewModel @Inject constructor(
    val repository: CryptoApiRepository,
    var roomDb : RoomDB
) : ViewModel() {

}

*/

@HiltViewModel
class CryptoTrackerViewModel @Inject constructor(
    val repository: CryptoApiRepository,
    var filteRepository : CryptoFilterDao
    ) : ViewModel() {

    val uiEvent = MutableLiveData<CryptoUiEvents>()

    fun getCryptoFilter(name : String) {
        viewModelScope.launch {
            val crypto = filteRepository.getCryptoFilterByName(name) ?: CryptoFilterEntity(name = name)
            uiEvent.postValue(CryptoUiEvents.OnFilter(crypto))
        }
    }

    fun saveCryptoFilter(name : String, min : Float, max : Float){
        viewModelScope.launch {
            filteRepository.insert(CryptoFilterEntity(name = name, minValue = min,maxValue = max))
        }
    }

    fun getCrypto(){
        viewModelScope.launch {
             repository.getCrypto(Constants.CryptoNames,Constants.CryptoCurrency)
                .onStart {
                    //show Loading
                }
                .catch {
                    //show Error
                    uiEvent.postValue(CryptoUiEvents.OnMessage("Error"))
                }
                .collect{
                    uiEvent.postValue(CryptoUiEvents.LoadCrypto(it.toModel()))
                }

        }
    }
}