package com.example.fintecimaltest.ui.trips

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fintecimaltest.data.utils.Resource
import com.example.fintecimaltest.data.utils.autoCleared
import com.example.fintecimaltest.databinding.TripsFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TripsFragment : Fragment(), TripsAdapter.TripItemListener {

    private var binding: TripsFragmentBinding by autoCleared()
    private val viewModel: TripsViewModel by viewModels()
    private lateinit var adapter: TripsAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = TripsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupObservers()
    }

    private fun setupRecyclerView() {
        adapter = TripsAdapter(this)
        binding.tripsRv.layoutManager = LinearLayoutManager(requireContext())
        binding.tripsRv.adapter = adapter
    }

    private fun setupObservers() {
        viewModel.trips.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    binding.progressBar.visibility = View.GONE
                    if (!it.data.isNullOrEmpty()) adapter.setItems(ArrayList(it.data))
                }
                Resource.Status.ERROR ->
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()

                Resource.Status.LOADING ->
                    binding.progressBar.visibility = View.VISIBLE
            }
        })
    }

    override fun onClickedTrip(TripId: Int) {
        TODO("Not yet implemented")
    }
}