package com.aqsin.cryptotracker.ui.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.aqsin.cryptotracker.databinding.CryptoHistoryBinding
import com.aqsin.cryptotracker.events.CryptoUiEvents
import com.aqsin.cryptotracker.ui.home.CryptoTrackerViewModel
import androidx.recyclerview.widget.DividerItemDecoration
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CryptoHistoryFragment : Fragment() {

    private var cryptoHistoryBining : CryptoHistoryBinding? = null
    val binding get() = cryptoHistoryBining!!

    val viewModel: CryptoHistoryViewModel by viewModels()
    var adapter  = CryptoHistoryAdapter()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getHistory()
        lifecycleScope.launchWhenCreated {
            viewModel.uiEvent.observe(requireActivity()){ event->
                when(event){
                    is CryptoUiEvents.LoadHistory -> {
                        event.history?.let {
                            adapter.setData(it)
                        }
                    }
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        cryptoHistoryBining = CryptoHistoryBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.cryptoHistory.layoutManager = LinearLayoutManager(requireContext())
        val divider = DividerItemDecoration(
            requireContext(),
            LinearLayoutManager.VERTICAL
        )
        binding.cryptoHistory.addItemDecoration(divider)
        binding.cryptoHistory.adapter = adapter
    }


    override fun onDestroy() {
        super.onDestroy()
        cryptoHistoryBining = null
    }
}