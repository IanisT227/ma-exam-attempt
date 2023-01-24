package com.example.exam_practice_1.view

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.exam_practice_1.R
import com.example.exam_practice_1.databinding.AllProductsFragmentBinding
import com.example.exam_practice_1.view.adapter.ListItemAdapter
import com.example.exam_practice_1.viewmodel.ExamViewModel
import com.tapadoo.alerter.Alerter
import com.zhuinden.fragmentviewbindingdelegatekt.viewBinding
import org.koin.androidx.viewmodel.ext.android.activityViewModel

class AllProductsFragment : Fragment(R.layout.all_products_fragment) {
    private val feedViewModel: ExamViewModel by activityViewModel()
    private val binding by viewBinding(AllProductsFragmentBinding::bind)
    private val adapter by lazy { initProduseAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
        initRecyclerView(LinearLayoutManager(requireContext()))
        initObservers()
    }

    private fun initProduseAdapter(): ListItemAdapter = ListItemAdapter()

    private fun initRecyclerView(layoutManager: LinearLayoutManager) {
        binding.recipeListRV.adapter = adapter
        binding.recipeListRV.layoutManager = layoutManager
        feedViewModel.getProductsList()
    }

    private fun initViews() {
        binding.toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }

        binding.retryConnectionBTN.setOnClickListener {
            feedViewModel.getProductsList()
        }
    }

    private fun initObservers() {
        feedViewModel.produsList.observe(viewLifecycleOwner) { list ->
            adapter.submitList(list)
            adapter.notifyDataSetChanged()
        }

        feedViewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            binding.isLoadingCIP.isVisible = isLoading
            binding.recipeListRV.isVisible = !isLoading
        }

        feedViewModel.isError.observe(viewLifecycleOwner) { isError ->
            if (isError != null) {
                Alerter.create(
                    requireActivity()
                ).setTitle("Error").setText(isError.toString())
                    .setBackgroundColorRes(R.color.orange).show()
            }
        }

        feedViewModel.isOffline.observe(viewLifecycleOwner) { isOffline ->
            if (isOffline == true) {
                binding.recipeListRV.isVisible = false
                binding.isLoadingCIP.isVisible = false
                binding.offlineTV.isVisible = true
                binding.retryConnectionBTN.isVisible = true
            } else {
                binding.recipeListRV.isVisible = true
                binding.isLoadingCIP.isVisible = true
                binding.offlineTV.isVisible = false
                binding.retryConnectionBTN.isVisible = false
            }
        }
    }
}