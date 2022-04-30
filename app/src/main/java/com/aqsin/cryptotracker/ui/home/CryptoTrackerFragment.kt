package com.aqsin.cryptotracker.ui.home


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.aqsin.cryptotracker.R
import com.aqsin.cryptotracker.alert.CryptoMinMaxAlert
import com.aqsin.cryptotracker.data.model.CryptoEntity
import com.aqsin.cryptotracker.data.model.CryptoFilterEntity
import com.aqsin.cryptotracker.databinding.CryptoTrackerFragmentBinding
import com.aqsin.cryptotracker.events.CryptoUiEvents
import dagger.hilt.android.AndroidEntryPoint



@AndroidEntryPoint
class CryptoTrackerFragment : Fragment() {

    private var cryptoTrackerBinding : CryptoTrackerFragmentBinding? = null
    val binding get() = cryptoTrackerBinding!!

     val viewModel: CryptoTrackerViewModel by viewModels()
     var adapter  = CryptoRatesAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        adapter.onClickEvent = {
            viewModel.getCryptoFilter(it.name)
        }

        observeEvents()
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        cryptoTrackerBinding = CryptoTrackerFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rcyCryptoRates.layoutManager = LinearLayoutManager(requireContext())
        binding.rcyCryptoRates.adapter = adapter

        // Call Api Here
        // After return from History for fresh rates
        viewModel.getCrypto()

        binding.btnShowHistory.setOnClickListener {
          findNavController().navigate(R.id.action_homeFragment_to_cryptoHistoryFragment)
        }
    }

    private fun observeEvents() {
        lifecycleScope.launchWhenCreated {
            viewModel.uiEvent.observe(requireActivity()){ event->
                when(event){
                    is CryptoUiEvents.LoadCrypto -> {
                        event.data?.let {
                            adapter.setData(it.crypto)
                        }
                    }
                    is CryptoUiEvents.OnMessage -> {
                        ShowMessage(event.data)
                    }
                    is CryptoUiEvents.OnFilter -> {
                        openFilterDialog(event.data)
                    }
                }
            }
        }
    }

    private fun openFilterDialog(data : CryptoFilterEntity){
        CryptoMinMaxAlert.showMinMaxAlert(requireContext(),
            CryptoMinMaxAlert.MinMaxData(
                data.name, data.minValue,data.maxValue
            ), object : CryptoMinMaxAlert.MinMaxAction {
                override fun onPopupConfirm(data: CryptoMinMaxAlert.MinMaxData) {
                    viewModel.saveCryptoFilter(data.title,data.minValue,data.maxValue)
                }
            })
    }

    private fun ShowMessage(message : String){
        Toast.makeText(requireContext(),message, Toast.LENGTH_LONG).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        cryptoTrackerBinding = null
    }
}