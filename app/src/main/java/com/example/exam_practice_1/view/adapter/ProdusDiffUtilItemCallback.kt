package com.example.exam_practice_1.view.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.exam_practice_1.model.Produs

class ProdusDiffUtilItemCallback : DiffUtil.ItemCallback<Produs>() {
    override fun areItemsTheSame(oldItem: Produs, newItem: Produs) = oldItem == newItem

    override fun areContentsTheSame(oldItem: Produs, newItem: Produs) = oldItem == newItem

}
