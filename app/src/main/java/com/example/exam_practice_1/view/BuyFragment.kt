package com.example.exam_practice_1.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.exam_practice_1.R
import com.example.exam_practice_1.databinding.BuyFragmentBinding
import com.example.exam_practice_1.model.ProdusBuy
import com.example.exam_practice_1.viewModel
import com.example.exam_practice_1.viewmodel.ExamViewModel
import com.tapadoo.alerter.Alerter
import com.zhuinden.fragmentviewbindingdelegatekt.viewBinding
import kotlinx.coroutines.delay
import org.koin.androidx.viewmodel.ext.android.activityViewModel

class BuyFragment : Fragment(R.layout.buy_fragment) {
    private val args: BuyFragmentArgs by navArgs()
    private val buyViewModel: ExamViewModel by activityViewModel()
    private val binding by viewBinding(BuyFragmentBinding::bind)
    private var isAllowed: Boolean? = null


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()
        initViews()
    }

    private fun initViews() {
        binding.toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }

        val produs = args.toBuyProdus

        binding.produsNameTV.text = produs.nume

        binding.buyBtn.setOnClickListener {

            val cantitate = binding.quantityTIET.text.toString()
            if (cantitate.isBlank()) {
                Alerter.create(
                    requireActivity()
                ).setTitle("Error").setText("One or more fields are empty")
                    .setBackgroundColorRes(R.color.orange).show()
            } else {
                buyViewModel.buyProdus(ProdusBuy(produs.id, cantitate.toInt()))
            }
        }
    }

    private fun initObservers() {
        buyViewModel.isError.observe(viewLifecycleOwner) { isError ->
            if (isError != null) {
                Alerter.create(
                    requireActivity()
                ).setTitle("Error").setText(isError.toString())
                    .setBackgroundColorRes(R.color.orange).show()
            }
        }

        buyViewModel.isAllowed.observe(viewLifecycleOwner) { isAllowed ->
            if (isAllowed == true) {
                findNavController().navigate(BuyFragmentDirections.actionBuyFragmentToMainFragment())

            }
        }
    }
}
