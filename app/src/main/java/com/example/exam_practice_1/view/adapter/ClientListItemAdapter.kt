package com.example.exam_practice_1.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.exam_practice_1.databinding.ClientListItemBinding
import com.example.exam_practice_1.databinding.ListItemBinding
import com.example.exam_practice_1.model.Produs


typealias OnClickItemListener = (produs: Produs) -> Unit


class ClientListItemAdapter(private val onClickItemListener: OnClickItemListener) :
    ListAdapter<Produs, ClientProdusViewHolder>(ProdusDiffUtilItemCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ClientProdusViewHolder(
        ClientListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    ,onClickItemListener)

    override fun onBindViewHolder(holder: ClientProdusViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}