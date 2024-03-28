package com.example.advweek4nrp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.advweek4nrp.databinding.FragmentBagListBinding
import com.example.advweek4nrp.viewmodel.BagsViewModel

// TODO: Rename parameter arguments, choose names that match

class BagListFragment : Fragment() {
    private lateinit var binding: FragmentBagListBinding
    private lateinit var bagListAdapter: BagListAdapter
    private lateinit var viewModel: BagsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentBagListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(BagsViewModel::class.java)
        viewModel.refresh()

        bagListAdapter = BagListAdapter(arrayListOf())
        binding.recViewBag.layoutManager = LinearLayoutManager(context)
        binding.recViewBag.adapter = bagListAdapter



        binding.refreshLayout.setOnRefreshListener {
            binding.recViewBag.visibility = View.GONE
            binding.txtErrorr2.visibility = View.GONE
            binding.progressLoad2.visibility = View.VISIBLE
            viewModel.refresh()
            binding.refreshLayout.isRefreshing = false
        }
        observeViewModel()
    }

    fun observeViewModel() {
        viewModel.bagLD.observe(viewLifecycleOwner, Observer {
//            bagListAdapter.updateBagList(it)
            bagListAdapter.updateBagList(it)
        })

        viewModel.bagLoadErrorLD.observe(viewLifecycleOwner, Observer {
            if(it == true) {
                binding.txtErrorr2?.visibility = View.VISIBLE
            } else {
                binding.txtErrorr2?.visibility = View.GONE
            }
        })

        viewModel.loadingLD.observe(viewLifecycleOwner, Observer {
            if(it == true) {
                binding.recViewBag.visibility = View.GONE
                binding.progressLoad2.visibility = View.VISIBLE
            } else {
                binding.recViewBag.visibility = View.VISIBLE
                binding.progressLoad2.visibility = View.GONE
            }
        })


    }
//    private fun observeViewModel() {
//        viewModel.bagListLD.observe(viewLifecycleOwner, Observer<List<Bag>> { bags ->
//            bags?.let {
//                bagListAdapter.updateBagList(it)
//            }
//        })
//    }

}