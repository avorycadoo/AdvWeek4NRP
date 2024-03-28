package com.example.advweek4nrp.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.advweek4nrp.databinding.BagListItemBinding
import com.example.advweek4nrp.model.Bag
import com.example.advweek4nrp.model.Student

class BagListAdapter(val bagList: ArrayList<Bag>)
    : RecyclerView.Adapter<BagListAdapter.BagViewHolder>() {

    class BagViewHolder(var binding: BagListItemBinding)
        : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BagViewHolder {
        val binding = BagListItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false)
        return BagViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return bagList.size
    }

    override fun onBindViewHolder(holder: BagViewHolder, position: Int) {

        holder.binding.txtIDBags.text = bagList[position].id
        holder.binding.txtNameBag.text = bagList[position].name
    }

    fun updateBagList(newBagList: ArrayList<Bag>) {
        bagList.clear()
        bagList.addAll(newBagList)
        notifyDataSetChanged()
    }


}