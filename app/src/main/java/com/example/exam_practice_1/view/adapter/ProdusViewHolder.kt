package com.example.exam_practice_1.view.adapter

import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.exam_practice_1.databinding.ListItemBinding
import com.example.exam_practice_1.model.Produs

class ProdusViewHolder(private val binding: ListItemBinding) :
    RecyclerView.ViewHolder(binding.root) {
    init {

    }

    fun bind(produs: Produs) {
        binding.idTV.text = produs.id.toString()
        binding.numeTV.text = produs.nume
        binding.descriereTV.text = produs.descriere
        binding.pretTV.text = produs.pret.toString()
        binding.cantitateTV.text = produs.cantitate.toString()
        binding.statusTV.text = produs.status.toString()
    }
}
