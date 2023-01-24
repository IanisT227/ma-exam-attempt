package com.example.exam_practice_1.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.exam_practice_1.R
import com.example.exam_practice_1.databinding.MainFragmentBinding
import com.zhuinden.fragmentviewbindingdelegatekt.viewBinding

class MainFragment : Fragment(R.layout.main_fragment) {
    private val binding by viewBinding(MainFragmentBinding::bind)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.allBtn.setOnClickListener {
            findNavController().navigate(MainFragmentDirections.actionMainFragmentToAllProductsFragment())
        }

        binding.clientsBtn.setOnClickListener {
            findNavController().navigate(MainFragmentDirections.actionMainFragmentToClientFragment())
        }

        binding.storeBtn.setOnClickListener {
            findNavController().navigate(MainFragmentDirections.actionMainFragmentToStoreFragment())
        }
    }
}