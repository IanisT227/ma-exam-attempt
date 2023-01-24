package com.example.exam_practice_1.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.exam_practice_1.databinding.ListItemBinding
import com.example.exam_practice_1.model.Produs

class ListItemAdapter : ListAdapter<Produs, ProdusViewHolder>(ProdusDiffUtilItemCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ProdusViewHolder(
        ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: ProdusViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}