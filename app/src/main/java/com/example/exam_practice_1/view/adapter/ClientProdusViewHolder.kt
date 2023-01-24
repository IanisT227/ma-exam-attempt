package com.example.exam_practice_1.view.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.exam_practice_1.databinding.ClientListItemBinding
import com.example.exam_practice_1.databinding.ListItemBinding
import com.example.exam_practice_1.model.Produs

class ClientProdusViewHolder(
    private val binding: ClientListItemBinding,
    private val onClickItemListener: OnClickItemListener
) :
    RecyclerView.ViewHolder(binding.root) {
    private var produsVH: Produs? = null

    init {
        binding.root.setOnClickListener {
            produsVH?.let { produs: Produs -> onClickItemListener(produs) }
        }
    }

    fun bind(produs: Produs) {
        produsVH = produs
        binding.numeTV.text = produs.nume
        binding.descriereTV.text = produs.descriere
        binding.pretTV.text = produs.pret.toString()
        binding.statusTV.text = produs.status.toString()
    }
}
