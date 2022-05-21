package com.abdul.geometrycalc.screens.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.abdul.geometrycalc.databinding.ItemMainBinding
import com.abdul.geometrycalc.model.FigureModel
import com.abdul.geometrycalc.model.callbacks.RecyclerViewItemClickCallback

class RootAdapter(
    private val callback: RecyclerViewItemClickCallback
) : RecyclerView.Adapter<RootAdapter.RootViewHolder>() {

    inner class RootViewHolder(private val binding: ItemMainBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun initContent(item: FigureModel) {
            binding.root.setOnClickListener {
                callback.onItemClick(item)
            }

            binding.tvMain.text = item.name
            binding.imgMain.setImageResource(item.img) //= item.img
        }
    }

    var listFigure = emptyList<FigureModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RootViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return RootViewHolder(ItemMainBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: RootViewHolder, position: Int) {
        holder.initContent(listFigure[position])
    }

    override fun getItemCount(): Int {
        return listFigure.size
    }

    fun setFigureList(list: List<FigureModel>) {
        listFigure = list
        notifyDataSetChanged()
    }



}