package com.example.exam_practice_1.view

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.exam_practice_1.R
import com.example.exam_practice_1.databinding.ClientFragmentBinding
import com.example.exam_practice_1.databinding.StoreFragmentBinding
import com.example.exam_practice_1.model.Produs
import com.example.exam_practice_1.view.adapter.ClientListItemAdapter
import com.example.exam_practice_1.view.adapter.ListItemAdapter
import com.example.exam_practice_1.viewmodel.ExamViewModel
import com.tapadoo.alerter.Alerter
import com.zhuinden.fragmentviewbindingdelegatekt.viewBinding
import org.koin.androidx.viewmodel.ext.android.activityViewModel

class ClientFragment : Fragment(R.layout.client_fragment) {
    private val binding by viewBinding(ClientFragmentBinding::bind)
    private val clientViewModel: ExamViewModel by activityViewModel()
    private val adapter by lazy { initProduseAdapter() }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
        initRecyclerView(LinearLayoutManager(requireContext()))
        initObservers()
    }

    private fun initProduseAdapter(): ClientListItemAdapter =
        ClientListItemAdapter(::onItemClickListener)

    private fun initRecyclerView(layoutManager: LinearLayoutManager) {
        binding.recipeListRV.adapter = adapter
        binding.recipeListRV.layoutManager = layoutManager
        clientViewModel.getProductsList()
    }

    private fun onItemClickListener(produs: Produs) {
        findNavController().navigate(
            ClientFragmentDirections.actionClientFragmentToBuyFragment(
                toBuyProdus = produs
            )
        )

    }

    private fun initObservers() {
        clientViewModel.produsList.observe(viewLifecycleOwner) { list ->
            adapter.submitList(list)
            adapter.notifyDataSetChanged()
        }

        clientViewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            binding.isLoadingCIP.isVisible = isLoading
            binding.recipeListRV.isVisible = !isLoading
        }

        clientViewModel.isError.observe(viewLifecycleOwner) { isError ->
            if (isError != null) {
                Alerter.create(
                    requireActivity()
                ).setTitle("Error").setText(isError.toString())
                    .setBackgroundColorRes(R.color.orange).show()
            }
        }

    }

    private fun initViews() {
        binding.toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
    }
}