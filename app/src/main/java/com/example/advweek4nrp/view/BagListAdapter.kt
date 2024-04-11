package com.example.advweek4nrp.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.advweek4nrp.databinding.BagListItemBinding
import com.example.advweek4nrp.model.Bag
import com.example.advweek4nrp.model.Student
import com.squareup.picasso.Picasso


class BagListAdapter(val bagList: ArrayList<Bag>)
    : RecyclerView.Adapter<BagListAdapter.BagViewHolder>() {
    class BagViewHolder(var binding: BagListItemBinding)
        : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BagViewHolder {
        val binding = BagListItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false)
        return BagListAdapter.BagViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return bagList.size
    }

    override fun onBindViewHolder(holder: BagViewHolder, position: Int) {

        holder.binding.txtBagId.text = bagList[position].id.toString()
        holder.binding.txtBagName.text = bagList[position].name
        holder.binding.txtBagItems.text = "Items: " + bagList[position].items?.joinToString(", ")
        holder.binding.txtColor.text = "Colors: " + bagList[position].details?.color.toString()
        holder.binding.txtSize.text = "Intelligent: " + bagList[position].details?.size

        var url = bagList[position].images
        val builder = Picasso.Builder(holder.binding.root.context)
        builder.listener { picasso, uri, exception ->
            exception.printStackTrace() }
        builder.build().load(url).into(holder.binding.imgBag)
    }

    fun updateBagList(newBagList: ArrayList<Bag>) {
        bagList.clear()
        bagList.addAll(newBagList)
        notifyDataSetChanged()
    }


}