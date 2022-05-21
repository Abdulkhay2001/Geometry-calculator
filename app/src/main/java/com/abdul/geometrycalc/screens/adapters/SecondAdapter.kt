package com.abdul.geometrycalc.screens.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.abdul.geometrycalc.R
import com.abdul.geometrycalc.databinding.ItemSecondBinding
import com.abdul.geometrycalc.model.FigureFormulas
import com.abdul.geometrycalc.model.callbacks.RecyclerViewItemClickCallback

class SecondAdapter(
    private val callback: RecyclerViewItemClickCallback
):RecyclerView.Adapter<SecondAdapter.SecondViewHolder>() {

        inner class SecondViewHolder(private val binding: ItemSecondBinding) :
            RecyclerView.ViewHolder(binding.root) {
            fun initContent(item: FigureFormulas) {
                binding.root.setOnClickListener {
                    callback.onItemClick(item)
                }
                binding.imgSecond.setImageResource(item.img)
                binding.tvSecond.text = item.name
                binding.tv2Second.text = item.formula_dop_info
            }

            }

    var listFormul = emptyList<FigureFormulas>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SecondViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return SecondViewHolder(ItemSecondBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: SecondViewHolder, position: Int) {
        holder.initContent(listFormul[position])
    }

    override fun getItemCount(): Int {
        return listFormul.size
    }

    fun setFormulList(list: List<FigureFormulas>){
        listFormul = list
        notifyDataSetChanged()
    }


}